package cn.edu.fzu.cloudsign.project.signin.service;

import java.util.List;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaStudentSign;

/**
 * 学生签到记录Service接口
 * 
 * @author linkai
 * @date 2020-04-26
 */
public interface IClaStudentSignService 
{
    /**
     * 查询学生签到记录
     * 
     * @param studentSignId 学生签到记录ID
     * @return 学生签到记录
     */
    public ClaStudentSign selectClaStudentSignById(Long studentSignId);

    /**
     * 查询学生签到记录列表
     * 
     * @param claStudentSign 学生签到记录
     * @return 学生签到记录集合
     */
    public List<ClaStudentSign> selectClaStudentSignList(ClaStudentSign claStudentSign);

    /**
     * 新增学生签到记录
     * 
     * @param claStudentSign 学生签到记录
     * @return 结果
     */
    public int insertClaStudentSign(ClaStudentSign claStudentSign);

    /**
     * 修改学生签到记录
     * 
     * @param claStudentSign 学生签到记录
     * @return 结果
     */
    public int updateClaStudentSign(ClaStudentSign claStudentSign);

    /**
     * 批量删除学生签到记录
     * 
     * @param studentSignIds 需要删除的学生签到记录ID
     * @return 结果
     */
    public int deleteClaStudentSignByIds(Long[] studentSignIds);

    /**
     * 删除学生签到记录信息
     * 
     * @param studentSignId 学生签到记录ID
     * @return 结果
     */
    public int deleteClaStudentSignById(Long studentSignId);

	public int studentSignIn(ClaStudentSign claStudentSign);
}
