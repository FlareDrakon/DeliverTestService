package com.web.app.rest.controller;

import com.web.app.rest.controller.dto.DeliverListRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface DeliverControllerMeta {
    @GetMapping("/deliver/list")
    Object list(HttpServletRequest httpServletRequest, @RequestBody @Valid DeliverListRequest deliverListRequest);

    @GetMapping("/deliver/details")
    Object details(HttpServletRequest httpServletRequest);

    @PutMapping("/deliver/cancel")
    Object cancel(HttpServletRequest httpServletRequest);

    @PostMapping("/deliver/manage")
    Object manage(HttpServletRequest httpServletRequest);

    @PutMapping("/deliver/create")
    @ApiOperation("return id of created or exists order")
    Long create(HttpServletRequest httpServletRequest);

}
