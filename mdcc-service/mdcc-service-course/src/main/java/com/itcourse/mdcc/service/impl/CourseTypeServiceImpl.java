package com.itcourse.mdcc.service.impl;

import com.itcourse.mdcc.constants.BaseConstants;
import com.itcourse.mdcc.domain.CourseType;
import com.itcourse.mdcc.mapper.CourseTypeMapper;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.service.ICourseTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author daemon
 * @since 2024-07-15
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 课程分类树
     *
     * @return 课程分类列表
     */
    @Override
    public JSONResult getTreeData() {
        String key = BaseConstants.Course.COURSE_TYPE;
        // 先查询缓存
        redisTemplate.opsForValue().get(key);

        // 1.从数据库中查询所有课程类型
        List<CourseType> courseTypes = baseMapper.selectList(null);

        // 2.将类型集合转化为map
        Map<Long, CourseType> courseTypeMap = new HashMap<>();
        for (CourseType courseType : courseTypes) {
            courseTypeMap.put(courseType.getId(), courseType);
        }

        // 3.判断是否是顶级目录
        List<CourseType> firstType = new ArrayList<>();
        for (CourseType courseType : courseTypes) {
            Long pid = courseType.getPid();
            // 一级分类
            if (pid == null || pid == 0) {
                firstType.add(courseType);
            } else {
                // 将当前目录加入到父目录kids中
                courseTypeMap.get(pid).getChildren().add(courseType);
            }
        }

        // 存入缓存
        redisTemplate.opsForValue().set(key, firstType);

        return JSONResult.success(firstType);
    }

    /**
     * 删除
     * @param id 要删除的id
     * @return 删除是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Serializable id) {
        // 删除数据库中的数据
        Integer i = courseTypeMapper.deleteById(id);

        // 只有当数据库中成功删除了才删除缓存中的数据
        if (i > 0) {
            redisTemplate.delete(BaseConstants.Course.COURSE_TYPE);
        }
        return true;
    }

    /**
     * 修改
     * @param entity 要修改的对象
     * @return 修改是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(CourseType entity) {
        Integer i = courseTypeMapper.updateById(entity);

        if (i > 0) {
            redisTemplate.delete(BaseConstants.Course.COURSE_TYPE);
        }

        return true;
    }

    /**
     * 保存新对象
     * @param entity 要保存的对象
     * @return 保存是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(CourseType entity) {
        Integer i = courseTypeMapper.insert(entity);
        if (i > 0) {
            redisTemplate.delete(BaseConstants.Course.COURSE_TYPE);
        }
        return true;
    }
}
