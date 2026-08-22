package com.ex.controller.admin;

import com.ex.exception.BaseException;
import com.ex.result.Result;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@Api(tags = "设置营业状态")
@RequestMapping("/admin/shop")
public class ShopController {

    private final String KEY = "shop_status";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result<String> setStatus(@PathVariable Integer status) {
        log.info("设置营业状态：{}", status);
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus() {
        log.info("获得营业状态");
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        if (shopStatus == null) {
            throw new BaseException("营业状态获取失败");
        }
        log.info("营业状态为：{}", shopStatus == 1 ? "营业中" : "打样中");
        return Result.success(shopStatus);
    }
}
