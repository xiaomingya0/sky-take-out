package com.sky.controller.admin;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/11 20:43
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
/**
 * 通用接口
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {

//    @Autowired
//    private CommonService commonService;

    @Value("${file.upload.dir}")
    private String uploadDir;

//    private static final String UPLOAD_DIR = "G:\\sky-take-out\\img";

//    @Value("${baseUploadUrl}")
//    private String url;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "单个图片上传")
    public String uploadImg(@RequestParam(value = "file") MultipartFile file) {
       if (file.isEmpty()){
           return "请选择需要上传的文件";
       }
       try{
           //获取上传文件原始名称
           String fileName = file.getOriginalFilename();
           //构造目标文件路径
           String filePath = uploadDir + File.separator +fileName;
           File dest = new File(filePath);
           //保存文件
           file.transferTo(dest);
           return "文件上传成功";
       } catch (IOException e) {
           e.printStackTrace();
           return "文件上传失败" + e.getMessage();
       }

    }

}
