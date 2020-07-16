package com.example.demo.test.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * book_name
 * @author 
 */
@Getter
@Setter
public class BookName implements Serializable {
    private Byte id;

    private String name;

    private Byte typeId;

    private static final long serialVersionUID = 1L;
}