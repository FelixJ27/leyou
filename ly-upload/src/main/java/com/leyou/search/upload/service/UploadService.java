package com.leyou.search.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Felix
 * @Description
 * 面向对象面向卿，不负代码不负君
 */
public interface UploadService {
    String uploadImage(MultipartFile file);
}
