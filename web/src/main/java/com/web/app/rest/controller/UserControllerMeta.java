package com.web.app.rest.controller;

import com.web.app.rest.controller.dto.RegisterRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface UserControllerMeta {
    @PostMapping("/user/register")
    void register(HttpServletRequest httpServletRequest, RegisterRequest request);

    @PostMapping("/user/add")
    void add(HttpServletRequest httpServletRequest, RegisterRequest request);
}
