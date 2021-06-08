package com.leyou.update;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author Felix
 * @Description 测试fastDFS类
 * 面向对象面向卿，不负代码不负君
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FdfsTest {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("D:/testUpload/1.jpg");
        //上传并生成缩略图
        StorePath storePath = this.fastFileStorageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpg", null);
        //带分组的路径
        System.out.println(storePath.getFullPath());
        //不带分组的路径
        System.out.println(storePath.getPath());
    }

    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("D:/testUpload/1.jpg");
        //上传并生成缩略图
        StorePath storePath = this.fastFileStorageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "jpg", null);
        //带分组的路径
        System.out.println("带分组的路径" + storePath.getFullPath());
        //不带分组的路径
        System.out.println("不带分组的路径" + storePath.getPath());
        //获取缩略图路径
        String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println("缩略图路径" + thumbImagePath);
    }
}
