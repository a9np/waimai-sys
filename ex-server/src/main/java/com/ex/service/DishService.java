package com.ex.service;

import com.ex.dto.DishDTO;
import com.ex.dto.DishPageQueryDTO;
import com.ex.entity.Dish;
import com.ex.result.PageResult;
import com.ex.vo.DishVO;

import java.util.List;

public interface DishService {

    public void addDish(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    void setStatus(Long id, Integer status);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
}
