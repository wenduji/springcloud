package com.example.study.clone;

import com.example.study.stream.Person;
import org.apache.commons.lang.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author hjs
 * @date 2020/9/4
 * @description 浅拷贝与深度拷贝
 */
public class CloneTest {

    @Test
    public void test() {
        String[] arrFavor = {"basketball", "football"};
        Person person = new Person("Jay", "123", arrFavor);

        // 深度克隆的2种方式
        // 1、Person类实现Serializable接口，通过序列化实现深度克隆
        Person p = (Person) SerializationUtils.clone(person);
        p.setName("Jolin");
        p.getArrFavor()[0] = "badminton";

        // 2、重写clone()
        /*Person p = (Person) person.clone();
        p.setName("Jolin");
        p.getArrFavor()[0] = "badminton";*/

        System.out.println("原对象：");
        System.out.println(person.getName());
        Stream.of(person.getArrFavor()).forEach(e -> System.out.println(e));
        System.out.println("克隆对象：");
        System.out.println(p.getName());
        Stream.of(p.getArrFavor()).forEach(e -> System.out.println(e));
    }

}
