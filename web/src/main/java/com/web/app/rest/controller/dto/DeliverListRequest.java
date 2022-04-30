package com.web.app.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeliverListRequest {

    @NotNull
    private Integer page;
}
