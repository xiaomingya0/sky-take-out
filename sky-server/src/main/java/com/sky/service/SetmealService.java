package com.sky.service;

import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/23 18:56
 */
public interface SetmealService {

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
