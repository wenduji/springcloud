package com.example.activiti.editor.model;

import com.example.common.utils.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/service")
@Slf4j
public class ModelSaveRestResource implements ModelDataJsonConstants {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    @PutMapping(value = "/model/{modelId}/save")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(HttpServletRequest request, @PathVariable String modelId) {
        try {
            /*
            根据流程信息设置任务处理人角色
             */
            ArrayList childShapesList = (ArrayList) JSONUtils.json2map(request.getParameter("json_xml")).get("childShapes");
            for (int i = 0, size = childShapesList.size(); i < size; i++) {
                LinkedHashMap childShapesMap = (LinkedHashMap) childShapesList.get(i);
                LinkedHashMap stencilMap = (LinkedHashMap) childShapesMap.get("stencil");
                if ("UserTask".equals(stencilMap.get("id"))) {
                    LinkedHashMap propertiesMap = (LinkedHashMap) childShapesMap.get("properties");
                    LinkedHashMap taskrolesMap = (LinkedHashMap) propertiesMap.get("taskroles");
                    ArrayList taskRolesList = (ArrayList) taskrolesMap.get("taskRoles");
                    for (int j = 0, len = taskRolesList.size(); j < len; j++) {
                        LinkedHashMap map = (LinkedHashMap) taskRolesList.get(j);
//                        System.out.println(map.get("id") + "  " + map.get("type"));
                    }
                }
            }

            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, request.getParameter("name"));
            modelJson.put(MODEL_DESCRIPTION, request.getParameter("description"));
            model.setMetaInfo(modelJson.toString());
            model.setName(request.getParameter("name"));

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), request.getParameter("json_xml").getBytes(StandardCharsets.UTF_8));

            InputStream svgStream = new ByteArrayInputStream(request.getParameter("svg_xml").getBytes(StandardCharsets.UTF_8));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformationapp
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            log.error("Error saving model", e);
        }
    }
}
