package cn.edu.fzu.cloudsign.project.cla.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.fzu.cloudsign.common.exception.CustomException;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourse;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseStudent;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseTeacher;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseMapper;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseStudentMapper;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseTeacherMapper;
import cn.edu.fzu.cloudsign.project.cla.service.IClaCourseService;

/**
 * 班课信息Service业务层处理
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
@Service
public class ClaCourseServiceImpl implements IClaCourseService {
	@Autowired
	private ClaCourseMapper claCourseMapper;

	@Autowired
	private ClaCourseStudentMapper claCourseStudentMapper;

	@Autowired
	private ClaCourseTeacherMapper claCourseTeacherMapper;

	/**
	 * 查询班课信息
	 * 
	 * @param courseId 班课信息ID
	 * @return 班课信息
	 */
	@Override
	public ClaCourse selectClaCourseById(Long courseId) {
		return claCourseMapper.selectClaCourseById(courseId);
	}

	/**
	 * 查询班课信息列表
	 * 
	 * @param claCourse 班课信息
	 * @return 班课信息
	 */
	@Override
	public List<ClaCourse> selectClaCourseList(ClaCourse claCourse) {
		return claCourseMapper.selectClaCourseList(claCourse);
	}

	/**
	 * 新增班课信息
	 * 
	 * @param claCourse 班课信息
	 * @return 结果
	 */
	@Override
	public int insertClaCourse(ClaCourse claCourse) {
		claCourse.setCreateTime(DateUtils.getNowDate());
		claCourse.setCreateBy(SecurityUtils.getUsername());
		return claCourseMapper.insertClaCourse(claCourse);
	}

	/**
	 * 修改班课信息
	 * 
	 * @param claCourse 班课信息
	 * @return 结果
	 */
	@Override
	public int updateClaCourse(ClaCourse claCourse) {
		claCourse.setUpdateTime(DateUtils.getNowDate());
		claCourse.setUpdateBy(SecurityUtils.getUsername());
		return claCourseMapper.updateClaCourse(claCourse);
	}

	/**
	 * 批量删除班课信息
	 * 
	 * @param courseIds 需要删除的班课信息ID
	 * @return 结果
	 */
	@Override
	public int deleteClaCourseByIds(Long[] courseIds) {
		for (Long courseId : courseIds) {
			if (claCourseStudentMapper.countClaCourseStudentByCourseId(courseId) > 0) {
				throw new CustomException(String.format("已经有学生选择此课程,不能删除"));
			}
		}
		return claCourseMapper.deleteClaCourseByIds(courseIds);
	}

	/**
	 * 删除班课信息信息
	 * 
	 * @param courseId 班课信息ID
	 * @return 结果
	 */
	@Override
	public int deleteClaCourseById(Long courseId) {
		return claCourseMapper.deleteClaCourseById(courseId);
	}

	/**
	 * 查询我创建的班课信息列表
	 */
	@Override
	public List<ClaCourse> selectMyCreateClaCourse(ClaCourse claCourse, Long userId) {
		claCourse.getParams().put("userId", userId);
		return claCourseMapper.selectMyCreateClaCourse(claCourse);
	}

	/**
	 * 查询我加入的班课信息列表
	 */
	@Override
	public List<ClaCourse> selectMyJoinClaCourse(ClaCourse claCourse, Long userId) {
		claCourse.getParams().put("userId", userId);
		return claCourseMapper.selectMyJoinClaCourse(claCourse);
	}

	private String getRandomCourseNum() {
		String courseNum;
		for (int i = 0; i < 10; i++) {
			courseNum = claCourseMapper.getRandomCourseNum();
			if (StringUtils.isNotEmpty(courseNum)) {
				return courseNum;
			}
		}
		throw new CustomException("无法获取唯一的班课号");
	}

	/**
	 * 加入班课
	 * 
	 * @param claCourse
	 * @return
	 */
	@Override
	public int joinClaCourse(ClaCourse claCourse) {
		ClaCourseStudent claCourseStudent = new ClaCourseStudent();
		claCourseStudent.setCreateBy(SecurityUtils.getUsername());
		claCourseStudent.setCreateTime(DateUtils.getNowDate());
		claCourseStudent.setCourseId(claCourse.getCourseId());
		claCourseStudent.setStudentId(SecurityUtils.getUserId());
		return claCourseStudentMapper.insertClaCourseStudent(claCourseStudent);
	}

	/**
	 * 创建班课
	 * 
	 * @param claCourse 班课信息
	 * @return 结果
	 */
	@Override
	public int createClaCourse(ClaCourse claCourse) {
		claCourse.setCourseNum(getRandomCourseNum());
		claCourse.setCreateBy(SecurityUtils.getUsername());
		claCourse.setCreateTime(DateUtils.getNowDate());
		int rows = claCourseMapper.insertClaCourse(claCourse);

		ClaCourseTeacher claCourseTeacher = new ClaCourseTeacher();
		claCourseTeacher.setCreateBy(SecurityUtils.getUsername());
		claCourseTeacher.setCreateTime(DateUtils.getNowDate());
		claCourseTeacher.setCourseId(claCourse.getCourseId());
		claCourseTeacher.setTeacherId(SecurityUtils.getUserId());

		claCourseTeacherMapper.insertClaCourseTeacher(claCourseTeacher);
		return rows;
	}

	/**
	 * 根据班课号查询班课详情
	 * 
	 * @param coursenum
	 * @return
	 */
	@Override
	public ClaCourse selectClaCourseByCourseNum(String coursenum) {
		return claCourseMapper.selectClaCourseByCourseNum(coursenum);
	}
}
