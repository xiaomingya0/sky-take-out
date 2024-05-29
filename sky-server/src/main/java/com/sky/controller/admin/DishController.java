package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/14 21:48
 */

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品: {}" +dishDTO);
        try {
            dishService.saveWithFlavor(dishDTO);
            String key = "dish_" +dishDTO.getCategoryId();
            cleanCache(key);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("page")
    @ApiOperation(value = "菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询 :{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除菜品或批量删除")
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除: {}", ids);
        try {
            dishService.deleteBatch(ids);

            cleanCache("dish_*");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success(ids);
    }

    /**
     * 根据id查询菜品对应的口味数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品: {}",id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 根据id修改菜品数据和对应的口味数据
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation(value = "根据id修改菜品数据")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("根据id来修改菜品数据: {}",dishDTO);
        try {
            dishService.updateWithFlavor(dishDTO);
            cleanCache("dish_*");

        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.success();
    }

    /**
     * 根据分类id查询菜品数据
     * @param categoryId
     * @return
     */
    @GetMapping("/list/{categoryId}")
    @ApiOperation(value = "根据分类id查询菜品")
    public Result<List<Dish>> getClassifyByIdList(@PathVariable("categoryId") Long categoryId) {
        log.info("根据分类id来查询菜品列表: {}", categoryId);
        try {
            List<Dish> dishList = dishService.getClassifyByIdList(categoryId);
            return Result.success(dishList);
        } catch (Exception e) {
            log.error("查询菜品列表时发生异常: {}", e.getMessage(), e);
            System.out.println(e);
            return Result.error(e.getMessage());
            // 或者使用自定义的错误码和消息
        }
    }

    /**
     * 启用、禁用 菜品
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用、禁用 菜品")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        dishService.startOrStop(status,id);

        cleanCache("dish_*");
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * 清理缓存数据
     * @param pattern
     */
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);

    }
}
