//package com.sky.service.impl;
//
//import com.qiniu.storage.UploadManager;
//import com.qiniu.util.Auth;
//import com.qiniu.util.StringMap;
//import com.sky.service.CommonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Map;
//
//
///**
// * 功能
// * 作者： 小茗
// * 日期：2024/4/12 0:38
// */
//@Service
//public class CommonServiceImpl implements CommonService {
//    @Autowired
//    private UploadManager uploadManager;
//
//    @Autowired
//    private Auth auth;
//
//    @Value("${qiniu.bucket}")
//    private String bucket;
//
//    private StringMap putPolicy;
//
//    @Override
//    public Map<String, Object> uploadFile(MultipartFile file) throws IOException {
//        // 在这里编写将 MultipartFile 转换为 File 的逻辑
//        // 例如，可以将 MultipartFile 写入临时文件，然后获取文件对象进行上传
//        File convertedFile = convertMultipartFileToFile(file);
//
//        // 调用真正的上传方法，这里假设已经实现了上传逻辑
//        Map<String, Object> response = uploadToQiniu(convertedFile);
//
//        // 返回上传结果
//        return response;
//    }
//
//    // 这是一个示例的 MultipartFile 转换为 File 的方法，您需要根据实际需求进行实现
//    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
//        File file = new File(multipartFile.getOriginalFilename());
//        multipartFile.transferTo(file);
//        return file;
//    }
//
//    // 这是一个示例的上传到七牛云的方法，您需要根据实际情况进行实现
//    private Map<String, Object> uploadToQiniu(File file) {
//        // 在这里编写上传到七牛云的逻辑
//        // 返回上传结果
//        return null;
//    }
//
//    private String getUploadToken(){
//        return this.auth.uploadToken(bucket,null,3600,putPolicy);
//    }
//
//}
