package com.example.study.test.service;

import com.example.study.jwt.util.JWTUtils;
import com.example.study.test.entity.Person;
import com.example.study.test.mapper.PersonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

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

    public Map<String, String> createJWTByPersonInfo(Person person) {
        person = personMapper.selectByPrimaryKey(person.getId());
        if (ObjectUtils.isEmpty(person)) {
            return null;
        }
        return JWTUtils.getTokens(person);
    }
}
