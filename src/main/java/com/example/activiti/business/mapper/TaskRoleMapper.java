package com.example.activiti.business.mapper;

import com.example.activiti.business.entity.TaskRole;

public interface TaskRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskRole record);

    int insertSelective(TaskRole record);

    TaskRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskRole record);

    int updateByPrimaryKey(TaskRole record);
}