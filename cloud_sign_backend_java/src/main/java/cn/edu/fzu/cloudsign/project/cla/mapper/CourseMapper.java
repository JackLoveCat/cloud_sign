package cn.edu.fzu.cloudsign.project.cla.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.cla.domain.Course;

/**
 * 班课信息Mapper接口
 * 
 * @author fanxuenan
 * @date 2020-03-17
 */
public interface CourseMapper 
{
    /**
     * 查询班课信息
     * 
     * @param courseId 班课信息ID
     * @return 班课信息
     */
    public Course selectCourseById(Long courseId);

    /**
     * 查询班课信息列表
     * 
     * @param course 班课信息
     * @return 班课信息集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增班课信息
     * 
     * @param course 班课信息
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改班课信息
     * 
     * @param course 班课信息
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 删除班课信息
     * 
     * @param courseId 班课信息ID
     * @return 结果
     */
    public int deleteCourseById(Long courseId);

    /**
     * 批量删除班课信息
     * 
     * @param courseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseByIds(Long[] courseIds);
}
