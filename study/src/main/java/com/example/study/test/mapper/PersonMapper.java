package com.example.study.test.mapper;

import com.example.study.test.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    long countByExample(Person example);

    int deleteByExample(Person example);

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(Person example);

    Person selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") Person example);

    int updateByExample(@Param("record") Person record, @Param("example") Person example);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}