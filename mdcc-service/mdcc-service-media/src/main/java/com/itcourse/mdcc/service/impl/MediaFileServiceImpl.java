package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.domain.MediaFile;
import com.itcourse.mdcc.domain.MediaFileProcess_m3u8;
import com.itcourse.mdcc.mapper.MediaFileMapper;
import com.itcourse.mdcc.mq.MediaProducer;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.service.IMediaFileService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itcourse.mdcc.utils.HlsVideoUtil;
import com.itcourse.mdcc.utils.Mp4VideoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

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

    @Autowired
    private MediaProducer mediaProducer;

    @Autowired
    private MediaFileMapper mediaFileMapper;

    //上传文件根目录
    @Value("${media.upload.location}")
    private String uploadPath;

    //推流服务器地址
    @Value("${srs.rtmp}")
    private String srsRtmpPath;

    //推流服务器播放
    @Value("${srs.play}")
    private String srsPalyPath;

    //上传文件根目录
    @Value("${xc‐service‐manage‐media.upload‐location}")
    String serverPath;

    //ffmpeg绝对路径
    @Value("${xc‐service‐manage‐media.ffmpeg‐path}")
    String ffmpeg_path;


    /**
     * 校验文件块是否已经存在了
     */
    @Override
    public JSONResult checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
        //获取块文件文件夹路径
        String chunkfileFolderPath = getChunkFileFolderPath(fileMd5);
        //块文件的文件名称以1,2,3..序号命名，没有扩展名
        File chunkFile = new File(chunkfileFolderPath + chunk);
        if (!chunkFile.exists()) {
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

    /**
     * 上传分片
     *
     * @param file
     * @param fileMd5
     * @param chunk
     * @return
     */
    @Override
    public JSONResult uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
        if (file == null) {
            return JSONResult.error("上传文件不能为null");
        }
        // 创建块目录
        createChunkFileFolder(fileMd5);
        //块文件存放完整路径
        File chunkfile = new File(getChunkFileFolderPath(fileMd5) + chunk);

        //上传的块文件
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(chunkfile);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.error("文件上传失败！");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JSONResult.success();
    }

    /**
     * 上传分片 完成后 合并分块
     *
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @param chapterId
     * @param courseId
     * @param videoNumber
     * @param name
     * @param courseName
     * @param chapterName
     * @return
     */
    @Override
    public JSONResult mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt, Long chapterId, Long courseId, Integer videoNumber, String name, String courseName, String chapterName) {
        Long startTime = System.currentTimeMillis();

        //获取块文件的路径
        String chunkfileFolderPath = getChunkFileFolderPath(fileMd5);

        //创建文件目录
        File chunkfileFolder = new File(chunkfileFolderPath);

        //合并文件，创建新的文件对象
        File mergeFile = new File(getFilePath(fileMd5, fileExt));

        // 合并文件存在先删除再创建
        if (mergeFile.exists()) {
            mergeFile.delete();
        }

        boolean newFile = false;
        try {
            //创建文件
            newFile = mergeFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!newFile) {
            //创建失败
            return JSONResult.error("创建文件失败！");
        }

        //获取块文件，此列表是已经排好序的列表
        List<File> chunkFiles = getChunkFiles(chunkfileFolder);

        //合并文件
        mergeFile = mergeFile(mergeFile, chunkFiles);

        if (mergeFile == null) {
            return JSONResult.error("合并文件失败！");
        }

        //校验文件
        boolean checkResult = this.checkFileMd5(mergeFile, fileMd5);

        if (!checkResult) {
            return JSONResult.error("文件校验失败！");
        }

        //将文件信息保存到数据库
        MediaFile mediaFile = new MediaFile();
        //MD5作为文件唯一ID
        mediaFile.setFileId(fileMd5);
        //文件名
        mediaFile.setFileName(fileMd5 + "." + fileExt);
        //源文件名
        mediaFile.setFileOriginalName(fileName);
        //文件路径保存相对路径
        mediaFile.setFilePath(getFileFolderRelativePath(fileMd5, fileExt));
        mediaFile.setFileSize(fileSize);
        mediaFile.setUploadTime(new Date());
        mediaFile.setMimeType(mimetype);
        mediaFile.setFileType(fileExt);
        mediaFile.setChapterId(chapterId);
        mediaFile.setCourseId(courseId);
        mediaFile.setName(name);
        mediaFile.setCourseName(courseName);
        mediaFile.setChapterName(chapterName);
        // 播放地址
        mediaFile.setFileUrl(srsPalyPath + mediaFile.getFileId() + ".m3u8");
        //状态为上传成功
        mediaFile.setFileStatus(2);

        //根据最新的number生成number
        int maxMumber = mediaFileMapper.selectMaxNumberByChapterIdAndCourseId(chapterId, courseId);
        mediaFile.setNumber(maxMumber + 1);

        mediaFileMapper.insert(mediaFile);

        // 文件上传到视频服务器做 断点续播 推送
        boolean success = mediaProducer.synSend(mediaFile);

        log.info("合并文件耗时 {}", System.currentTimeMillis() - startTime);
        return success ? JSONResult.success() : JSONResult.error();
    }

    public JSONResult handleFile2m3u8Mp4(MediaFile mediaFile) {
        mediaFile.setFileStatus(1);
        //处理状态为未处理
        mediaFileMapper.updateById(mediaFile);

        //此地址为mp4的本地地址
        String video_path = serverPath + mediaFile.getFilePath() + mediaFile.getFileName();

        //初始化推流工具
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpeg_path);
        hlsVideoUtil.init(srsRtmpPath, video_path, mediaFile.getFileId());
        //推流到云端
        String result = hlsVideoUtil.generateM3u8ToSrs();

        if (result == null || !result.equals("success")) {
            //操作失败写入处理日志
            mediaFile.setFileStatus(3);
            //处理状态为处理失败
            MediaFileProcess_m3u8 mediaFileProcess_m3u8 = new MediaFileProcess_m3u8();
            mediaFileProcess_m3u8.setErrorMsg(result);
            mediaFile.setMediaFileProcess_m3u8(mediaFileProcess_m3u8);
            mediaFileMapper.updateById(mediaFile);
            return JSONResult.success();
        }
        //获取m3u8列表
        //更新处理状态为成功
        mediaFile.setFileStatus(2);
        //m3u8文件url,播放使用 http://192.168.75.128:8080/live/23b52cc0e985544b0f240d5d2d8fed10.m3u8
        mediaFile.setFileUrl(srsPalyPath + mediaFile.getFileId() + ".m3u8");
        mediaFileMapper.updateById(mediaFile);
        log.info("视频推流完成...");

        return JSONResult.success();
    }

    /**
     * 文件推流
     **/
    public JSONResult handleFile2m3u8(MediaFile mediaFile) {
        String fileType = mediaFile.getFileType();
        if (fileType == null) {
            //目前只处理avi文件
            mediaFile.setFileStatus(4);
            //处理状态为无需处理
            mediaFileMapper.updateById(mediaFile);
            return JSONResult.success();
        } else if ("mp4".equalsIgnoreCase(fileType)) {
            return handleFile2m3u8Mp4(mediaFile);
        } else {
            mediaFile.setFileStatus(1);
            //处理状态为未处理
            mediaFileMapper.updateById(mediaFile);
        }
        //生成mp4
        String video_path = serverPath + mediaFile.getFilePath() + mediaFile.getFileName();
        String mp4_name = mediaFile.getFileId() + ".mp4";
        String mp4folder_path = serverPath + mediaFile.getFilePath();

        //视频编码，生成mp4文件
        Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpeg_path, video_path, mp4_name, mp4folder_path);

        String result = videoUtil.generateMp4();
        if (result == null || !result.equals("success")) {
            //操作失败写入处理日志
            mediaFile.setFileStatus(3);
            mediaFileMapper.updateById(mediaFile);
            return JSONResult.error("视频转换mp4失败");
        }

        //此地址为mp4的本地地址
        video_path = serverPath + mediaFile.getFilePath() + mp4_name;

        //初始化推流工具
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpeg_path);
        hlsVideoUtil.init(srsRtmpPath, video_path, mediaFile.getFileId());
        //推流到云端
        result = hlsVideoUtil.generateM3u8ToSrs();

        if (result == null || !result.equals("success")) {
            //操作失败写入处理日志
            mediaFile.setFileStatus(3);
            //处理状态为处理失败
            MediaFileProcess_m3u8 mediaFileProcess_m3u8 = new MediaFileProcess_m3u8();
            mediaFileProcess_m3u8.setErrorMsg(result);
            mediaFile.setMediaFileProcess_m3u8(mediaFileProcess_m3u8);
            mediaFileMapper.updateById(mediaFile);
            return JSONResult.success();
        }
        //获取m3u8列表
        //更新处理状态为成功
        mediaFile.setFileStatus(2);
        //m3u8文件url,播放使用 http://192.168.75.128:8080/live/23b52cc0e985544b0f240d5d2d8fed10.m3u8
        mediaFile.setFileUrl(srsPalyPath + mediaFile.getFileId() + ".m3u8");
        mediaFileMapper.updateById(mediaFile);
        log.info("视频推流完成...");

        return JSONResult.success();
    }

    /**
     * 创建块文件目录
     */
    private boolean createChunkFileFolder(String fileMd5) { //创建上传文件目录
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        if (!chunkFileFolder.exists()) {
            //创建文件夹
            boolean mkdirs = chunkFileFolder.mkdirs();
            return mkdirs;
        }
        return true;
    }

    //获取所有块文件
    private List<File> getChunkFiles(File chunkfileFolder) {
        //获取路径下的所有块文件
        File[] chunkFiles = chunkfileFolder.listFiles();
        //将文件数组转成list，并排序
        List<File> chunkFileList = new ArrayList<File>();
        chunkFileList.addAll(Arrays.asList(chunkFiles));
        //排序
        Collections.sort(chunkFileList, (o1, o2) -> {
            if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {
                return 1;
            }
            return -1;
        });
        return chunkFileList;
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
        String fileChunkFolderPath = getFileFolderPath(fileMd5) + "/" + "chunks" + "/";
        return fileChunkFolderPath;
    }

    //合并文件
    private File mergeFile(File mergeFile, List<File> chunkFiles) {
        try {
            //创建写文件对象
            RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
            //遍历分块文件开始合并
            // 读取文件缓冲区
            byte[] b = new byte[1024];
            for (File chunkFile : chunkFiles) {
                RandomAccessFile raf_read = new RandomAccessFile(chunkFile, "r");
                int len = -1;
                //读取分块文件
                while ((len = raf_read.read(b)) != -1) {
                    //向合并文件中写数据
                    raf_write.write(b, 0, len);
                }
                raf_read.close();
            }
            raf_write.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mergeFile;
    }

    //校验文件的md5值
    private boolean checkFileMd5(File mergeFile, String md5) {
        if (mergeFile == null || StringUtils.isEmpty(md5)) {
            return false;
        }
        //进行md5校验
        FileInputStream mergeFileInputstream = null;
        try {
            mergeFileInputstream = new FileInputStream(mergeFile);
            //得到文件的md5
            String mergeFileMd5 = DigestUtils.md5Hex(mergeFileInputstream);
            //比较md5
            if (md5.equalsIgnoreCase(mergeFileMd5)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                mergeFileInputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //得到文件目录相对路径，路径中去掉根目录
    private String getFileFolderRelativePath(String fileMd5, String fileExt) {
        String filePath = fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/";
        return filePath;
    }
}
