package com.web.app.rest.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("jps")
public class JpsController {


    @GetMapping("coords")
    @ApiOperation("return json body with gps coords of deliver")
    public Object getCoordsByCoirierId(@RequestParam("courierId") Long id) {
        //just call repository for get device id of courier
        //call to google map integration
    }
}
