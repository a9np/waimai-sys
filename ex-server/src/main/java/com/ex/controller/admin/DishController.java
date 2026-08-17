package com.ex.controller.admin;


import com.ex.dto.DishDTO;
import com.ex.result.Result;
import com.ex.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/dish")
@RestController
@Slf4j
@Api("菜品接口")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("添加菜品")
    public Result<String> addDish(@RequestBody DishDTO dishDTO) {

        dishService.addDish(dishDTO);
        return Result.success();
    }
}
