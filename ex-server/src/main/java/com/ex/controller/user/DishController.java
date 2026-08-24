package com.ex.controller.user;

import com.ex.constant.StatusConstant;
import com.ex.entity.Dish;
import com.ex.result.Result;
import com.ex.service.DishService;
import com.ex.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        //缓存相关操作
        //查询缓存中是否存在，若存在返回缓存数据
        String key = "dish_" + categoryId;
        List<DishVO> cachedList = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (cachedList != null && cachedList.size() > 0) {
            return Result.success(cachedList);
        }


        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品

        List<DishVO> list = dishService.listWithFlavor(dish);
        //更新缓存
        redisTemplate.opsForValue().set(key, list);

        return Result.success(list);
    }

}
