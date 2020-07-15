package com.example.demo.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hjs
 * @date 2020/7/15
 * @description
 */
@Getter
@Setter
public class Person implements Serializable {
    private Integer id;

    private String name;

    private Integer age;
}
