package com.itcourse.mdcc.mapper;

import com.itcourse.mdcc.domain.MediaFile;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author daemon
 * @since 2024-07-16
 */
public interface MediaFileMapper extends BaseMapper<MediaFile> {

    int selectMaxNumberByChapterIdAndCourseId(@Param("courseChapterId") Long courseChapterId, @Param("courseId") Long courseId);
}
