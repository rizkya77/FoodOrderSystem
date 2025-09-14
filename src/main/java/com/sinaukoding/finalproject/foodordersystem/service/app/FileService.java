package com.sinaukoding.finalproject.foodordersystem.service.app;

import com.sinaukoding.finalproject.foodordersystem.model.enums.TipeUpload;
import com.sinaukoding.finalproject.foodordersystem.model.response.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    BaseResponse<?> upload(MultipartFile files, TipeUpload tipeUpload);

    Resource loadFileAsResource(String pathFile);

}