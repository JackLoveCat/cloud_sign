package cn.edu.fzu.cloudsign.project.cla.service.impl;

import java.util.List;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseTeacherMapper;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseTeacher;
import cn.edu.fzu.cloudsign.project.cla.service.IClaCourseTeacherService;

/**
 * 班课和教师信息Service业务层处理
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
@Service
public class ClaCourseTeacherServiceImpl implements IClaCourseTeacherService {
	@Autowired
	private ClaCourseTeacherMapper claCourseTeacherMapper;

	/**
	 * 查询班课和教师信息
	 * 
	 * @param courseId 班课和教师信息ID
	 * @return 班课和教师信息
	 */
	@Override
	public ClaCourseTeacher selectClaCourseTeacherById(Long courseId) {
		return claCourseTeacherMapper.selectClaCourseTeacherById(courseId);
	}

	/**
	 * 查询班课和教师信息列表
	 * 
	 * @param claCourseTeacher 班课和教师信息
	 * @return 班课和教师信息
	 */
	@Override
	public List<ClaCourseTeacher> selectClaCourseTeacherList(ClaCourseTeacher claCourseTeacher) {
		return claCourseTeacherMapper.selectClaCourseTeacherList(claCourseTeacher);
	}

	/**
	 * 新增班课和教师信息
	 * 
	 * @param claCourseTeacher 班课和教师信息
	 * @return 结果
	 */
	@Override
	public int insertClaCourseTeacher(ClaCourseTeacher claCourseTeacher) {
		claCourseTeacher.setCreateBy(SecurityUtils.getUsername());
		claCourseTeacher.setCreateTime(DateUtils.getNowDate());
		return claCourseTeacherMapper.insertClaCourseTeacher(claCourseTeacher);
	}

	/**
	 * 修改班课和教师信息
	 * 
	 * @param claCourseTeacher 班课和教师信息
	 * @return 结果
	 */
	@Override
	public int updateClaCourseTeacher(ClaCourseTeacher claCourseTeacher) {
		claCourseTeacher.setUpdateBy(SecurityUtils.getUsername());
		claCourseTeacher.setUpdateTime(DateUtils.getNowDate());
		return claCourseTeacherMapper.updateClaCourseTeacher(claCourseTeacher);
	}

	/**
	 * 批量删除班课和教师信息
	 * 
	 * @param courseIds 需要删除的班课和教师信息ID
	 * @return 结果
	 */
	@Override
	public int deleteClaCourseTeacherByIds(Long[] courseIds) {
		return claCourseTeacherMapper.deleteClaCourseTeacherByIds(courseIds);
	}

	/**
	 * 删除班课和教师信息信息
	 * 
	 * @param courseId 班课和教师信息ID
	 * @return 结果
	 */
	@Override
	public int deleteClaCourseTeacherById(Long courseId) {
		return claCourseTeacherMapper.deleteClaCourseTeacherById(courseId);
	}
}
