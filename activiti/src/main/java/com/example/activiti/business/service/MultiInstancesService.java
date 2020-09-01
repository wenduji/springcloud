package com.example.activiti.business.service;

import com.example.activiti.business.entity.MultiInstances;
import com.example.activiti.business.mapper.MultiInstancesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/9/1
 * @description
 */
@Service
public class MultiInstancesService {

    @Resource
    private MultiInstancesMapper multiInstancesMapper;

    public MultiInstances selectSelective(String processKey, String nodeId) {
        return multiInstancesMapper.selectSelective(processKey, nodeId);
    }

}
