package com.example.springdemo.controller;

import com.example.springdemo.conf.RabbitSource;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bring3D Customer<p> 代码描述<p> Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 * Company: 白令三维技术部<p>
 *
 * @author dongyu.ye
 * @since 2021/8/12 1:08 PM
 **/
@RestController
@RequestMapping("/config")
public class ConfigTestController {

    @Resource(name = "testRabbitSource")
    private RabbitSource testRabbitSource;

    @Resource(name = "devRabbitSource")
    private RabbitSource devRabbitSource;

    @GetMapping(path = "/get/{type}")
    public RabbitSource getRabbitSource(@PathVariable("type") String type) {
        if ("dev".equals(type)) {
            return devRabbitSource;
        }
        return testRabbitSource;
    }
}
