package com.sky.result;

import com.sky.utils.ResultInfo;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/16 22:25
 */
public class ResultInfoUtil {

    public static ResultInfo buildSuccess(Object data){
        return new ResultInfo(ResultInfo.SUCCESS_CODE,"操作成功",data);
    }

    public static ResultInfo buildError(String message){
        return new ResultInfo(ResultInfo.ERROR_CODE,message,null);
    }
}
