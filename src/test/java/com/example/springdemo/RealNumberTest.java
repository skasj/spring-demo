package com.example.springdemo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RealNumberTest {

    private static Logger logger = LoggerFactory.getLogger(RealNumberTest.class);

    @Test
    public void testDouble(){
        double a = 1.1234567890123456789d;
        double b = 0.1234567890123456789d;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void testFloat(){
        float a = 1.1234567890123456789f;
        float b = 0.1234567890123456789f;
        System.out.println(a);
        System.out.println(b);
    }
}
