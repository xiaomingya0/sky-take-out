package com.sky.utils;

import lombok.Data;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/16 22:18
 */
@Data
public class ResultInfo {

    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    private int code;
    private String message;
    private Object data;

    public ResultInfo(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


}
