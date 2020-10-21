package com.example.springdemo.service;

import com.example.springdemo.mapper.UserMapper;
import com.example.springdemo.model.po.UserPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public UserPo getUser(String name){
        return userMapper.selectByName(name);
    }

    public int insertUser(String name,String password){
        return userMapper.insert(new UserPo(name,password));
    }

}
