package com.web.app.rest.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdminRegisterRequest extends RegisterRequest {

    @NotNull
    private UserType userType;

    @NotEmpty
    @Length(max = 255)
    private String name;

    @NotEmpty
    @Length(max = 255)
    private String password;
}
