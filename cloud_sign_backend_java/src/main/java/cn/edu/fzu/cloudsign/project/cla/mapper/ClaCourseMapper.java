package cn.edu.fzu.cloudsign.project.cla.mapper;

import java.util.List;

import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourse;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseMember;
import cn.edu.fzu.cloudsign.project.cla.domain.UniacadaClaCourse;

/**
 * 班课信息Mapper接口
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
public interface ClaCourseMapper {
	/**
	 * 查询班课信息
	 * 
	 * @param courseId 班课信息ID
	 * @return 班课信息
	 */
	public ClaCourse selectClaCourseById(Long courseId);

	/**
	 * 查询班课信息列表
	 * 
	 * @param claCourse 班课信息
	 * @return 班课信息集合
	 */
	public List<ClaCourse> selectClaCourseList(ClaCourse claCourse);

	/**
	 * 新增班课信息
	 * 
	 * @param claCourse 班课信息
	 * @return 结果
	 */
	public int insertClaCourse(ClaCourse claCourse);

	/**
	 * 修改班课信息
	 * 
	 * @param claCourse 班课信息
	 * @return 结果
	 */
	public int updateClaCourse(ClaCourse claCourse);

	/**
	 * 删除班课信息
	 * 
	 * @param courseId 班课信息ID
	 * @return 结果
	 */
	public int deleteClaCourseById(Long courseId);

	/**
	 * 批量删除班课信息
	 * 
	 * @param courseIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteClaCourseByIds(Long[] courseIds);

	/**
	 * 查询我创建的班课信息列表
	 * 
	 * @param claCourse
	 * @param userId
	 * @return
	 */
	public List<ClaCourse> selectMyCreateClaCourse(ClaCourse claCourse);

	/**
	 * 查询我创建的班课信息列表
	 * 
	 * @param claCourse
	 * @param userId
	 * @return
	 */
	public List<ClaCourse> selectMyJoinClaCourse(ClaCourse claCourse);

	/**
	 * 获取随机唯一的班课号
	 * 
	 * @return
	 */
	public String getRandomCourseNum();

	/**
	 * 根据班课号查询班课详情
	 * 
	 * @param coursenum
	 * @return
	 */
	public ClaCourse selectClaCourseByCourseNum(String coursenum);

	/**
	 * 查询教师创建的课程
	 * 
	 * @param userId
	 * @return
	 */
	public int countClaCourseTeacherByUserId(Long userId);

	/**
	 * 查询学生加入的课程
	 * 
	 * @param userId
	 * @return
	 */
	public int countClaCourseStudentByUserId(Long userId);

	/**
	 * 根据学校院系查询班课列表
	 * 
	 * @param uniacadaId
	 * @return
	 */
	public List<UniacadaClaCourse> selectUniacadaClaCourseByUniversity(Long uniacadaId);

	/**
	 * 根据班课查询班课学员
	 * 
	 * @param uniacadaId
	 * @return
	 */
	public List<ClaCourseMember> selectClaCourseMemberByClaCourse(Long courseId);
	
}
