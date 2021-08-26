package com.example.springdemo.controller;

import com.example.springdemo.annotation.MyAnnotationForAspect;
import com.example.springdemo.model.dto.UserDto;
import com.example.springdemo.model.po.UserPo;
import com.example.springdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

@RestController
@RequestMapping("/mybatis")
public class MyMybatisController implements Serializable {

    @Resource
    private UserService userService;

    @GetMapping("user")
    @ResponseBody
    @MyAnnotationForAspect(name = "获取用户姓名",value = "测试")
    public UserPo getUserByName(@RequestParam String name){
        return userService.getUser("ydy");
    }

    @PostMapping("user")
    @ResponseBody
    public int insertUserByName(@RequestBody UserDto userDto){
        return userService.insertUser(userDto.getUserName(),userDto.getPassWord());
    }
}
