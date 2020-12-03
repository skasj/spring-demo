package com.example.springdemo.service;

import com.example.springdemo.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BeanPrototypeTestService {

    @Qualifier("User")
    @Autowired
    public User user;

    public void printUser(){
        System.out.println(user.getName());
    }

}