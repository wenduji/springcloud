package com.example.activiti;

import com.example.common.utils.JSONUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author hjs
 * @date 2020/8/26
 * @description
 */
public class ExtractUserTaskTest {

    @Test
    public void test() {
        String jsonStr = "{\n" +
                "\t\"resourceId\": \"17501\",\n" +
                "\t\"properties\": {\n" +
                "\t\t\"process_id\": \"process\",\n" +
                "\t\t\"name\": \"\",\n" +
                "\t\t\"documentation\": \"\",\n" +
                "\t\t\"process_author\": \"\",\n" +
                "\t\t\"process_version\": \"\",\n" +
                "\t\t\"process_namespace\": \"http://www.activiti.org/processdef\",\n" +
                "\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\"eventlisteners\": \"\",\n" +
                "\t\t\"signaldefinitions\": \"\",\n" +
                "\t\t\"messagedefinitions\": \"\"\n" +
                "\t},\n" +
                "\t\"stencil\": {\n" +
                "\t\t\"id\": \"BPMNDiagram\"\n" +
                "\t},\n" +
                "\t\"childShapes\": [{\n" +
                "\t\t\"resourceId\": \"sid-174F8354-FC55-4529-BB9F-8EEFB5C8AA1D\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"\",\n" +
                "\t\t\t\"name\": \"\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\t\"initiator\": \"\",\n" +
                "\t\t\t\"formkeydefinition\": \"\",\n" +
                "\t\t\t\"formproperties\": \"\"\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"StartNoneEvent\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [{\n" +
                "\t\t\t\"resourceId\": \"sid-680CB4F6-59DB-45DE-AEF3-442CE8493D0E\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 126,\n" +
                "\t\t\t\t\"y\": 78\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 96,\n" +
                "\t\t\t\t\"y\": 48\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-74516028-4312-4F62-BF30-A5616C2EA4C8\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"1\",\n" +
                "\t\t\t\"name\": \"多实例\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"asynchronousdefinition\": \"false\",\n" +
                "\t\t\t\"exclusivedefinition\": \"false\",\n" +
                "\t\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\t\"multiinstance_type\": \"Parallel\",\n" +
                "\t\t\t\"multiinstance_cardinality\": \"\",\n" +
                "\t\t\t\"multiinstance_collection\": \"\",\n" +
                "\t\t\t\"multiinstance_variable\": \"\",\n" +
                "\t\t\t\"multiinstance_condition\": \"\",\n" +
                "\t\t\t\"isforcompensation\": \"false\",\n" +
                "\t\t\t\"usertaskassignment\": \"\",\n" +
                "\t\t\t\"formkeydefinition\": \"\",\n" +
                "\t\t\t\"duedatedefinition\": \"\",\n" +
                "\t\t\t\"prioritydefinition\": \"\",\n" +
                "\t\t\t\"formproperties\": \"\",\n" +
                "\t\t\t\"tasklisteners\": \"\",\n" +
                "\t\t\t\"taskroles\": {\n" +
                "\t\t\t\t\"taskRoles\": [{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"type\": \"Manager A\",\n" +
                "\t\t\t\t\t\"readable\": true,\n" +
                "\t\t\t\t\t\"writable\": true\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"type\": \"Manager B\",\n" +
                "\t\t\t\t\t\"readable\": true,\n" +
                "\t\t\t\t\t\"writable\": true\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"UserTask\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [{\n" +
                "\t\t\t\"resourceId\": \"sid-B76EC3C9-7D9E-4043-89E2-E351EFCF7819\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 265,\n" +
                "\t\t\t\t\"y\": 95\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 165,\n" +
                "\t\t\t\t\"y\": 15\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-680CB4F6-59DB-45DE-AEF3-442CE8493D0E\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"\",\n" +
                "\t\t\t\"name\": \"\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"conditionsequenceflow\": \"\",\n" +
                "\t\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\t\"defaultflow\": \"false\"\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"SequenceFlow\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [{\n" +
                "\t\t\t\"resourceId\": \"sid-74516028-4312-4F62-BF30-A5616C2EA4C8\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 164.4404455144984,\n" +
                "\t\t\t\t\"y\": 61.798303501115264\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 126.62205448550158,\n" +
                "\t\t\t\t\"y\": 58.889196498884736\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": [{\n" +
                "\t\t\t\"x\": 15,\n" +
                "\t\t\t\"y\": 15\n" +
                "\t\t}, {\n" +
                "\t\t\t\"x\": 50,\n" +
                "\t\t\t\"y\": 40\n" +
                "\t\t}],\n" +
                "\t\t\"target\": {\n" +
                "\t\t\t\"resourceId\": \"sid-74516028-4312-4F62-BF30-A5616C2EA4C8\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-192DF18F-0317-4FC0-B265-0C2E15D1E845\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"2\",\n" +
                "\t\t\t\"name\": \"组任务\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"asynchronousdefinition\": \"false\",\n" +
                "\t\t\t\"exclusivedefinition\": \"false\",\n" +
                "\t\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\t\"multiinstance_type\": \"None\",\n" +
                "\t\t\t\"multiinstance_cardinality\": \"\",\n" +
                "\t\t\t\"multiinstance_collection\": \"\",\n" +
                "\t\t\t\"multiinstance_variable\": \"\",\n" +
                "\t\t\t\"multiinstance_condition\": \"\",\n" +
                "\t\t\t\"isforcompensation\": \"false\",\n" +
                "\t\t\t\"usertaskassignment\": \"\",\n" +
                "\t\t\t\"formkeydefinition\": \"\",\n" +
                "\t\t\t\"duedatedefinition\": \"\",\n" +
                "\t\t\t\"prioritydefinition\": \"\",\n" +
                "\t\t\t\"formproperties\": \"\",\n" +
                "\t\t\t\"tasklisteners\": {\n" +
                "\t\t\t\t\"taskListeners\": [{\n" +
                "\t\t\t\t\t\"event\": \"create\",\n" +
                "\t\t\t\t\t\"implementation\": \"${userTaskListener}\",\n" +
                "\t\t\t\t\t\"className\": \"\",\n" +
                "\t\t\t\t\t\"expression\": \"\",\n" +
                "\t\t\t\t\t\"delegateExpression\": \"${userTaskListener}\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"taskroles\": {\n" +
                "\t\t\t\t\"taskRoles\": [{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"type\": \"Normal\",\n" +
                "\t\t\t\t\t\"readable\": true,\n" +
                "\t\t\t\t\t\"writable\": true\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"type\": \"Manager B\",\n" +
                "\t\t\t\t\t\"readable\": true,\n" +
                "\t\t\t\t\t\"writable\": true\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"UserTask\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [{\n" +
                "\t\t\t\"resourceId\": \"sid-5BD03004-0829-4AF4-AB37-5EE0ED16FEEE\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 416,\n" +
                "\t\t\t\t\"y\": 103\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 316,\n" +
                "\t\t\t\t\"y\": 23\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-B76EC3C9-7D9E-4043-89E2-E351EFCF7819\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"\",\n" +
                "\t\t\t\"name\": \"\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"conditionsequenceflow\": \"\",\n" +
                "\t\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\t\"defaultflow\": \"false\"\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"SequenceFlow\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [{\n" +
                "\t\t\t\"resourceId\": \"sid-192DF18F-0317-4FC0-B265-0C2E15D1E845\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 315.453125,\n" +
                "\t\t\t\t\"y\": 63\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 265.546875,\n" +
                "\t\t\t\t\"y\": 55\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": [{\n" +
                "\t\t\t\"x\": 50,\n" +
                "\t\t\t\"y\": 40\n" +
                "\t\t}, {\n" +
                "\t\t\t\"x\": 290.5,\n" +
                "\t\t\t\"y\": 55\n" +
                "\t\t}, {\n" +
                "\t\t\t\"x\": 290.5,\n" +
                "\t\t\t\"y\": 63\n" +
                "\t\t}, {\n" +
                "\t\t\t\"x\": 50,\n" +
                "\t\t\t\"y\": 40\n" +
                "\t\t}],\n" +
                "\t\t\"target\": {\n" +
                "\t\t\t\"resourceId\": \"sid-192DF18F-0317-4FC0-B265-0C2E15D1E845\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-A852B229-9946-4E3C-AACF-1681DD13B124\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"\",\n" +
                "\t\t\t\"name\": \"\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"executionlisteners\": \"\"\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"EndNoneEvent\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 489,\n" +
                "\t\t\t\t\"y\": 77\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 461,\n" +
                "\t\t\t\t\"y\": 49\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-5BD03004-0829-4AF4-AB37-5EE0ED16FEEE\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"\",\n" +
                "\t\t\t\"name\": \"\",\n" +
                "\t\t\t\"documentation\": \"\",\n" +
                "\t\t\t\"conditionsequenceflow\": \"\",\n" +
                "\t\t\t\"executionlisteners\": \"\",\n" +
                "\t\t\t\"defaultflow\": \"false\"\n" +
                "\t\t},\n" +
                "\t\t\"stencil\": {\n" +
                "\t\t\t\"id\": \"SequenceFlow\"\n" +
                "\t\t},\n" +
                "\t\t\"childShapes\": [],\n" +
                "\t\t\"outgoing\": [{\n" +
                "\t\t\t\"resourceId\": \"sid-A852B229-9946-4E3C-AACF-1681DD13B124\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 460.375,\n" +
                "\t\t\t\t\"y\": 63\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 416.390625,\n" +
                "\t\t\t\t\"y\": 63\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": [{\n" +
                "\t\t\t\"x\": 50,\n" +
                "\t\t\t\"y\": 40\n" +
                "\t\t}, {\n" +
                "\t\t\t\"x\": 14,\n" +
                "\t\t\t\"y\": 14\n" +
                "\t\t}],\n" +
                "\t\t\"target\": {\n" +
                "\t\t\t\"resourceId\": \"sid-A852B229-9946-4E3C-AACF-1681DD13B124\"\n" +
                "\t\t}\n" +
                "\t}],\n" +
                "\t\"bounds\": {\n" +
                "\t\t\"lowerRight\": {\n" +
                "\t\t\t\"x\": 1200,\n" +
                "\t\t\t\"y\": 1050\n" +
                "\t\t},\n" +
                "\t\t\"upperLeft\": {\n" +
                "\t\t\t\"x\": 0,\n" +
                "\t\t\t\"y\": 0\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"stencilset\": {\n" +
                "\t\t\"url\": \"stencilsets/bpmn2.0/bpmn2.0.json\",\n" +
                "\t\t\"namespace\": \"http://b3mn.org/stencilset/bpmn2.0#\"\n" +
                "\t},\n" +
                "\t\"ssextensions\": []\n" +
                "}";

        LinkedHashMap propertyMap = (LinkedHashMap) JSONUtils.json2map(jsonStr).get("properties");
        String processKey = String.valueOf(propertyMap.get("process_id"));
        System.out.println(processKey);

        ArrayList childShapesList = (ArrayList) JSONUtils.json2map(jsonStr).get("childShapes");
        for (int i = 0, size = childShapesList.size(); i < size; i++) {
            LinkedHashMap childShapesMap = (LinkedHashMap) childShapesList.get(i);
            LinkedHashMap stencilMap = (LinkedHashMap) childShapesMap.get("stencil");
            if ("UserTask".equals(stencilMap.get("id"))) {
                LinkedHashMap propertiesMap = (LinkedHashMap) childShapesMap.get("properties");

                String userTaskId = String.valueOf(propertiesMap.get("overrideid"));
                System.out.println(userTaskId);

                LinkedHashMap taskrolesMap = (LinkedHashMap) propertiesMap.get("taskroles");
                ArrayList taskRolesList = (ArrayList) taskrolesMap.get("taskRoles");
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0, len = taskRolesList.size(); j < len; j++) {
                    LinkedHashMap map = (LinkedHashMap) taskRolesList.get(j);
                    System.out.println(map.get("id") + "  " + map.get("type"));
                    stringBuilder.append(map.get("type")).append(",");
                }
                System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));

                if (!ObjectUtils.isEmpty(propertiesMap.get("tasklisteners"))) {
                    LinkedHashMap tasklistenersMap = (LinkedHashMap) propertiesMap.get("tasklisteners");
                    ArrayList tasklistenersList = (ArrayList) tasklistenersMap.get("taskListeners");
                    for (int j = 0, len = tasklistenersList.size(); j < len; j++) {
                        LinkedHashMap map = (LinkedHashMap) tasklistenersList.get(j);
                        System.out.println(map.get("event") + "  " + map.get("delegateExpression"));
                    }
                }
            }
        }
    }
}
