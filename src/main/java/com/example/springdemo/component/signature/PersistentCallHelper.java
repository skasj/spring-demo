package com.example.springdemo.component.signature;

import com.example.springdemo.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
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
            Class<?>[] objects = Arrays.stream(signatureCallDto.getParamClassTypes())
                .map(JavaType::getRawClass).toArray(Class[]::new);
            MethodType mt = MethodType.methodType(signatureCallDto.getReturnType(), objects);
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

    public static void main(String[] args)
        throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        List<UserDto> list = new ArrayList<>();
        UserDto saleTrackItemDto1 = UserDto.builder()
            .id(1)
            .passWord("agent")
            .build();
        UserDto saleTrackItemDto2 = UserDto.builder()
            .id(2)
            .passWord("agent2")
            .build();
        list.add(saleTrackItemDto1);
        list.add(saleTrackItemDto2);
        SignatureCallDto<PersistentCallHelper, Integer> signatureCallDto = new SignatureCallDto<>();
        signatureCallDto.setBeanClass(PersistentCallHelper.class);
        signatureCallDto.setBeanName("userService");
        signatureCallDto.setMethodName("out");
        signatureCallDto.setMethodType(SignatureCallDto.MethodType.VIRTUAL);
        CollectionType collectionType = objectMapper.getTypeFactory()
            .constructCollectionType(List.class, UserDto.class);
        signatureCallDto.setParamClassTypes(new JavaType[]{collectionType});
        signatureCallDto.setParamArray(new Object[]{list});
        signatureCallDto.setReturnType(Integer.class);
        String msg = objectMapper.writeValueAsString(signatureCallDto);
        System.out.println(msg);

        SignatureCallDto scccc = objectMapper.readValue(msg, SignatureCallDto.class);
        PersistentCallHelper persistentCallHelper = new PersistentCallHelper();

        // 创建Lookup对象
        MethodHandles.Lookup l = MethodHandles.lookup();
        // 创建方法句柄类型
        Class<?>[] objects = Arrays.stream(scccc.getParamClassTypes())
            .map(JavaType::getRawClass).toArray(Class[]::new);
        MethodType mt = MethodType.methodType(scccc.getReturnType(), objects);
        // 创建方法句柄，绑定具体的类
        MethodHandle mh = l
            .findVirtual(scccc.getBeanClass(), scccc.getMethodName(), mt);
        // 通过方法句柄调用方法，绑定目标对象及传入参数
        List<Object> arguments = new ArrayList<>();
        arguments.add(persistentCallHelper);
        arguments.addAll(Arrays.asList(scccc.getParamArray()));
        Object invoke = mh.invokeWithArguments(arguments);
        System.out.println(invoke);
    }

    @SneakyThrows
    public Integer out(List<UserDto> userDtos) {
        ObjectMapper test = new ObjectMapper();
        System.out.println(test.writeValueAsString(userDtos));
        return 354;
    }
}
