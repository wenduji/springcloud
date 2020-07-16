package com.example.demo.test.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * book_type
 * @author 
 */
@Getter
@Setter
public class BookType implements Serializable {
    private Byte id;

    private String type;

    private List<BookName> bookNameList;

    private static final long serialVersionUID = 1L;
}