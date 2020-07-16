package com.example.demo.test.mapper;

import com.example.demo.test.entity.BookName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookNameMapper {
    long countByExample(BookName example);

    int deleteByExample(BookName example);

    int deleteByPrimaryKey(Byte id);

    int insert(BookName record);

    int insertSelective(BookName record);

    List<BookName> selectByExample(BookName example);

    BookName selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") BookName record, @Param("example") BookName example);

    int updateByExample(@Param("record") BookName record, @Param("example") BookName example);

    int updateByPrimaryKeySelective(BookName record);

    int updateByPrimaryKey(BookName record);
}