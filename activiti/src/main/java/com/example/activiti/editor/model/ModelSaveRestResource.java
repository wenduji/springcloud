package com.example.activiti.editor.model;

import com.example.activiti.business.service.UserTaskInfoService;
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

@RestController
@RequestMapping("/service")
@Slf4j
public class ModelSaveRestResource implements ModelDataJsonConstants {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UserTaskInfoService userTaskInfoService;

    @PutMapping(value = "/model/{modelId}/save")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(HttpServletRequest request, @PathVariable String modelId) {
        try {
            userTaskInfoService.save(request);

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
