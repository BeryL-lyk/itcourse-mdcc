package com.itcourse.mdcc.service;

import com.itcourse.mdcc.domain.MediaFile;
import com.baomidou.mybatisplus.service.IService;
import com.itcourse.mdcc.result.JSONResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author daemon
 * @since 2024-07-16
 */
public interface IMediaFileService extends IService<MediaFile> {

    JSONResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

    JSONResult checkchunk(String fileMd5, Integer chunk, Integer chunkSize);

    JSONResult uploadchunk(MultipartFile file, String fileMd5, Integer chunk);
}
