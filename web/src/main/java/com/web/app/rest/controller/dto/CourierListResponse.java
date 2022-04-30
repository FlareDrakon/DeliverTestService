package com.web.app.rest.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CourierListResponse {
    private final List<UserDto> userDtoList;
}
