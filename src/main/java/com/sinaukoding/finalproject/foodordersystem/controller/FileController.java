package com.sinaukoding.finalproject.foodordersystem.controller;

import com.sinaukoding.finalproject.foodordersystem.model.enums.TipeUpload;
import com.sinaukoding.finalproject.foodordersystem.model.response.BaseResponse;
import com.sinaukoding.finalproject.foodordersystem.service.app.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping(path = "upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public BaseResponse<?> uploadFile(@RequestPart MultipartFile file,
                                      @RequestParam TipeUpload tipeUpload) {
        return fileService.upload(file, tipeUpload);
    }

    @GetMapping("view")
    public void viewFile(@RequestParam String pathFile, HttpServletResponse response) throws IOException {
        Resource resource = fileService.loadFileAsResource(pathFile);
        IOUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

}
