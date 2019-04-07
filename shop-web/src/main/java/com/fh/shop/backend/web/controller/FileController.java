package com.fh.shop.backend.web.controller;


import com.fh.core.common.ServerResponse;
import com.fh.core.util.COSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController extends BaseController{

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public ServerResponse upload(@RequestParam MultipartFile uploadFileInfo) {
        InputStream inputStream = null;
        try {
            inputStream = uploadFileInfo.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = uploadFileInfo.getOriginalFilename();
        String uploadFilePath = COSUtil.upload(inputStream, filename);
//        String uploadedFileName = FileUtil.copyFile(inputStream, filename, getRootPath(request) + SystemConstant.IMAGE_UPLOAD_PATH);
        return ServerResponse.success(uploadFilePath);
    }
}
