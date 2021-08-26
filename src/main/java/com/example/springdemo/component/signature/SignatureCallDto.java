package com.example.springdemo.component.signature;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author dongyu.ye
 **/
@Data
public class SignatureCallDto<T, R> implements Serializable {

    private static final long serialVersionUID = 2233337693586900025L;

    private Class<T> beanClass;

    private String beanName;

    private MethodType methodType;

    private String methodName;

    private Class<R> returnType;

    private Object[] paramArray;

    private List<Object> paramList;

    private Class[] paramClassTypes;

    public enum MethodType {
        /**
         * 虚方法
         */
        VIRTUAL;
    }
}
