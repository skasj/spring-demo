package com.example.springdemo.controller;

import com.example.springdemo.component.signature.PersistentCallHelper;
import com.example.springdemo.component.signature.SignatureCallDto;
import com.example.springdemo.service.UserService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bring3D Customer<p> 代码描述<p> Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 * Company: 白令三维技术部<p>
 *
 * @author dongyu.ye
 * @since 2021/8/25 9:00 PM
 **/
@RestController("signature")
@RequestMapping("/signature")
public class SignatureCallController {

    @Resource
    private UserService userService;
    @Resource
    private PersistentCallHelper persistentCallHelper;

    @GetMapping("/out/{out}")
    @ResponseBody
    public int out(@PathVariable("out") String out) {
        SignatureCallDto<SignatureCallController, Integer> signatureCallDto = new SignatureCallDto<>();
        signatureCallDto.setBeanClass(SignatureCallController.class);
        signatureCallDto.setBeanName("signature");
        signatureCallDto.setMethodName("test");
        signatureCallDto.setParamClassTypes(new Class[]{String.class});
        signatureCallDto.setParamArray(new String[]{"my"});
        signatureCallDto.setReturnType(Integer.class);
        return persistentCallHelper.callService(signatureCallDto);
    }

    public Integer test(String out) {
        System.out.println(out);
        return Integer.valueOf(12);
    }
}
