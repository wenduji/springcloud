package com.example.activiti.business.service;

import com.example.activiti.business.context.ActivitiContext;
import com.example.common.utils.Validate;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjs
 * @date 2020/3/13
 * @description
 */
@Service
public class ActivitiService {

    /**
     * 根据流程实例id获取任务id
     *
     * @param processInstanceId 流程实例id
     * @return 任务id
     */
    public String getTaskId(String processInstanceId) {
        Validate.isNotNull(processInstanceId);
        Task task = ActivitiContext.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            return null;
        }
        return task.getId();
    }

    /**
     * 拾取任务
     *
     * @param taskId 任务ID
     * @param userId 用户ID
     */
    public void claim(String taskId, String userId) {
        Validate.isNotNull(taskId);
        Validate.isNotNull(userId);
        ActivitiContext.getTaskService().claim(taskId, userId);
    }

    /**
     * 完成任务
     *
     * @param taskId 任务ID
     */
    @Transactional
    public void completeTask(String taskId) {
        Validate.isNotNull(taskId);
        completeTaskWithVariable(taskId, null);
    }

    /**
     * 完成任务,设置流程变量
     *
     * @param taskId   任务ID
     * @param variable 流程变量
     */
    @Transactional
    public void completeTaskWithVariable(String taskId, Map<String, Object> variable) {
        Validate.isNotNull(taskId);
        ActivitiContext.getTaskService().setVariablesLocal(taskId, variable);
        ActivitiContext.getTaskService().complete(taskId, variable);
    }

    /**
     * 获取任务的负责人
     *
     * @param processInstanceId 流程实例id
     * @return 任务负责人
     */
    public String getAssignee(String processInstanceId) {
        Validate.isNotNull(processInstanceId);
        Task task = ActivitiContext.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
        return task.getAssignee();
    }

    /**
     * 获取流程发布状态
     *
     * @param key 流程Key
     * @return 状态（true：已发布；false：未发布）
     */
    public boolean isDeployed(String key) {
        Validate.isNotNull(key);
        List<ProcessDefinition> list = ActivitiContext.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(key).list();
        if (list == null || list.size() == 0) {//流程未部署
            return false;
        } else {
            return true;
        }
    }

    /**
     * 发布流程
     *
     * @param bpmnName 流程文件名
     */
    public Deployment deployProcess(String bpmnName) {
        Validate.isNotNull(bpmnName);
        if (!isDeployed(bpmnName)) {
            if (cancelDeployProcess(bpmnName)) {
                return ActivitiContext.getRepositoryService().createDeployment().addClasspathResource("processes/" + bpmnName + ".bpmn").deploy();
            }
        }
        return null;
    }

    /**
     * 流程删除
     *
     * @param bpmnName 流程文件名
     * @return 状态（true：删除；false：不允许删除）
     */
    @Transactional
    public boolean cancelDeployProcess(String bpmnName) {
        Validate.isNotNull(bpmnName);
        List<ProcessDefinition> processDefinitionList = ActivitiContext.getRepositoryService().createProcessDefinitionQuery().
                processDefinitionResourceName("processes/" + bpmnName).list();
        //判断取消流程发布时是否有正在执行的任务
        boolean flag = true;
        for (ProcessDefinition processDefinition : processDefinitionList) {
            String deploymentId = processDefinition.getDeploymentId();
            //若当前规则下有正在执行的流程，返回false
            try {
                ActivitiContext.getRepositoryService().deleteDeployment(deploymentId);
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 委派任务
     *
     * @param taskId
     * @param userId
     */
    @Transactional
    public void delegateTask(String taskId, String userId) {
        Validate.isNotNull(taskId);
        Validate.isNotNull(userId);
        ActivitiContext.getTaskService().delegateTask(taskId, userId);
    }

    /**
     * 委派人完成任务
     *
     * @param taskId
     * @param comment
     */
    public void resolveTask(String taskId, String comment) {
        Validate.isNotNull(taskId);
        Validate.isNotNull(comment);
        Map<String, Object> variable = new HashMap<>();
        variable.put("advice", comment);
        ActivitiContext.getTaskService().resolveTask(taskId, variable);
    }

    /**
     * 启动流程实例，同时设置流程发起人
     *
     * @param processKey
     * @param applicantId
     * @return
     */
    @Transactional
    public ProcessInstance startProcessByKeyAndUserId(String processKey, String applicantId) {
        Validate.isNotNull(processKey);
        Validate.isNotNull(applicantId);

        //applicantId对应流程发起人
        ActivitiContext.getIdentityService().setAuthenticatedUserId(applicantId);
        ProcessInstance processInstance = ActivitiContext.getRuntimeService().startProcessInstanceByKey(processKey);
        return processInstance;
    }

    /**
     * 添加审批意见
     *
     * @param taskId    当前任务ID
     * @param processId 流程ID
     * @param message   审批意见
     */
    @Transactional
    public void addComment(String taskId, String processId, String message) {
        ActivitiContext.getTaskService().addComment(taskId, processId, message);
    }

    /**
     * 查询流程节点信息
     *
     * @param processKey
     * @return
     */
    public List<String> findProcessNodes(String processKey) {
        Validate.isNotNull(processKey);
        Process process = null;
        //读取流程文件
        String filePath = ActivitiContext.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(processKey).singleResult().getResourceName();
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = null;
        XMLStreamReader xtr = null;
        try {
            in = new InputStreamReader(resourceStream, "UTF-8");
            xtr = xif.createXMLStreamReader(in);
            BpmnModel model = new BpmnXMLConverter().convertToBpmnModel(xtr);
            process = model.getMainProcess();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (xtr != null) {
                try {
                    xtr.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //流程节点信息
        List<String> nodeList = new ArrayList<>();
//        List<StartEvent> startEventList = process.findFlowElementsOfType(StartEvent.class);
        List<UserTask> userTaskList = process.findFlowElementsOfType(UserTask.class);
//        List<EndEvent> endEventList = process.findFlowElementsOfType(EndEvent.class);
//        nodeList.add(startEventList.get(0).getId());
        for (UserTask userTask : userTaskList) {
            nodeList.add(userTask.getName());
//            userTask.getCandidateUsers();
        }
//         nodeList.add(endEventList.get(0).getId());
        return nodeList;
//        return userTaskList;
    }

    /**
     * 查询流程流转记录
     *
     * @param processInstanceId
     */
    public List<Map<String, Object>> findProcessFlow(String processInstanceId) {
        Validate.isNotNull(processInstanceId);
        List<Comment> commentList = ActivitiContext.getTaskService().getProcessInstanceComments(processInstanceId);
        String taskId;
        List<Map<String, Object>> flowList = new ArrayList<>();
        Map<String, Object> map;
        // 流程开始信息
        List<HistoricTaskInstance> list =
                ActivitiContext.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricTaskInstanceStartTime().asc().list();
        HistoricVariableInstanceQuery historicVariableInstanceQuery = ActivitiContext.getHistoryService().createHistoricVariableInstanceQuery();
        HistoricVariableInstance historicVariableInstance;
        for (HistoricTaskInstance task : list) {
            map = new HashMap<>();
            taskId = task.getId();
            map.put("taskId", taskId);
            map.put("taskName", task.getName());
            map.put("taskStartTime", task.getStartTime());
            map.put("taskEndTime", task.getEndTime());
            map.put("taskAssignee", task.getAssignee());
            for (Comment comment : commentList) {
                if (taskId.equals(comment.getTaskId())) {
                    map.put("comment", comment.getFullMessage());
                }
            }
            historicVariableInstance = historicVariableInstanceQuery.processInstanceId(task.getProcessInstanceId()).taskId(taskId).variableName("status").singleResult();
            if (!ObjectUtils.isEmpty(historicVariableInstance)) {
                map.put("status", historicVariableInstance.getValue());
            }
            flowList.add(map);
        }
        return flowList;
    }

    /**
     * 删除流程任务
     *
     * @param processId
     */
    @Transactional
    public void deleteProcessInstance(String processId, String deleteReason) {
        Validate.isNotNull(processId);
        ActivitiContext.getRuntimeService().deleteProcessInstance(processId, deleteReason);
    }

    /**
     * 流程发起人
     *
     * @param processId
     */
    public String getProcessStarter(String processId) {
        Validate.isNotNull(processId);
        HistoricProcessInstance historicProcessInstance = ActivitiContext.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
        return historicProcessInstance.getStartUserId();
    }

    /**
     * 已部署流程信息
     */
    public List<HashMap<String, String>> findDeployedFlows() {
        List<ProcessDefinition> list = ActivitiContext.getRepositoryService().createProcessDefinitionQuery().list();
        HashMap<String, String> map;
        List<HashMap<String, String>> flowList = new ArrayList<>();
        for (ProcessDefinition definition : list) {
            map = new HashMap<>();
            map.put("key", definition.getKey());
            map.put("flowName", definition.getName());
            flowList.add(map);
        }
        return flowList;
    }
}
