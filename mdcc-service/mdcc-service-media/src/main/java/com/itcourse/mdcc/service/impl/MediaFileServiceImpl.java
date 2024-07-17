package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.MediaFile;
import com.itcourse.mdcc.mapper.MediaFileMapper;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.service.IMediaFileService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-16
 */
@Slf4j
@Service
public class MediaFileServiceImpl extends ServiceImpl<MediaFileMapper, MediaFile> implements IMediaFileService {

    //上传文件根目录
    @Value("${media.upload.location}")
    private String uploadPath;

    @Autowired
    private MediaFileMapper mediaFileMapper;

    /**
     * 校验文件块是否已经存在了
     */
    @Override
    public JSONResult checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
        //获取块文件文件夹路径
        String chunkfileFolderPath = getChunkFileFolderPath(fileMd5);
        //块文件的文件名称以1,2,3..序号命名，没有扩展名
        File chunkFile = new File(chunkfileFolderPath+chunk);
        if(!chunkFile.exists()){
            return JSONResult.error();
        }
        return JSONResult.success();
    }


    /**
     * 文件上传之前的注册功能
     */
    @Override
    public JSONResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        //检查文件是否上传
        // 1、得到文件的路径
        String filePath = getFilePath(fileMd5, fileExt);
        File file = new File(filePath);

        //2、查询数据库文件是否存在
        MediaFile media = mediaFileMapper.selectById(fileMd5);
        //文件存在直接返回
        if (file.exists() && media != null) {
            log.info("文件注册 {} ,文件已经存在", fileName);
            return JSONResult.error("上传文件已存在");
        }

        boolean fileFold = createFileFold(fileMd5);

        if (!fileFold) {
            //上传文件目录创建失败
            log.info("上传文件目录创建失败 {} ,文件已经存在", fileName);
            return JSONResult.error("上传文件目录失败");
        }

        return JSONResult.success();
    }

    /*
     *根据文件md5得到文件路径
     */
    private String getFilePath(String fileMd5, String fileExt) {
        String filePath = uploadPath + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + "." + fileExt;
        return filePath;
    }

    //创建文件目录
    private boolean createFileFold(String fileMd5) {
        //创建上传文件目录
        String fileFolderPath = getFileFolderPath(fileMd5);
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            //创建文件夹
            boolean mkdirs = fileFolder.mkdirs();
            log.info("创建文件目录 {} ,结果 {}", fileFolder.getPath(), mkdirs);
            return mkdirs;
        }
        return true;
    }

    //得到文件所在目录
    private String getFileFolderPath(String fileMd5) {
        String fileFolderPath = uploadPath + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/";
        return fileFolderPath;
    }

    //得到块文件所在目录
    private String getChunkFileFolderPath(String fileMd5) {
        String fileChunkFolderPath = getFileFolderPath(fileMd5) +"/" + "chunks" + "/";
        return fileChunkFolderPath;
    }
}
