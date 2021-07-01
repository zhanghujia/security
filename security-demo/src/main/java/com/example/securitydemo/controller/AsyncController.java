package com.example.securitydemo.controller;

import com.example.securitydemo.async.queue.DeferredResultHolder;
import com.example.securitydemo.async.queue.MockQueue;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;


/**
 * @author jia
 */
@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/async")
    @ApiOperation(value = "Callable多线程服务")
    public Callable<String> orderCall() throws InterruptedException {
        log.info("主线程开始......");
        Thread.sleep(1000);
        Callable<String> result = () -> {
            log.info("副线程开始......");
            Thread.sleep(3000);
            log.info("副线程返回......");
            return "Success";
        };
        log.info("主线程结束......");
        return result;
    }

    @GetMapping("/async/order")
    @ApiOperation(value = "模拟下单队列服务")
    public DeferredResult<String> order() throws InterruptedException {

        log.info("主线程开始......");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        log.info("主线程结束......");

        return result;
    }


}
