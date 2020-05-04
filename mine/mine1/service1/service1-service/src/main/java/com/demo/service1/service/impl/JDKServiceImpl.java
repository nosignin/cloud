package com.demo.service1.service.impl;

import com.demo.service1.service.JDKService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/04/12:21
 */
@Service
public class JDKServiceImpl implements JDKService {
    @Override
    public void thenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        System.out.println(future.thenAcceptBoth(CompletableFuture.supplyAsync(()->{
            return 20;
        }),(x,y)->System.out.println(x+y)).get());
    }
}
