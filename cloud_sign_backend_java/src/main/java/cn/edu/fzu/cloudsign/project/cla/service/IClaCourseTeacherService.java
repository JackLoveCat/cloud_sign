package cn.edu.fzu.cloudsign.project.cla.service;

import java.util.List;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseTeacher;

/**
 * 班课和教师信息Service接口
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
public interface IClaCourseTeacherService 
{
    /**
     * 查询班课和教师信息
     * 
     * @param courseId 班课和教师信息ID
     * @return 班课和教师信息
     */
    public ClaCourseTeacher selectClaCourseTeacherById(Long courseId);

    /**
     * 查询班课和教师信息列表
     * 
     * @param claCourseTeacher 班课和教师信息
     * @return 班课和教师信息集合
     */
    public List<ClaCourseTeacher> selectClaCourseTeacherList(ClaCourseTeacher claCourseTeacher);

    /**
     * 新增班课和教师信息
     * 
     * @param claCourseTeacher 班课和教师信息
     * @return 结果
     */
    public int insertClaCourseTeacher(ClaCourseTeacher claCourseTeacher);

    /**
     * 修改班课和教师信息
     * 
     * @param claCourseTeacher 班课和教师信息
     * @return 结果
     */
    public int updateClaCourseTeacher(ClaCourseTeacher claCourseTeacher);

    /**
     * 批量删除班课和教师信息
     * 
     * @param courseIds 需要删除的班课和教师信息ID
     * @return 结果
     */
    public int deleteClaCourseTeacherByIds(Long[] courseIds);

    /**
     * 删除班课和教师信息信息
     * 
     * @param courseId 班课和教师信息ID
     * @return 结果
     */
    public int deleteClaCourseTeacherById(Long courseId);
}
