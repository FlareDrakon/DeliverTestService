package com.web.app.rest.controller;

import com.web.app.rest.controller.dto.CourierListResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.async.DeferredResult;

public interface CourierControllerMeta {
    @ApiOperation(
            value = "Return list of Couriers sorted by created date fro new one to old",
            notes =
                    "Common notes: \n" +
                            "- method for Admin list couriers"
    )
    @ApiResponses({
            @ApiResponse(code = 200, response = CourierListResponse.class, message = "Successful response."),
            @ApiResponse(code = 401, message = "- `no.session`: session is expired, login again \n")
    })
    @GetMapping(value = "/courier/list", produces = "application/json")
    DeferredResult<ResponseEntity<CourierListResponse>> list();
}
