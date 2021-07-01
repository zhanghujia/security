package com.example.securitydemo.async.queue;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author jia
 */
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(), 2, TimeUnit.MINUTES, new SynchronousQueue<>(),
                new DefaultThreadFactory("jia"));
        threadPoolExecutor.execute(() -> {
                    while (true) {
                        if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                            String orderNumber = mockQueue.getCompleteOrder();
                            log.info("返回订单结果处理: " + orderNumber);
                            deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                            mockQueue.setCompleteOrder(null);
                        } else {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
}
