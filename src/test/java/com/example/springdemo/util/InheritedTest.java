package com.example.springdemo.util;

import com.example.springdemo.annotation.AnnotationWithInherited;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * date: 2020/11/28 15:40
 *
 * @author dongyu.ye
 * @since 3.0.0
 */
public class InheritedTest {

    @Data
    public static class Father {
        String name;
        @AnnotationWithInherited("father")
        public String print() {
            return name;
        }
    }
    @Data
    public static class Children1 extends Father {
        String childrenName;
    }
    @Data
    public static class Children2 extends Father {
        String childrenName;
        public String print() {
            return name;
        }
    }

    @Test
    public void test() throws NoSuchMethodException {
        Children1 notOverrideMethod = new Children1();
        Children2 overrideMethod = new Children2();
        Assert.assertNotNull(notOverrideMethod.getClass().getMethod("print").getAnnotation(AnnotationWithInherited.class));
        Assert.assertNull(overrideMethod.getClass().getMethod("print").getAnnotation(AnnotationWithInherited.class));
    }
}
