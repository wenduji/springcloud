package com.example.demo.test.controller;

import com.example.demo.test.entity.BookType;
import com.example.demo.test.service.BookTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjs
 * @date 2020/7/16
 * @description
 */
@RestController
@RequestMapping("/rest/books")
public class BookRestController {
    @Resource
    private BookTypeService bookTypeService;

    @GetMapping("/lazy-load")
    public void lazyLoad() {
        List<BookType> bookTypeList = bookTypeService.listBookTypesByLazyLoad();
        for (BookType bookType : bookTypeList) {
            System.out.println(bookType.getType());
        }

        bookTypeList.get(0).getBookNameList();
    }
}
