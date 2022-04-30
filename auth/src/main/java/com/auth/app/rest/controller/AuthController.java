package com.auth.app.rest.controller;

import com.auth.app.dao.entity.UserEntity;
import com.auth.app.service.UserPrincipal;
import org.h2.engine.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/auth")
public class AuthController {

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public void checkAuth(HttpServletRequest httpServletRequest) {
        //todo check
    }

    @PostMapping("/auth")
    public Object auth(HttpServletRequest httpServletRequest) {
        //todo auth like web
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(httpServletRequest.getHeader("Auth-header").split(":")[1]);
        userEntity.setUsername(httpServletRequest.getHeader("Auth-header").split(":")[0]);
        return new UserPrincipal(userEntity);
    }
}
