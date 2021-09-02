package com.example.springdemo.zk;

import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoadTest {
    static class MyClassLoad extends URLClassLoader {

        public MyClassLoad(URL[] urls) {
            super(urls);
        }
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }

    public static void main(String[] args)
        throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        URL url = FileURL.makeURL("/Users/xiedongyu/IdeaProjects/spring-demo/src/main/java/java/lang");
        MyClassLoad myClassLoad = new MyClassLoad(new URL[]{url});
        Class<?> string = myClassLoad.loadClass("String");
        Object obj = string.newInstance();
        Method method = string.getDeclaredMethod("print",null);
        //通过反射调用Test类的say方法
        System.out.println(method.invoke(obj, null));
    }
}
