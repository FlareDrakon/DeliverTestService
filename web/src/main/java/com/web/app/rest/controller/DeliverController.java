package com.web.app.rest.controller;

import com.web.app.rest.controller.dto.CourierListResponse;
import com.web.app.rest.controller.dto.DeliverListRequest;
import com.web.app.rest.controller.dto.OrderDto;
import com.web.app.rest.controller.dto.OrderListResponse;
import com.web.app.service.DeliverOrderService;
import com.web.app.service.DeliverService;
import com.web.app.service.UserPrincipal;
import com.web.app.service.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DeliverController implements DeliverControllerMeta {

    @Autowired
    private DeliverService deliverService;

    @Autowired
    private DeliverOrderService deliverOrderService;

    @Override
    public DeferredResult<ResponseEntity<OrderListResponse>> list(HttpServletRequest httpServletRequest, @RequestBody @Valid DeliverListRequest deliverListRequest)  {
        DeferredResult<ResponseEntity<OrderListResponse>> courierListResponseDeferredResult = new DeferredResult<>(10000L, () ->
             new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        );
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Order> orderList = new ArrayList<>();
        if (auth != null) {
            boolean admin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                if(admin) {
                    orderList = deliverService.getAllOrdersList(deliverListRequest.getPage());
                } else if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                    orderList = deliverService.getMyOrderList(deliverListRequest.getPage(), ((UserPrincipal)auth.getPrincipal()).getUsername());
                } else if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_COURIER"))) {
                    orderList = deliverService.getListOrdersByAssign(deliverListRequest.getPage(), ((UserPrincipal)auth.getPrincipal()).getUsername());
                }
            }
        courierListResponseDeferredResult.setResult(new ResponseEntity<>(new OrderListResponse(orderList.stream().map(order ->
             new OrderDto()
        ).collect(Collectors.toList())), HttpStatus.OK));
        return courierListResponseDeferredResult;
    }

    @Override
    public Object details(HttpServletRequest httpServletRequest)  {
        //just call repository with details using orderService


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean admin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(admin) {
            //check order status in progress? so call locator

            //just call deliverOrderService.getWithCoords that call restTemplate for locator
        }
        return null;
    }

    @Override
    public Object cancel(HttpServletRequest httpServletRequest)  {
        deliverOrderService.updateStatus("REJECTED", httpServletRequest.getParameter("orderId"));
        return null;
    }

    @Override
    public Object manage(HttpServletRequest httpServletRequest)  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            boolean admin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            if(admin) {
                //just call repository using orderService for update view for courier
            } else if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_COURIER"))) {
                deliverOrderService.updateStatus(httpServletRequest.getParameter("status"), httpServletRequest.getParameter("orderId"));
            } else {
                //just call repository using orderService for update order data in details
            }
        }
        return null;
    }

    @Override
    public Long create(HttpServletRequest httpServletRequest) {
        //validateOrderData()
        //just call repository using orderService for carete/update order data in details
        return 0L;
    }
}
