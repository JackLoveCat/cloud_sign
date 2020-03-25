package cn.edu.fzu.cloudsign.project.cla.service.impl;

import java.util.List;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseStudentMapper;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseStudent;
import cn.edu.fzu.cloudsign.project.cla.service.IClaCourseStudentService;

/**
 * 班课和学生信息Service业务层处理
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
@Service
public class ClaCourseStudentServiceImpl implements IClaCourseStudentService {
	@Autowired
	private ClaCourseStudentMapper claCourseStudentMapper;

	/**
	 * 查询班课和学生信息
	 * 
	 * @param courseId 班课和学生信息ID
	 * @return 班课和学生信息
	 */
	@Override
	public ClaCourseStudent selectClaCourseStudentById(Long courseId) {
		return claCourseStudentMapper.selectClaCourseStudentById(courseId);
	}

	/**
	 * 查询班课和学生信息列表
	 * 
	 * @param claCourseStudent 班课和学生信息
	 * @return 班课和学生信息
	 */
	@Override
	public List<ClaCourseStudent> selectClaCourseStudentList(ClaCourseStudent claCourseStudent) {
		return claCourseStudentMapper.selectClaCourseStudentList(claCourseStudent);
	}

	/**
	 * 新增班课和学生信息
	 * 
	 * @param claCourseStudent 班课和学生信息
	 * @return 结果
	 */
	@Override
	public int insertClaCourseStudent(ClaCourseStudent claCourseStudent) {
		claCourseStudent.setCreateBy(SecurityUtils.getUsername());
		claCourseStudent.setCreateTime(DateUtils.getNowDate());
		return claCourseStudentMapper.insertClaCourseStudent(claCourseStudent);
	}

	/**
	 * 修改班课和学生信息
	 * 
	 * @param claCourseStudent 班课和学生信息
	 * @return 结果
	 */
	@Override
	public int updateClaCourseStudent(ClaCourseStudent claCourseStudent) {
		claCourseStudent.setUpdateBy(SecurityUtils.getUsername());
		claCourseStudent.setUpdateTime(DateUtils.getNowDate());
		return claCourseStudentMapper.updateClaCourseStudent(claCourseStudent);
	}

	/**
	 * 批量删除班课和学生信息
	 * 
	 * @param courseIds 需要删除的班课和学生信息ID
	 * @return 结果
	 */
	@Override
	public int deleteClaCourseStudentByIds(Long[] courseIds) {
		return claCourseStudentMapper.deleteClaCourseStudentByIds(courseIds);
	}

	/**
	 * 删除班课和学生信息信息
	 * 
	 * @param courseId 班课和学生信息ID
	 * @return 结果
	 */
	@Override
	public int deleteClaCourseStudentById(Long courseId) {
		return claCourseStudentMapper.deleteClaCourseStudentById(courseId);
	}
}
