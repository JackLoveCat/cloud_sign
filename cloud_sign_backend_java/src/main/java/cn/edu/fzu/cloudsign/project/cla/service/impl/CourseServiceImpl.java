package cn.edu.fzu.cloudsign.project.cla.service.impl;

import java.util.List;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.cla.mapper.CourseMapper;
import cn.edu.fzu.cloudsign.project.cla.domain.Course;
import cn.edu.fzu.cloudsign.project.cla.service.ICourseService;

/**
 * 班课信息Service业务层处理
 * 
 * @author fanxuenan
 * @date 2020-03-17
 */
@Service
public class CourseServiceImpl implements ICourseService 
{
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询班课信息
     * 
     * @param courseId 班课信息ID
     * @return 班课信息
     */
    @Override
    public Course selectCourseById(Long courseId)
    {
        return courseMapper.selectCourseById(courseId);
    }

    /**
     * 查询班课信息列表
     * 
     * @param course 班课信息
     * @return 班课信息
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增班课信息
     * 
     * @param course 班课信息
     * @return 结果
     */
    @Override
    public int insertCourse(Course course)
    {
        course.setCreateTime(DateUtils.getNowDate());
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改班课信息
     * 
     * @param course 班课信息
     * @return 结果
     */
    @Override
    public int updateCourse(Course course)
    {
        course.setUpdateTime(DateUtils.getNowDate());
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除班课信息
     * 
     * @param courseIds 需要删除的班课信息ID
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(Long[] courseIds)
    {
        return courseMapper.deleteCourseByIds(courseIds);
    }

    /**
     * 删除班课信息信息
     * 
     * @param courseId 班课信息ID
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long courseId)
    {
        return courseMapper.deleteCourseById(courseId);
    }
}
