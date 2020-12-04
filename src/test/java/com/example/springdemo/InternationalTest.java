package com.example.springdemo;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * date: 2020/12/3 10:28 description: 测试国际化文件是否能够正常使用
 *
 * @author dongyu.ye
 * @since 3.0.0
 */
@SpringBootTest(classes = SpringDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class InternationalTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void process(){
        Locale localeEn = new Locale("en", "US");
        Locale localeZh = new Locale("zh", "CN");
        ResourceBundle rbEn = ResourceBundle.getBundle("i18n/messages", localeEn);
        ResourceBundle rbZh = ResourceBundle.getBundle("i18n/messages", localeZh);
        System.out.println("en-US:" + rbEn.getString("helloWorld"));
        System.out.println("en-US:" + MessageFormat.format(rbEn.getString("time"), "08:00"));
        System.out.println("zh-CN：" + rbZh.getString("helloWorld"));
        System.out.println("zh-CN：" + MessageFormat.format(rbZh.getString("time"), "08:00"));
    }

    @Test
    public void messageSourceTest(){
        Locale localeEn = new Locale("en", "US");
        Locale localeZh = new Locale("zh", "CN");
        System.out.println("en-US:" + messageSource.getMessage("helloWorld",null,localeZh));
        System.out.println("en-US:" + MessageFormat.format(messageSource.getMessage("time",null,localeZh), "08:00"));
        System.out.println("zh-CN：" + messageSource.getMessage("helloWorld",null,localeEn));
        System.out.println("zh-CN：" + MessageFormat.format(messageSource.getMessage("time",null,localeEn), "08:00"));
    }
}
