package com.itcourse.mdcc.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author daemon
 * @since 2024-07-16
 */
@TableName("t_media_file")
public class MediaFile extends Model<MediaFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 文件的ID
     */
    @TableField("file_id")
    private String fileId;
    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 源文件名
     */
    @TableField("file_original_name")
    private String fileOriginalName;
    /**
     * 文件的云存储地址，处理过的m3u8格式
     */
    @TableField("file_url")
    private String fileUrl;
    /**
     * 文件在本地磁盘的临时存储目录
     */
    @TableField("file_path")
    private String filePath;
    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;
    /**
     * 1未处理 2处理成功 3处理失败 4无需处理
     */
    @TableField("file_status")
    private Integer fileStatus;
    /**
     * 文件总大小
     */
    @TableField("file_size")
    private Long fileSize;
    /**
     * 文件mime类型
     */
    @TableField("mime_type")
    private String mimeType;
    /**
     * 上传时间
     */
    @TableField("upload_time")
    private Date uploadTime;
    /**
     * 属于哪个章节
     */
    @TableField("chapter_id")
    private Long chapterId;
    /**
     * 属于哪个课程
     */
    @TableField("course_id")
    private Long courseId;
    /**
     * 视频的序号
     */
    private Integer number;
    /**
     * 视频名
     */
    private String name;
    @TableField("course_name")
    private String courseName;
    @TableField("chapter_name")
    private String chapterName;
    @TableField("time_minute")
    private Integer timeMinute;
    private Boolean free;
    //hls处理
    @TableField(exist = false)
    private MediaFileProcess_m3u8 mediaFileProcess_m3u8;

    public MediaFileProcess_m3u8 getMediaFileProcess_m3u8() {
        return mediaFileProcess_m3u8;
    }

    public void setMediaFileProcess_m3u8(MediaFileProcess_m3u8 mediaFileProcess_m3u8) {
        this.mediaFileProcess_m3u8 = mediaFileProcess_m3u8;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(Integer timeMinute) {
        this.timeMinute = timeMinute;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MediaFile{" +
        ", id=" + id +
        ", fileId=" + fileId +
        ", fileName=" + fileName +
        ", fileOriginalName=" + fileOriginalName +
        ", fileUrl=" + fileUrl +
        ", filePath=" + filePath +
        ", fileType=" + fileType +
        ", fileStatus=" + fileStatus +
        ", fileSize=" + fileSize +
        ", mimeType=" + mimeType +
        ", uploadTime=" + uploadTime +
        ", chapterId=" + chapterId +
        ", courseId=" + courseId +
        ", number=" + number +
        ", name=" + name +
        ", courseName=" + courseName +
        ", chapterName=" + chapterName +
        ", timeMinute=" + timeMinute +
        ", free=" + free +
        "}";
    }
}
