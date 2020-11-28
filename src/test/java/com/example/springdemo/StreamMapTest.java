package com.example.springdemo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class StreamMapTest {

    @Data
    @AllArgsConstructor
    public static class Teacher{
        String name;
        Integer age;
    }

    public static void main(String[] args) {
        normalTest();
        duplicateKeyTest();
        nullPointTest();
    }

    private static void normalTest() {
        List<Teacher> teacherList1 = Arrays.asList(new Teacher("李华", 34),
                                                   new Teacher("小王", 26),
                                                   new Teacher("小红", 23));
        Map<String, Integer> teacherNameAgeMap = teacherList1.stream()
                                                             .collect(Collectors.toMap(Teacher::getName, Teacher::getAge));
        System.out.println("key不相同"+teacherNameAgeMap);
        System.out.println();
    }

    private static void duplicateKeyTest() {
        List<Teacher> teacherList1 = Arrays.asList(new Teacher("李华", 34),
                                                   new Teacher("小红", 32),
                                                   new Teacher("小王", 26),
                                                   new Teacher("小红", 23));
        // key相同，取前者
        Map<String, Integer> teacherNameAgeMap1 =
                teacherList1.stream().collect(Collectors.toMap(Teacher::getName, Teacher::getAge,(k1,k2)->k1));
        // key相同，取后者
        Map<String, Integer> teacherNameAgeMap2 =
                teacherList1.stream().collect(Collectors.toMap(Teacher::getName, Teacher::getAge,(k1,k2)->k2));
        // key相同，用list存储value
        Map<String, List<Integer>> teacherNameAgeMap3 =
            teacherList1.stream().collect(
                Collectors.toMap(Teacher::getName,
                    o->{
                        List<Integer> ages = new ArrayList<>();
                        ages.add(o.getAge());
                        return ages;
                    },
                    (v1,v2)->{
                        v1.addAll(v2);
                    return v1;
                }));
        System.out.println("key相同，取前者："+teacherNameAgeMap1);
        System.out.println("key相同，取后者："+teacherNameAgeMap2);
        System.out.println("key相同，用list存储value："+teacherNameAgeMap3);
        System.out.println();
    }

    private static void nullPointTest() {
        List<Teacher> teacherList2 = Arrays.asList(new Teacher("李华",34),
                                                   new Teacher("小红",32),
                                                   new Teacher("小王",26),
                                                   new Teacher("赵松",null),
                                                   new Teacher(null,23));
        // HashMap 的value不能为null,所以需要把null值用其他值替代
        Map<String, Integer> teacherNameAgeMap =
                teacherList2.stream().collect(Collectors.toMap(Teacher::getName, p-> Objects.isNull(p.getAge()) ? -1:p.getAge()));
        System.out.println("value为null，用-1代替null："+teacherNameAgeMap);
        System.out.println();
    }
}
