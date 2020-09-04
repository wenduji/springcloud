package com.example.study.stream;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hjs
 * @date 2020/9/4
 * @description
 */
@Getter
@Setter
public class Person implements Cloneable, Serializable {

    String name;
    String password;
    String[] arrFavor;

    public Person(String name, String password, String[] arrFavor) {
        this.name = name;
        this.password = password;
        this.arrFavor = arrFavor;
    }

    @Override
    public Object clone() {
        Person person;
        try {
            person = (Person) super.clone();
            // 深度克隆，注释掉即为浅克隆
//                person.arrFavor = arrFavor.clone();
            return person;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
