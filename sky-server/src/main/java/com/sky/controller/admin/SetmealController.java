package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/23 18:53
 */
@RestController
@RequestMapping("admin/setmeal")
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

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        semtealService.sabeWithDish(setmealDTO);
        return Result.success();
    }


    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result deleteBatch(@RequestParam List<Long> ids){

        try{
            semtealService.deleteBatch(ids);
            return Result.success();
        }catch (Exception e){
            System.out.println(e);
            return Result.error("删除失败");
        }

    }

    /**
     * 根据id查询套餐,用于修改页面回显数据
     * @param id
     * @return
     */
    @GetMapping
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealVO setmealVO = semtealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        semtealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售停售")
    public Result startOrStop(@PathVariable Integer status, Long id){
        try {
            semtealService.startOrStop(status, id);
            return Result.success();
        }catch (Exception e){
            System.out.println(e);
            return Result.success("出错了");

        }
    }
}

