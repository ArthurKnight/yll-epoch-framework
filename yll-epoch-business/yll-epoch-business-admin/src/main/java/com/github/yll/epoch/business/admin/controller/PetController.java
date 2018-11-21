package com.github.yll.epoch.business.admin.controller;

import com.github.yll.epoch.business.admin.model.Pet;
import com.github.yll.epoch.business.admin.model.User;
import com.github.yll.epoch.business.admin.service.UserService;
import com.github.yll.epoch.core.commons.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * @author luliang_yu
 * @date 2018-11-07
 */

@RestController
@RequestMapping(value = "/pets")
@Api(value = "/pets", tags = "Pets", description = "Operations about pets")
public class PetController {

    private static final Logger LOGGER= LoggerFactory.getLogger(PetController.class);

    @RequestMapping(method = RequestMethod.GET,value="/{id}")
    @ApiOperation(value = "Add a new pet to the store")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input")})
    @ResponseBody
    public Pet getPet(
            @PathVariable int id) {
        LOGGER.info("查询某个用户信息");
        Pet pet = new Pet(1,"小A");
        return pet;
    }

    @RequestMapping(method = RequestMethod.GET,value="/{id}/a")
    @ApiOperation(value = "查询某个")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input")})
    @ResponseBody
    public Pet hi(
            @PathVariable int id) {
        LOGGER.info("查询某个用户信息");
        Pet pet = new Pet(1,"小A");
        return pet;
    }


    @Autowired
    UserService userService;

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询")
    public Result get(Long id, String name) {
        User user = userService.getUserById(id);
        return Result.createSuccessResult().setData(user);
    }


}
