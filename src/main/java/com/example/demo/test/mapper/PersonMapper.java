package com.example.demo.test.mapper;

import com.example.demo.test.entity.Person;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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