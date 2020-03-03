package com.leyou.upload.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
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
public class UploadServiceImpl implements UploadService {

    private static final List<String> ALLOW_TYPE = Arrays.asList("image/png", "image/jpeg", "image/bmp");

    /**
     * @description: 上传图片
     * @auther: Felix
     */
    @Override
    public String uploadImage(MultipartFile file) {
        try {
            //检验文件类型
            String contentType = file.getContentType();
            if (!ALLOW_TYPE.contains(contentType)) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //检验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (null == image) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //目标路径
            File dest = new File("D:/UploadFile", file.getOriginalFilename());
            //保存文件
            file.transferTo(dest);
            //返回路径
            return "http://" + file.getOriginalFilename();
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_FAIL);
        }
    }
}
