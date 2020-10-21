package com.example.springdemo.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class MyImportConfig {

    @Value("${MyImportConfig.importValue}")
    private String importValue;

    @Override
    public String toString() {
        return "MyImportConfig{" + "importValue='" + importValue + '\'' + '}';
    }
}
