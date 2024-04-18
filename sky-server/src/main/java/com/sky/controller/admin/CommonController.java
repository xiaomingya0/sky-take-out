package com.sky.controller.admin;

/**
 * 功能
 * 作者： 小茗
 * 日期：2024/4/11 20:43
 */

import com.sky.result.ResultInfoUtil;
import com.sky.service.CommonService;
import com.sky.utils.ResultInfo;
import com.sky.vo.QiniuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 * 通用接口
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private CommonService commonService;

//    @Value("${file.upload.dir}")
//    private String uploadDir;

//    private static final String UPLOAD_DIR = "G:\\sky-take-out\\img";

//    @Value("${baseUploadUrl}")
//    private String url;


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "单个图片上传")
    public ResultInfo uploadImg(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultInfoUtil.buildError("上传失败：文件为空");
            }

            String path = commonService.uploadQNImg(file);
            if (path.equals("失败")) {
                return ResultInfoUtil.buildError("上传失败");
            } else {
                System.out.println("七牛云返回的图片链接是：" + path);
                QiniuVo qiniuVo = new QiniuVo();
                qiniuVo.setPath(path);
                return ResultInfoUtil.buildSuccess(qiniuVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfoUtil.buildError("上传失败：" + e.getMessage());
        }
    }


}
