package com.example.activiti.business.controller;

import com.example.common.result.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjs
 * @date 2020/8/26
 * @description
 */
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {

    @GetMapping
    public ResultWrapper mockData() {
        List<String> roleList = new ArrayList<>();
        roleList.add("Normal");
        roleList.add("Manager A");
        roleList.add("Manager B");
        roleList.add("Manager C");
        return ResultWrapper.success(roleList);
    }
}
