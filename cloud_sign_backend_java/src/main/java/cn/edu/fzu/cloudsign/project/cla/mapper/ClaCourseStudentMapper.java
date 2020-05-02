package cn.edu.fzu.cloudsign.project.cla.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseStudent;

/**
 * 班课和学生信息Mapper接口
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
public interface ClaCourseStudentMapper 
{
    /**
     * 查询班课和学生信息
     * 
     * @param courseId 班课和学生信息ID
     * @return 班课和学生信息
     */
    public ClaCourseStudent selectClaCourseStudentById(Long courseId);

    /**
     * 查询班课和学生信息列表
     * 
     * @param claCourseStudent 班课和学生信息
     * @return 班课和学生信息集合
     */
    public List<ClaCourseStudent> selectClaCourseStudentList(ClaCourseStudent claCourseStudent);

    /**
     * 新增班课和学生信息
     * 
     * @param claCourseStudent 班课和学生信息
     * @return 结果
     */
    public int insertClaCourseStudent(ClaCourseStudent claCourseStudent);

    /**
     * 修改班课和学生信息
     * 
     * @param claCourseStudent 班课和学生信息
     * @return 结果
     */
    public int updateClaCourseStudent(ClaCourseStudent claCourseStudent);

    /**
     * 删除班课和学生信息
     * 
     * @param courseId 班课和学生信息ID
     * @return 结果
     */
    public int deleteClaCourseStudentById(Long courseId);

    /**
     * 批量删除班课和学生信息
     * 
     * @param courseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaCourseStudentByIds(Long[] courseIds);
    
    /**
     * 查看课程是否已有学生选择
     * 
     * @param courseId
     * @return
     */
    public int countClaCourseStudentByCourseId(Long courseId);

	public int updateClaCourseStudentSignInfo(ClaCourseStudent claCourseStudent);
}
