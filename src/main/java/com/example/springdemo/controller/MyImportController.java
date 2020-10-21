package com.example.springdemo.controller;

import com.example.springdemo.conf.MyImportConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
@Import(MyImportConfig.class)
public class MyImportController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    MyImportController(){
        System.out.println("test");
    }

    @GetMapping("/importConfig")
    public Object getImportConfig(){

        Object bean = applicationContext.getBean("com.example.springdemo.conf.MyImportConfig");
        Object user = applicationContext.getBean("User");
        System.out.println(bean.toString());
        System.out.println(user.toString());
        return user;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
