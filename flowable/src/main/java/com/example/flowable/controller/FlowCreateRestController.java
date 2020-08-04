package com.example.flowable.controller;

import com.example.common.constant.ErrorCode;
import com.example.common.exception.ApiException;
import com.example.common.result.Result;
import com.example.common.result.ResultWrapper;
import com.example.common.utils.JSONUtils;
import com.example.flowable.config.BpmnContent;
import com.example.flowable.entity.FlowableNode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author hjs
 * @date 2020/8/3
 * @description 生成.bpmn文件
 */
@RestController
@RequestMapping("/rest/flow-creations")
public class FlowCreateRestController {
    @Resource
    private BpmnContent bpmnContent;

    @PostMapping
    public Result createFlow(@RequestBody List<FlowableNode> flowableNodeList) {
        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ApiException(ErrorCode.FLOW_CREATE_ERROR);
        }
        Document document = db.newDocument();
        // 不显示standalone="no"
        document.setXmlStandalone(true);

        // definitions节点
        Element definitionNode = document.createElement("definitions");
        List<String> definitionsKeys = bpmnContent.getDefinitionsKeys();
        List<String> definitionsValues = bpmnContent.getDefinitionsValues();
        for (int i = 0; i < definitionsKeys.size(); i++) {
            definitionNode.setAttribute(definitionsKeys.get(i), definitionsValues.get(i));
        }

        // 流程节点
        if (ObjectUtils.isEmpty(flowableNodeList) || flowableNodeList.size() == 0) {
            throw new ApiException(ErrorCode.FLOW_CREATE_ERROR);
        }

        String nodeType;
        Element process = null;
        Element node;
        String key = null;
        for (FlowableNode flowableNode : flowableNodeList) {
            nodeType = flowableNode.getType();
            if ("process".equals(nodeType)) {
                // process根节点追加到definitions节点下
                process = document.createElement(nodeType);
                setNodeAttribute(flowableNode, process);
                key = flowableNode.getId();
            } else {
                // 其他子节点追加到process根节点下
                node = document.createElement(nodeType);
                setNodeAttribute(flowableNode, node);
                process.appendChild(node);
            }
        }
        definitionNode.appendChild(process);
        document.appendChild(definitionNode);

        // 创建TransformerFactory对象
        TransformerFactory tff = TransformerFactory.newInstance();
        // 创建 Transformer对象
        Transformer tf;
        try {
            tf = tff.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new ApiException(ErrorCode.FLOW_CREATE_ERROR);
        }
        // 输出内容换行
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        // 创建bpmn文件并写入内容
        String filePath = "src/main/resources/processes/";
        // 流程文件名：process节点id值首字母小写
        String fileName = new StringBuilder().append(Character.toLowerCase(key.charAt(0))).append(key.substring(1)).append(".bpmn").toString();
        try {
            tf.transform(new DOMSource(document), new StreamResult(new File(filePath + fileName)));
        } catch (TransformerException e) {
            throw new ApiException(ErrorCode.FLOW_CREATE_ERROR);
        }
        return ResultWrapper.success();
    }

    /**
     * 为节点赋属性值
     *
     * @param flowableNode 节点实体类
     * @param node         节点
     */
    private void setNodeAttribute(FlowableNode flowableNode, Element node) {
        String jsonStr = JSONUtils.obj2json(flowableNode);
        Map<String, Object> map = JSONUtils.json2map(jsonStr);
        String key;
        for (Map.Entry<String, Object> nodeMap : map.entrySet()) {
            key = nodeMap.getKey();
            if (!"type".equals(key) && ObjectUtils.isNotEmpty(nodeMap.getValue())) {
                node.setAttribute(key, String.valueOf(nodeMap.getValue()));
            }
        }
    }
}
