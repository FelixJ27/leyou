package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Felix
 * @Description 文件上传service
 * 面向对象面向卿，不负代码不负君
 */
@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {

    //private static final List<String> ALLOW_TYPE = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/bmp");

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private UploadProperties uploadProperties;
    /**
     * @description: 上传图片
     * @auther: Felix
     */
    @Override
    public String uploadImage(MultipartFile file) {
        try {
            //检验文件类型
            String contentType = file.getContentType();
            if (!uploadProperties.getAllowTypes().contains(contentType)) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //检验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (null == image) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
           /* //目标路径
            File dest = new File("D:/UploadFile", file.getOriginalFilename());
            //保存文件
            file.transferTo(dest);*/
            //上传到fdfs
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            //返回路径
            return uploadProperties.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_FAIL);
        }
    }
}
