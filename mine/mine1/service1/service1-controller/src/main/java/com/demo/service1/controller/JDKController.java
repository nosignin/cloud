package com.demo.service1.controller;

import com.demo.service1.service.JDKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/04/12:20
 */
@RestController
@RequestMapping("/jdk")
public class JDKController {
    @Autowired
    private JDKService jdkService;

    @GetMapping("/then/accept/both")
    public void thenAcceptBoth() throws ExecutionException, InterruptedException {
        jdkService.thenAcceptBoth();
    }
}
