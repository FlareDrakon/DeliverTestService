package com.web.app.service;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class DeliverOrderProcessor {

       /*@Autowired
    private RedisTemplate sharedCacheClient;*/
       ExecutorService workers = Executors.newFixedThreadPool(10);

    @KafkaListener(topics = "topicName", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
        try {
            //todo refactoring for project reactor
            Future<?> submit = workers.submit(() -> {
                    /*
            some logic and db store
            //sendAck()
            sharedCacheClient.removeLock("lock_"message.getOrderId());
         */
            });
            submit.get();//TODO: timeout
        }catch (Exception e) {
            /* send nack */
        }
    }
}
