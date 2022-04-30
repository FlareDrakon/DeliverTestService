package com.web.app.rest.controller;

import com.web.app.rest.controller.dto.RegisterRequest;
import com.web.app.rest.controller.dto.UserType;
import com.web.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestController
@Slf4j
public class UserController implements UserControllerMeta {

    @Autowired
    private UserService userService;

    @Override
    @ResponseStatus(value = HttpStatus.OK)
    public void register(HttpServletRequest httpServletRequest, @RequestBody @Valid RegisterRequest request) {
        if(request.getUserType() == UserType.ADMIN) {
            throw new IllegalArgumentException("no rights");
        }
        try {
            userService.registerUser(request.getUserType(), request.getName(), request.getPassword());
        } catch (Exception e) {
            log.error("some error on duplicate user", e);
            throw new IllegalArgumentException("wrong username");
        }
    }

    @Override
    @ResponseStatus(value = HttpStatus.OK)
    public void add(HttpServletRequest httpServletRequest, @RequestBody @Valid RegisterRequest request) {
        try {
            userService.registerUser(request.getUserType(), request.getName(), request.getPassword());
        } catch (Exception e) {
            log.error("some error on duplicate user", e);
            throw new IllegalArgumentException("wrong username");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
