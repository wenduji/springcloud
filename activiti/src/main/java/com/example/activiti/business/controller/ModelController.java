package com.example.activiti.business.controller;

import com.example.common.constant.ErrorCode;
import com.example.common.result.Result;
import com.example.common.result.ResultWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/models")
@Slf4j
@CrossOrigin
public class ModelController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping("/create")
    public Result newModel(HttpServletRequest request, HttpServletResponse response) {
        try {
            //初始化一个空模型
            Model model = repositoryService.newModel();

            //设置一些默认信息
            String name = "new-process";
            String description = "";
            int revision = 1;
            String key = "process";

            ObjectNode modelNode = objectMapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

            model.setName(name);
            model.setKey(key);
            model.setMetaInfo(modelNode.toString());

            repositoryService.saveModel(model);
            String id = model.getId();

            //完善ModelEditorSource
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace",
                    "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.set("stencilset", stencilSetNode);
            repositoryService.addModelEditorSource(id, editorNode.toString().getBytes(StandardCharsets.UTF_8));

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.sendRedirect(request.getContextPath() + "/static/modeler.html?modelId=" + id);
            return Result.success();
        } catch (IOException e) {
            log.info("模型创建失败！");
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.error(ErrorCode.FLOW_CREATE_ERROR);
        }
    }

    @RequestMapping("/deployment/{modelId}")
    @ResponseBody
    public ResultWrapper deploy(@PathVariable String modelId) throws Exception {
        //获取模型
        Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return ResultWrapper.success("模型数据为空，请先设计流程并成功保存，再进行发布。");
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if (model.getProcesses().size() == 0) {
            return ResultWrapper.success("数据模型不符要求，请至少设计一条主线流程。");
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, StandardCharsets.UTF_8))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        return ResultWrapper.success("流程发布成功");
    }
}
