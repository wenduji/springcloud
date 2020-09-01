package com.example.activiti.business.mapper;

import com.example.activiti.business.entity.MultiInstances;
import org.apache.ibatis.annotations.Param;

public interface MultiInstancesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MultiInstances record);

    int insertSelective(MultiInstances record);

    MultiInstances selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MultiInstances record);

    int updateByPrimaryKey(MultiInstances record);

    MultiInstances selectSelective(@Param("processKey") String processKey, @Param("nodeId") String nodeId);
}