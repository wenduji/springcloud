package com.example.activiti.business.service;

import com.example.activiti.business.mapper.TaskRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/8/26
 * @description
 */
@Service
public class TaskRoleService {

    @Resource
    private TaskRoleMapper roleMapper;
}
