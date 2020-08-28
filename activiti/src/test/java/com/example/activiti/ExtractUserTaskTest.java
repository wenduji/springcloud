package com.example.activiti;

import com.example.common.utils.JSONUtils;
import org.junit.Test;

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
                "\t\"resourceId\": \"185001\",\n" +
                "\t\"properties\": {\n" +
                "\t\t\"process_id\": \"mockA\",\n" +
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
                "\t\t\"resourceId\": \"sid-A64B3C4C-D9D6-4E68-9F3B-56B351DCE91B\",\n" +
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
                "\t\t\t\"resourceId\": \"sid-7CD7F118-3358-4B2C-B6A4-A39705C2087C\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 126,\n" +
                "\t\t\t\t\"y\": 61\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 96,\n" +
                "\t\t\t\t\"y\": 31\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-D43D2F33-261D-4CF6-8DD6-AE83489C6DFD\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"1\",\n" +
                "\t\t\t\"name\": \"\",\n" +
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
                "\t\t\t\"tasklisteners\": \"\",\n" +
                "\t\t\t\"taskroles\": {\n" +
                "\t\t\t\t\"taskRoles\": [{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"type\": \"Normal\",\n" +
                "\t\t\t\t\t\"readable\": true,\n" +
                "\t\t\t\t\t\"writable\": true\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"type\": \"Manager A\",\n" +
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
                "\t\t\t\"resourceId\": \"sid-ABC60132-98A6-4C10-A7F9-FB2AC07D55A0\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 271,\n" +
                "\t\t\t\t\"y\": 86\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 171,\n" +
                "\t\t\t\t\"y\": 6\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-7CD7F118-3358-4B2C-B6A4-A39705C2087C\",\n" +
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
                "\t\t\t\"resourceId\": \"sid-D43D2F33-261D-4CF6-8DD6-AE83489C6DFD\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 170.15625,\n" +
                "\t\t\t\t\"y\": 46\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 126.609375,\n" +
                "\t\t\t\t\"y\": 46\n" +
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
                "\t\t\t\"resourceId\": \"sid-D43D2F33-261D-4CF6-8DD6-AE83489C6DFD\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-0DCFB454-2BC1-42EC-B9F2-4DF3EB181D2D\",\n" +
                "\t\t\"properties\": {\n" +
                "\t\t\t\"overrideid\": \"2\",\n" +
                "\t\t\t\"name\": \"\",\n" +
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
                "\t\t\t\"tasklisteners\": \"\",\n" +
                "\t\t\t\"taskroles\": {\n" +
                "\t\t\t\t\"taskRoles\": [{\n" +
                "\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\"type\": \"Manager B\",\n" +
                "\t\t\t\t\t\"readable\": true,\n" +
                "\t\t\t\t\t\"writable\": true\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": 2,\n" +
                "\t\t\t\t\t\"type\": \"Manager C\",\n" +
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
                "\t\t\t\"resourceId\": \"sid-FF4F99BF-EC1D-450A-92EF-91E2A1DB5043\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 416,\n" +
                "\t\t\t\t\"y\": 86\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 316,\n" +
                "\t\t\t\t\"y\": 6\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-ABC60132-98A6-4C10-A7F9-FB2AC07D55A0\",\n" +
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
                "\t\t\t\"resourceId\": \"sid-0DCFB454-2BC1-42EC-B9F2-4DF3EB181D2D\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 315.15625,\n" +
                "\t\t\t\t\"y\": 46\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 271.84375,\n" +
                "\t\t\t\t\"y\": 46\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": [{\n" +
                "\t\t\t\"x\": 50,\n" +
                "\t\t\t\"y\": 40\n" +
                "\t\t}, {\n" +
                "\t\t\t\"x\": 50,\n" +
                "\t\t\t\"y\": 40\n" +
                "\t\t}],\n" +
                "\t\t\"target\": {\n" +
                "\t\t\t\"resourceId\": \"sid-0DCFB454-2BC1-42EC-B9F2-4DF3EB181D2D\"\n" +
                "\t\t}\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-AE26FD2B-010A-474B-AE14-B200E270A089\",\n" +
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
                "\t\t\t\t\"y\": 60\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 461,\n" +
                "\t\t\t\t\"y\": 32\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"dockers\": []\n" +
                "\t}, {\n" +
                "\t\t\"resourceId\": \"sid-FF4F99BF-EC1D-450A-92EF-91E2A1DB5043\",\n" +
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
                "\t\t\t\"resourceId\": \"sid-AE26FD2B-010A-474B-AE14-B200E270A089\"\n" +
                "\t\t}],\n" +
                "\t\t\"bounds\": {\n" +
                "\t\t\t\"lowerRight\": {\n" +
                "\t\t\t\t\"x\": 460.375,\n" +
                "\t\t\t\t\"y\": 46\n" +
                "\t\t\t},\n" +
                "\t\t\t\"upperLeft\": {\n" +
                "\t\t\t\t\"x\": 416.390625,\n" +
                "\t\t\t\t\"y\": 46\n" +
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
                "\t\t\t\"resourceId\": \"sid-AE26FD2B-010A-474B-AE14-B200E270A089\"\n" +
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
                for (int j = 0, len = taskRolesList.size(); j < len; j++) {
                    LinkedHashMap map = (LinkedHashMap) taskRolesList.get(j);
                    System.out.println(map.get("id") + "  " + map.get("type"));
                }
            }
        }
    }
}
