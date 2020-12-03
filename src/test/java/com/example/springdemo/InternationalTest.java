package com.example.springdemo;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;

/**
 * date: 2020/12/3 10:28 description: 测试国际化文件是否能够正常使用
 *
 * @author dongyu.ye
 * @since 3.0.0
 */
public class InternationalTest {

    @Test
    public void process(){
        Locale localeEn = new Locale("en", "US");
        Locale localeZh = new Locale("zh", "CN");
        ResourceBundle rbEn = ResourceBundle.getBundle("messages", localeEn);
        ResourceBundle rbZh = ResourceBundle.getBundle("messages", localeZh);
        System.out.println("en-US:" + rbEn.getString("helloWorld"));
        System.out.println("en-US:" + MessageFormat.format(rbEn.getString("time"), "08:00"));
        System.out.println("zh-CN：" + rbZh.getString("helloWorld"));
        System.out.println("zh-CN：" + MessageFormat.format(rbZh.getString("time"), "08:00"));
    }

}
