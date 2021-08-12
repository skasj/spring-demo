package com.example.springdemo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bring3D Customer<p> 代码描述<p> Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 * Company: 白令三维技术部<p>
 *
 * @author dongyu.ye
 * @since 2021/8/12 1:15 PM
 **/
@Configuration
public class RabbitConfig {

    @Bean("testRabbitSource")
    @ConfigurationProperties(prefix = "rabbit.source.test")
    public RabbitSource testRabbitSource(){
        return new RabbitSource();
    }

    @Bean("devRabbitSource")
    @ConfigurationProperties(prefix = "rabbit.source.dev")
    public RabbitSource devRabbitSource(){
        return new RabbitSource();
    }
}
