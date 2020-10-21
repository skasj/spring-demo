package com.example.springdemo.handle;

import com.example.springdemo.annotation.MyAnnotationForAspect;
import com.example.springdemo.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 仅用于测试
 */
@Aspect
@Component
public class MyAspect {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @Pointcut("@annotation(myAnnotation)")
    public void serviceStatistics(MyAnnotationForAspect myAnnotation){
        logger.info("测试");
    }

    @Around("serviceStatistics(myAnnotation)")
    public Object process(ProceedingJoinPoint point,MyAnnotationForAspect myAnnotation){
        logger.info(myAnnotation.name() + ":" +myAnnotation.value());
        try {
            logger.info(userService.getUser("ydy").toString());
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "失败";
    }
}
