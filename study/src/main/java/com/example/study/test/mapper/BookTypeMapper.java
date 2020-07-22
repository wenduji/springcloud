package com.example.study.test.mapper;

import com.example.study.test.entity.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookTypeMapper {
    long countByExample(BookType example);

    int deleteByExample(BookType example);

    int deleteByPrimaryKey(Byte id);

    int insert(BookType record);

    int insertSelective(BookType record);

    List<BookType> selectByExample(BookType example);

    BookType selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") BookType record, @Param("example") BookType example);

    int updateByExample(@Param("record") BookType record, @Param("example") BookType example);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);

    List<BookType> listBookTypesByLazyLoad();
}