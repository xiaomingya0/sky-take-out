package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/17 18:34
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @return
     */

    //select setmeal id from setmeal dish where dish id in(1,2,3,4)
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
