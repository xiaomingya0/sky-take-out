package com.sky.config;


/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/11 22:55
 */

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "oss.qiniu")
public class QiNiuYunConfig {


    @Value("${oss.qiniu.host")
    private String host;

    @Value("${oss.qiniu.accessKey")
    private String accessKey;

    @Value("${oss.qiniu.secretKey")
    private String secretKey;

    @Value("${oss.qiniu.bucketName")
    private String bucketName;



}



