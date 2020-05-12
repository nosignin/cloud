package com.demo.service1.controller;

import com.demo.service1.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/12/10:19
 */
@RestController
@RequestMapping("/autowired")
public class AutowiredController {
    /**
     * 利用autowire可用于自动注入对应的实现类，key是类名的驼峰，值是对应的对象
     * 调用接口后，会打印map里面的内容如下：
    *       Person:studentImpl, com.demo.service1.service.impl.StudentImpl@34280ee3
     *      Person:teacherImpl, com.demo.service1.service.impl.TeacherImpl@35074b10
     * 相当于实现了工厂模式的自动注册，在代码设计中可用于实现类的注册和提取，代码更加优雅
    * @author 石佳
    * @since 2020/5/12
    */
    @Autowired
    private Map<String, IPersonService> personMap;

    @GetMapping("/test")
    public void testAutowired(){
        System.out.println("\nprint map:");
        for (Map.Entry<String, IPersonService> entry : personMap.entrySet()) {
            System.out.println("Person:" + entry.getKey() + ", " + entry.getValue());
        }
    }
}
