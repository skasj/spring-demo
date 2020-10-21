package com.example.springdemo.conf;

import com.example.springdemo.model.dto.User;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
public class Config {


    public List<User> userList;

    @Bean("User")
//    @Scope("prototype")
    public User getUser(Config config){
        return new User();
    }

    @Bean
//    @Scope("prototype")  // prototype 每次出入都会新生成一个bean
    public Config getConfig(){
        return new Config();
    }
    Config(){

    }
}
