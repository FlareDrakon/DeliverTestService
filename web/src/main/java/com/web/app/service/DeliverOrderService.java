package com.web.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.service.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeliverOrderService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;//TODO: configure it
   /*@Autowired
    private RedisTemplate sharedCacheClient;*/

    private void sendMessage(String msg) {
        kafkaTemplate.send("topicName", msg);
    }

    public void updateStatus(String status, Long orderId) {

        //sharedCacheClient.put("lock_" + orderId);// wait here
        String msg = status;
        //validate(status)
        //validate(OrderId)
        /*
            if(status == ACCEPTED) {
                packet = new AddOrderMsg(orderId);
            }
             if(status == REJECTED) {
                packet = new RemoveOrder(orderId);
            }
            msg = objectMapper.writeValueAsString(msg)
         */
        sendMessage(msg);
    }
}
