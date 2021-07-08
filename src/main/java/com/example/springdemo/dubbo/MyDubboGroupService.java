package com.example.springdemo.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springdemo.api.MyDubboGroup;

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class MyDubboGroupService implements MyDubboGroup {
    @Override
    public String findGroupByName(String name) {
        return "test";
    }
}
