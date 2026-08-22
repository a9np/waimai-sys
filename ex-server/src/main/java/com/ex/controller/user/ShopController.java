package com.ex.controller.user;

import com.ex.exception.BaseException;
import com.ex.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@Slf4j
@Api(tags = "设置营业状态")
@RequestMapping("/user/shop")
public class ShopController {

    private final String KEY = "shop_status";

    @Autowired
    private RedisTemplate redisTemplate;

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
