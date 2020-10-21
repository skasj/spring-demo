package com.example.springdemo.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.beans.ConstructorProperties;

@Data
@NoArgsConstructor
@TableName
public class User {

    @Value("${demo.config.user.name}")
    String name;
    int sex;
    int age;

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", sex=" + sex + ", age=" + age + '}';
    }

    //    @ConstructorProperties("demo.config.user.name")
//    User(String name){
//        this.name = name;
//    }
}
