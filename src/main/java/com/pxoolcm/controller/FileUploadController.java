package com.pxoolcm.controller;

import com.pxoolcm.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/10 15:12
 */
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //把文件存储到本地磁盘上
        file.transferTo(new File("C:\\Users\\春花\\Desktop\\files\\"+filename));
        return Result.success("url地址");
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.controller
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-10  15:12
 * @Description: TODO
 * @Version: 1.0
 */
