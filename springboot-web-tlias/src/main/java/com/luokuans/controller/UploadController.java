package com.luokuans.controller;


import com.luokuans.pojo.Result;
import com.luokuans.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    // @PostMapping("/upload")
    // public Result upload(String username, Integer age, MultipartFile image) throws IOException {
    //     log.info("文件上传: {}, {}, {}", username, age, image);
    //     //获取原始文件名
    //     String originalFilename = image.getOriginalFilename();
    //
    //     //构造唯一的文件名 (不能重复) - uuid(通用唯一识别码) de49685b-61c0-4b11-80fa-c71e95924018
    //     //找到最后一个.的位置
    //     int index = originalFilename.lastIndexOf(".");
    //     //截取出来
    //     String extname = originalFilename.substring(index);
    //     String newFileName = UUID.randomUUID().toString() + extname;
    //
    //     image.transferTo(new File("D:\\me\\Java\\Me\\heima\\" + newFileName));
    //     return Result.success();
    // }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传, 文件名: {}", image.getOriginalFilename());

        //调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url: {}", url);

        return Result.success(url);
    }
}
