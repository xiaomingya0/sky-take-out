package com.sky.utils;

import java.util.UUID;


/**
 * 七牛云生成唯一图片名的工具类
 * 作者： 小茗
 * 日期：2024/4/16 21:17
 */
public class StringUtil {

    public static String getRandomImgName(String fileName){

        //获取文件后缀名
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);

        //检验文件
        if (".jpg".equals(suffix) || ".jpeg".equals(suffix) || ".png".equals(suffix)){

            //改变上传到服务器的文件名 uuid + suffix
            //生成UUID
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String path = uuid + suffix;
            return path;
        }else {
            throw new IllegalArgumentException();
        }
    }
}
