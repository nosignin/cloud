package com.demo.service1.service.impl;

import com.demo.service1.service.IPersonService;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/12/10:22
 */
@Component
public class TeacherImpl implements IPersonService {
    @Override
    public void doWork() {
        System.out.println("I am teaching");
    }
}
