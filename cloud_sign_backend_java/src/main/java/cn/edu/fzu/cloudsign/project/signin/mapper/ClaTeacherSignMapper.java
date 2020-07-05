package cn.edu.fzu.cloudsign.project.signin.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaTeacherSign;

/**
 * 教师发起签到Mapper接口
 * 
 * @author linkai
 * @date 2020-04-26
 */
public interface ClaTeacherSignMapper 
{
    /**
     * 查询教师发起签到
     * 
     * @param teacherSignId 教师发起签到ID
     * @return 教师发起签到
     */
    public ClaTeacherSign selectClaTeacherSignById(Long teacherSignId);

    /**
     * 查询教师发起签到列表
     * 
     * @param claTeacherSign 教师发起签到
     * @return 教师发起签到集合
     */
    public List<ClaTeacherSign> selectClaTeacherSignList(ClaTeacherSign claTeacherSign);

    /**
     * 新增教师发起签到
     * 
     * @param claTeacherSign 教师发起签到
     * @return 结果
     */
    public int insertClaTeacherSign(ClaTeacherSign claTeacherSign);

    /**
     * 修改教师发起签到
     * 
     * @param claTeacherSign 教师发起签到
     * @return 结果
     */
    public int updateClaTeacherSign(ClaTeacherSign claTeacherSign);

    /**
     * 删除教师发起签到
     * 
     * @param teacherSignId 教师发起签到ID
     * @return 结果
     */
    public int deleteClaTeacherSignById(Long teacherSignId);

    /**
     * 批量删除教师发起签到
     * 
     * @param teacherSignIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaTeacherSignByIds(Long[] teacherSignIds);

	public ClaTeacherSign getSignInCourseInfo(Long courseId);

	public List<ClaTeacherSign> getSignInCourseList(Long teacherId);
}
