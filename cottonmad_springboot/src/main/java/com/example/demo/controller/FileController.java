package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    // postman网站 测试接口
    // post localhost:9011/files/upload
    // Body form-data
    // key:file
    @PostMapping("/upload")
    public Result<?> upload(@RequestPart("file") MultipartFile file, @RequestParam("userID") Integer userID) throws IOException {
//        String originalFilename = file.getOriginalFilename();
        // 定义文件的唯一标示（前缀）
//        String flag = IdUtil.fastSimpleUUID();
//        String property = System.getProperty("user.dir")
//                + "/cottonmad_springboot/src/main/resources/Files/"
//                + flag + "_" + originalFilename;
        String property = System.getProperty("user.dir")
                + "/cottonmad_springboot/src/main/resources/Files/"
                + userID.toString() + ".jpg" ;
        FileUtil.writeBytes(file.getBytes(), property);

//        return Result.success(ip+":"+port+"/files/"+userID.toString()+".jpg"); // 返回结果url用于下载
        return Result.success(userID.toString()+".jpg"); // 返回结果url用于下载
    }

    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response){
        OutputStream outputStream; // 新建输出流对象
        String basePath = System.getProperty("user.dir") + "/cottonmad_springboot/src/main/resources/Files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String filesFinded = fileNames.stream().filter(name -> name.contentEquals(flag)).findAny().orElse("");
        try {
            if (StrUtil.isAllNotEmpty(filesFinded)){
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(filesFinded, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + filesFinded);
                outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e){
            System.out.println("failed");
        }
    }
}
