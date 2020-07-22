package com.example.study.test.service;

import com.example.study.test.entity.BookType;
import com.example.study.test.mapper.BookTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjs
 * @date 2020/7/16
 * @description
 */
@Service
public class BookTypeService {
    @Resource
    private BookTypeMapper bookTypeMapper;

    public List<BookType> listBookTypesByLazyLoad() {
        return bookTypeMapper.listBookTypesByLazyLoad();
    }
}
