package com.example.demo.mybatis.service;

import com.example.demo.mybatis.entity.Person;
import com.example.demo.mybatis.mapper.PersonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/7/15
 * @description
 */
@Service
public class PersonService {
    @Resource
    private PersonMapper personMapper;

    public Person select(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(Person person) {
        try {
//            int i = 1 / 0;
            personMapper.insert(person);
            System.out.println("事务提交");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("事务回滚");
            throw e;
        }
    }
}
