package com.web.app.rest.controller;

import com.web.app.dao.UserRepository;
import com.web.app.dao.entity.UserEntity;
import com.web.app.rest.controller.dto.CourierListResponse;
import com.web.app.rest.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourierController implements CourierControllerMeta {

    @Autowired
    private UserRepository userRepository;

    @Override
    public DeferredResult<ResponseEntity<CourierListResponse>> list() {
        DeferredResult<ResponseEntity<CourierListResponse>> courierListResponseDeferredResult = new DeferredResult<>(1000L, () -> {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        });
        //no service no logic so here is just DeferredResult but limits? sort order?
        List<UserEntity> roleCourier = userRepository.findByRole("ROLE_COURIER");
        CourierListResponse courierListResponse = new CourierListResponse(roleCourier.stream().map(userEntity -> {
            UserDto userDto = new UserDto();
            userDto.setName(userEntity.getUsername());
            return userDto;
        }).collect(Collectors.toList()));
        courierListResponseDeferredResult.setResult(new ResponseEntity<>(courierListResponse, HttpStatus.OK));
        return courierListResponseDeferredResult;
    }
}
