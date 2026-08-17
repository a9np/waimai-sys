package com.ex.mapper;

import com.ex.annotation.AutoFill;
import com.ex.entity.Dish;
import com.ex.entity.DishFlavor;
import com.ex.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void addFlavors(List<DishFlavor> dishflavors);
}
