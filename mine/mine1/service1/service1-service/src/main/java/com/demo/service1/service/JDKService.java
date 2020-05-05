package com.demo.service1.service;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/04/12:21
 */
public interface JDKService {
    void thenAcceptBoth() throws ExecutionException, InterruptedException;
}
