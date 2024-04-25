package com.sky.controller.admin;

import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/23 18:53
 */
@RestController
@RequestMapping("admin/semteal")
@Slf4j
@Api(value = "套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService semtealService;

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    public Result<PageResult> Page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("套餐分页查询： {}",setmealPageQueryDTO);
        PageResult pageResult = semtealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }
}
