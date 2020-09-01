package com.example.activiti.business.service;

import com.example.activiti.business.entity.MultiInstances;
import com.example.activiti.business.entity.TaskRole;
import com.example.activiti.business.mapper.MultiInstancesMapper;
import com.example.activiti.business.mapper.TaskRoleMapper;
import com.example.common.utils.JSONUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author hjs
 * @date 2020/8/26
 * @description
 */
@Service
public class TaskRoleService {

    @Resource
    private TaskRoleMapper taskRoleMapper;

    @Resource
    private MultiInstancesMapper multiInstancesMapper;

    /**
     * 根据流程信息设置任务处理人角色、多实例属性
     *
     * @param request
     */
    public void save(HttpServletRequest request) {
        LinkedHashMap propertyMap = (LinkedHashMap) JSONUtils.json2map(request.getParameter("json_xml")).get("properties");
        String processKey = String.valueOf(propertyMap.get("process_id"));

        TaskRole taskRole;
        MultiInstances multiInstances;

        ArrayList childShapesList = (ArrayList) JSONUtils.json2map(request.getParameter("json_xml")).get("childShapes");
        for (int i = 0, size = childShapesList.size(); i < size; i++) {
            LinkedHashMap childShapesMap = (LinkedHashMap) childShapesList.get(i);
            LinkedHashMap stencilMap = (LinkedHashMap) childShapesMap.get("stencil");
            if ("UserTask".equals(stencilMap.get("id"))) {
                LinkedHashMap propertiesMap = (LinkedHashMap) childShapesMap.get("properties");
                LinkedHashMap taskRolesMap = (LinkedHashMap) propertiesMap.get("taskroles");
                String userTaskId = String.valueOf(propertiesMap.get("overrideid"));
                ArrayList taskRolesList = (ArrayList) taskRolesMap.get("taskRoles");

                if (!ObjectUtils.isEmpty(taskRolesList)) {
                    ArrayList<String> roles = new ArrayList<>();
                    for (int j = 0, len = taskRolesList.size(); j < len; j++) {
                        LinkedHashMap map = (LinkedHashMap) taskRolesList.get(j);
                        String role = String.valueOf(map.get("type"));
                        roles.add(role);
                    }

                    for (int j = 0, len = roles.size(); j < len; j++) {
                        taskRole = new TaskRole();
                        taskRole.setKey(processKey);
                        taskRole.setTaskId(userTaskId);
                        taskRole.setStarter(roles.get(j));
                        // 用户任务只有一个角色时，不配置TaskRole：approver
                        taskRole.setApprover(j != len - 1 ? roles.get(j + 1) : null);
//                    taskRoleMapper.insertSelective(taskRole);
                    }
                }

                // 多实例属性
                String multiInstanceType = String.valueOf(propertiesMap.get("multiinstance_type"));
                if (!"None".equals(multiInstanceType)) {
                    String multiInstanceCondition = String.valueOf(propertiesMap.get("multiinstance_condition"));
                    String multiInstanceCollection = String.valueOf(propertiesMap.get("multiinstance_collection"));
                    multiInstances = new MultiInstances();
                    multiInstances.setKey(processKey);
                    multiInstances.setTaskId(userTaskId);
                    multiInstances.setCompleteCondition(multiInstanceCondition);
                    multiInstances.setCollection(multiInstanceCollection);
//                    multiInstancesMapper.insertSelective(multiInstances);
                }
            }
        }
    }

    /**
     * 查询身份为当前任务角色的员工
     *
     * @param processKey
     * @param nodeId
     */
    public void listCandidateUsers(String processKey, String nodeId) {
        String role = taskRoleMapper.selectStarterRole(processKey, nodeId);
        // 根据角色查询员工

    }
}
