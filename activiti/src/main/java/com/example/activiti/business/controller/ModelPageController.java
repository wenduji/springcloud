package com.example.activiti.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hjs
 * @date 2020/8/20
 * @description
 */
@Controller
@RequestMapping("/page/models")
public class ModelPageController {

    @GetMapping("/list")
    public ModelAndView modelList(ModelAndView modelAndView) {
        modelAndView.setViewName("/template/list");
        return modelAndView;
    }

}
