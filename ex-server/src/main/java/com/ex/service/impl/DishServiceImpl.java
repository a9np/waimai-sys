package com.ex.service.impl;

import com.ex.dto.DishDTO;
import com.ex.entity.Dish;
import com.ex.entity.DishFlavor;
import com.ex.mapper.DishFlavorMapper;
import com.ex.mapper.DishMapper;
import com.ex.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDish(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.addDish(dish);
        
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        if (dishFlavors == null || dishFlavors.isEmpty()) {
            return;
        }
        for (DishFlavor dishflavor : dishFlavors) {
            dishflavor.setDishId(dish.getId());
        }
        dishFlavorMapper.addFlavors(dishFlavors);

    }
}
