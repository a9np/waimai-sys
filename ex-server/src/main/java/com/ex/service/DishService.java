package com.ex.service;

import com.ex.dto.DishPDTO;
import com.ex.dto.DishPageQueryDTO;
import com.ex.result.PageResult;

import java.util.List;

public interface DishService {

    public void addDish(DishPDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);
}
