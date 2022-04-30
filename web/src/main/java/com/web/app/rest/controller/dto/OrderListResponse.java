package com.web.app.rest.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class OrderListResponse {
    private final List<OrderDto> ordersPage;

}
