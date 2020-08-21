package com.example.activiti.business.controller;

import com.example.activiti.business.entity.ModelVO;
import com.example.activiti.business.service.ActivitiService;
import com.example.common.page.Page;
import com.example.common.page.PageInfo;
import com.example.common.page.PageUtils;
import com.example.common.result.ResultWrapper;
import com.example.common.utils.JSONUtils;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/models")
@Slf4j
@CrossOrigin(origins = "http://localhost:8848", allowCredentials = "true", allowedHeaders = "*")
public class ModelController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ActivitiService activitiService;

    @GetMapping("/list")
    public ResultWrapper<PageInfo<ModelVO>> list(@ModelAttribute Page page) {
        PageUtils.startPage(page);
        List<Map<String, String>> modelList = activitiService.findModelList();
        List<ModelVO> list = new ArrayList<>(modelList.size());
        for (Map<String, String> map : modelList) {
            ModelVO modelVO = JSONUtils.obj2pojo(map, ModelVO.class);
            list.add(modelVO);
        }
        PageInfo<ModelVO> pageInfo = new PageInfo<>(list);
        return ResultWrapper.success(pageInfo);
    }

    @GetMapping("/create")
    public ResultWrapper newModel() {
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
        return new ResultWrapper("static/modeler.html?modelId=" + id);
    }

    @RequestMapping("/deployment/{modelId}")
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
