package com.example.flowable.controller;

import com.example.flowable.entity.FlowableNode;
import org.springframework.web.bind.annotation.*;

/**
 * @author hjs
 * @date 2020/8/3
 * @description 生成.bpmn文件
 */
@RestController
@RequestMapping("/rest/flow-creations")
public class FlowCreateRestController {
    @PostMapping
    public void createFlow(@RequestBody FlowableNode flowableNode){

    }
}
