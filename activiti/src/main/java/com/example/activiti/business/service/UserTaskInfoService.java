package com.example.activiti.business.service;

import com.example.activiti.business.constant.ActivitiConstant;
import com.example.activiti.business.entity.UserTaskInfo;
import com.example.activiti.business.mapper.UserTaskInfoMapper;
import com.example.common.utils.JSONUtils;
import com.example.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author hjs
 * @date 2020/8/26
 * @description
 */
@Service
public class UserTaskInfoService {

    @Resource
    private UserTaskInfoMapper userTaskInfoMapper;

    /**
     * 保存流程信息
     *
     * @param request 请求
     */
    public void save(HttpServletRequest request) {
        LinkedHashMap propertyMap = (LinkedHashMap) JSONUtils.json2map(request.getParameter("json_xml")).get("properties");
        String processKey = String.valueOf(propertyMap.get("process_id"));

        UserTaskInfo userTaskInfo;

        ArrayList childShapesList = (ArrayList) JSONUtils.json2map(request.getParameter("json_xml")).get("childShapes");
        for (Object o2 : childShapesList) {
            LinkedHashMap childShapesMap = (LinkedHashMap) o2;
            LinkedHashMap stencilMap = (LinkedHashMap) childShapesMap.get("stencil");
            if ("UserTask".equals(stencilMap.get("id"))) {
                LinkedHashMap propertiesMap = (LinkedHashMap) childShapesMap.get("properties");
                String userTaskId = String.valueOf(propertiesMap.get("overrideid"));
                String userTaskName = String.valueOf(propertiesMap.get("name"));
                LinkedHashMap taskRolesMap = (LinkedHashMap) propertiesMap.get("taskroles");
                ArrayList taskRolesList = (ArrayList) taskRolesMap.get("taskRoles");
                StringBuilder roles = new StringBuilder();
                for (Object o1 : taskRolesList) {
                    LinkedHashMap map = (LinkedHashMap) o1;
                    roles.append(map.get("type")).append(",");
                }

                userTaskInfo = new UserTaskInfo();
                userTaskInfo.setProcessKey(processKey);
                userTaskInfo.setNodeId(userTaskId);
                userTaskInfo.setNodeName(userTaskName);
                userTaskInfo.setHandlersRole(roles.substring(0, roles.length() - 1));
                userTaskInfo.setNodeType(ActivitiConstant.user_task_type.NORMAL);

                // 多实例属性
                String multiInstanceType = String.valueOf(propertiesMap.get("multiinstance_type"));
                if (!"None".equals(multiInstanceType)) {
                    String multiInstanceCondition = String.valueOf(propertiesMap.get("multiinstance_condition"));
                    String multiInstanceCollection = String.valueOf(propertiesMap.get("multiinstance_collection"));
                    userTaskInfo.setCompleteCondition(multiInstanceCondition);
                    userTaskInfo.setCollection(multiInstanceCollection);
                    userTaskInfo.setNodeType(ActivitiConstant.user_task_type.MULTI_INSTANCE);
                }

                // 组任务
                if (!ObjectUtils.isEmpty(propertiesMap.get("tasklisteners"))) {
                    LinkedHashMap tasklistenersMap = (LinkedHashMap) propertiesMap.get("tasklisteners");
                    ArrayList tasklistenersList = (ArrayList) tasklistenersMap.get("taskListeners");
                    for (Object o : tasklistenersList) {
                        LinkedHashMap map = (LinkedHashMap) o;
                        if (StringUtils.isNotEmpty(String.valueOf(map.get("delegateExpression")))) {
                            userTaskInfo.setNodeType(ActivitiConstant.user_task_type.GROUP);
                        }
                    }

                }

//                userTaskInfoMapper.insertSelective(userTaskInfo);
            }
        }
    }

    public UserTaskInfo selectSelective(String processKey, String nodeId) {
        return userTaskInfoMapper.selectSelective(processKey, nodeId);
    }

    /**
     * 查询组任务人员
     *
     * @param processKey 流程key
     * @param nodeId UserTask id
     */
    public List<String> listCandidateUsers(String processKey, String nodeId) {
        // todo
        return null;
    }

}
