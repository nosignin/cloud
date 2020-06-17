package com.example.redis.clazz;

import com.example.redis.chain.Student;

import java.util.List;

/**
 * 测试java泛型中的T和Class<T>的区别
 */
public class TestClass {
    public static void main(String[] args) {
        TestClass test = new TestClass();
        Object object = new Object();
        List<Student> transfer1 = test.transfer1(object, Student.class);
        List<String> transfer2 = test.transfer2(object,new String());
    }

    /**
     * Class<T> t 表示传入的是一个class模型文件
     * @param object
     * @param t
     * @param <T>
     * @return
     */
    public  <T>  List<T> transfer1(Object object,Class<T> t) {
        List<T> objects = (List<T>) object;
        return objects;
    }

    /**
     * T表示一个具体的对象的实例
     * @param object
     * @param t
     * @param <T>
     * @return
     */
    public  <T>  List<T> transfer2(Object object,T t) {
        List<T> objects = (List<T>) object;
        return objects;
    }
}
