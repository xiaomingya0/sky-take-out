package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/29 23:59
 */@Mapper
public interface UserMapper {
    /**
     * 根据openid来查询用户
     * @param openid
     * @return
     */
    @Select("select  * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param user
     */
    void insert(User user);
}
