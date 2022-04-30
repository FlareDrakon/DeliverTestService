package com.web.app.service;

import com.web.app.dao.OrderRepository;
import com.web.app.service.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliverService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrdersList(int page) {
        return orderRepository.findAll(new QPageRequest(1000, page))
                .get()
                .map(orderEntry -> new Order())
                .collect(Collectors.toList());
    }

    public List<Order> getMyOrderList(Integer page, String userName) {
        //stub
        return null;
    }

    public List<Order> getListOrdersByAssign(Integer page, String username) {
        //stub
        return null;
    }
}
