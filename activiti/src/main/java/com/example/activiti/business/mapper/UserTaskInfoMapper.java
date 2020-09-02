package com.example.activiti.business.mapper;

import com.example.activiti.business.entity.UserTaskInfo;
import org.apache.ibatis.annotations.Param;

public interface UserTaskInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTaskInfo record);

    int insertSelective(UserTaskInfo record);

    UserTaskInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTaskInfo record);

    int updateByPrimaryKey(UserTaskInfo record);

    UserTaskInfo selectSelective(@Param("ProcessKey") String processKey, @Param("nodeId") String nodeId);
}