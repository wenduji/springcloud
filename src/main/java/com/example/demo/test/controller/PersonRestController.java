package com.example.demo.test.controller;

import com.example.demo.result.Result;
import com.example.demo.test.entity.Person;
import com.example.demo.test.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author hjs
 * @date 2020/7/15
 * @description
 */
@RestController
@RequestMapping("/rest/person")
public class PersonRestController {
    @Resource
    private PersonService personService;

    /**
     * 一级缓存，默认
     */
    @GetMapping("/caches/level/1")
    public void primaryCache() {
        Integer id = 1;
        Date firstDate = new Date();
        personService.select(id);
        System.out.println(new Date().getTime() - firstDate.getTime() + "ms");

        Date secondDate = new Date();
        personService.select(id);
        System.out.println(new Date().getTime() - secondDate.getTime() + "ms");
    }

    /**
     * 二级缓存
     */
    @GetMapping("/caches/level/2")
    public void secondaryCache() {
        Integer id = 1;
        Person person1 = personService.select(id);
        // 执行sql
        System.out.println(person1);

        Person person2 = personService.select(id);
        // 不执行sql，从缓存中读取
        System.out.println(person2);
    }

    @PostMapping
    public Result save(){
        Person person = new Person();
        person.setName("b");
        person.setAge(31);
        personService.insert(person);
        return Result.success();
    }
}
