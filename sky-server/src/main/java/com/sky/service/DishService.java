package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/14 21:57
 */
public interface DishService {

    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
