package com.example.springdemo.component.signature;

import com.example.springdemo.model.dto.User;
import com.example.springdemo.model.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author dongyu.ye
 **/
@Slf4j
@Component
public class PersistentCallHelper implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public <T, R> R callService(SignatureCallDto<T, R> signatureCallDto) {
        try {
            T targetService = null;
            if (StringUtils.isNotBlank(signatureCallDto.getBeanName())) {
                targetService = applicationContext.getBean(signatureCallDto.getBeanName(),
                    signatureCallDto.getBeanClass());
            } else {
                targetService = applicationContext.getBean(signatureCallDto.getBeanClass());
            }
            // 创建Lookup对象
            MethodHandles.Lookup l = MethodHandles.lookup();
            // 创建方法句柄类型
            MethodType mt = MethodType.methodType(signatureCallDto.getReturnType(),
                signatureCallDto.getParamClassTypes());
            // 创建方法句柄，绑定具体的类
            MethodHandle mh = l
                .findVirtual(signatureCallDto.getBeanClass(), signatureCallDto.getMethodName(), mt);
            // 通过方法句柄调用方法，绑定目标对象及传入参数
            List<Object> arguments = new ArrayList<>();
            arguments.add(targetService);
            arguments.addAll(Arrays.asList(signatureCallDto.getParamArray()));
            Object invoke = mh.invokeWithArguments(arguments);
            System.out.println(invoke);
            return (R) invoke;
        } catch (Throwable throwable) {
            log.error("callService error", throwable);
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) throws JsonProcessingException {
        UserDto userDto = UserDto.builder().id(1).passWord("123456").userName("123").build();
        SignatureCallDto<User, UserDto> chanceRemoteRSignatureCallDto = new SignatureCallDto<>();
        chanceRemoteRSignatureCallDto.setBeanClass(User.class);
        chanceRemoteRSignatureCallDto.setBeanName("chanceRemote");
        chanceRemoteRSignatureCallDto.setMethodName("saveInquiry");
        chanceRemoteRSignatureCallDto.setMethodType(SignatureCallDto.MethodType.VIRTUAL);
        chanceRemoteRSignatureCallDto.setParamClassTypes(new Class[]{UserDto.class});
        List<Object> objectList = new ArrayList<>();
        objectList.add(userDto);
        objectList.add(1);
        chanceRemoteRSignatureCallDto.setParamList(objectList);
        chanceRemoteRSignatureCallDto.setReturnType(UserDto.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String x = objectMapper.writeValueAsString(chanceRemoteRSignatureCallDto);
        System.out.println(x);
        SignatureCallDto signatureCallDto = objectMapper.readValue(x, SignatureCallDto.class);
        List paramList = signatureCallDto.getParamList();
        UserDto userDto1 = objectMapper.convertValue(paramList.get(0), UserDto.class);
        Integer integer = objectMapper.convertValue(paramList.get(1), Integer.class);
        System.out.println(objectMapper.writeValueAsString(userDto1));
        System.out.println(objectMapper.writeValueAsString(integer));
    }
}
