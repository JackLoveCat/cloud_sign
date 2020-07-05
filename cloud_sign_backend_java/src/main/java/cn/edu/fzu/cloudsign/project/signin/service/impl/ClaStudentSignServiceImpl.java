package cn.edu.fzu.cloudsign.project.signin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.signin.mapper.ClaStudentSignMapper;
import cn.edu.fzu.cloudsign.project.signin.mapper.ClaTeacherSignMapper;
import cn.edu.fzu.cloudsign.common.exception.CustomException;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;
import cn.edu.fzu.cloudsign.common.utils.SigninEncryptionUtils;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseStudent;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseTeacher;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseStudentMapper;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaStudentSign;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaTeacherSign;
import cn.edu.fzu.cloudsign.project.signin.service.IClaStudentSignService;

/**
 * 学生签到记录Service业务层处理
 * 
 * @author linkai
 * @date 2020-04-26
 */
@Service
public class ClaStudentSignServiceImpl implements IClaStudentSignService 
{
    @Autowired
    private ClaStudentSignMapper claStudentSignMapper;
    
    @Autowired
	private ClaCourseStudentMapper claCourseStudentMapper;
    
    @Autowired
	private ClaTeacherSignMapper claTeacherSignMapper;

    /**
     * 查询学生签到记录
     * 
     * @param studentSignId 学生签到记录ID
     * @return 学生签到记录
     */
    @Override
    public ClaStudentSign selectClaStudentSignById(Long studentSignId)
    {
        return claStudentSignMapper.selectClaStudentSignById(studentSignId);
    }

    /**
     * 查询学生签到记录列表
     * 
     * @param claStudentSign 学生签到记录
     * @return 学生签到记录
     */
    @Override
    public List<ClaStudentSign> selectClaStudentSignList(ClaStudentSign claStudentSign)
    {
        return claStudentSignMapper.selectClaStudentSignList(claStudentSign);
    }

    /**
     * 新增学生签到记录
     * 
     * @param claStudentSign 学生签到记录
     * @return 结果
     */
    @Override
    public int insertClaStudentSign(ClaStudentSign claStudentSign)
    {
        return claStudentSignMapper.insertClaStudentSign(claStudentSign);
    }

    /**
     * 修改学生签到记录
     * 
     * @param claStudentSign 学生签到记录
     * @return 结果
     */
    @Override
    public int updateClaStudentSign(ClaStudentSign claStudentSign)
    {
        return claStudentSignMapper.updateClaStudentSign(claStudentSign);
    }

    /**
     * 批量删除学生签到记录
     * 
     * @param studentSignIds 需要删除的学生签到记录ID
     * @return 结果
     */
    @Override
    public int deleteClaStudentSignByIds(Long[] studentSignIds)
    {
        return claStudentSignMapper.deleteClaStudentSignByIds(studentSignIds);
    }

    /**
     * 删除学生签到记录信息
     * 
     * @param studentSignId 学生签到记录ID
     * @return 结果
     */
    @Override
    public int deleteClaStudentSignById(Long studentSignId)
    {
        return claStudentSignMapper.deleteClaStudentSignById(studentSignId);
    }

	@Override
	public int studentSignIn(ClaStudentSign claStudentSign) {
		int rows=0;
		//在判断下是否在签到时间范围内（可能进入签到页面了但是没点时间过了）
		ClaTeacherSign claTeacherSign=claTeacherSignMapper.getSignInCourseInfo(claStudentSign.getCourseId());
		if(null == claTeacherSign )
		{
			throw new CustomException("非签到时间！");
		}
		
		//【判断签到类型】1-手势签到 0-点击签到
		if("1".equals(claTeacherSign.getSignType()))
		{
			String gesture=null;
			try {
				SigninEncryptionUtils des = new SigninEncryptionUtils("cloudsignbackend");// 自定义密钥
				System.out.println("加密后的字符：" + claTeacherSign.getGesture());
				System.out.println("解密后的字符：" + des.decrypt(claTeacherSign.getGesture()));
				gesture=des.decrypt(claTeacherSign.getGesture());
			} catch (Exception e) {
				e.printStackTrace();
	            throw new CustomException("签到手势解密异常！");
			}
			if(!claStudentSign.getGesture().equals(gesture))
			{
				throw new CustomException("签到手势不匹配！");
			}
		}
    	
    	//如果本次班课发起的签到已经+经验了 则后续签到可以完成 但是不增加经验了
    	Map<String, Date> queryMap = new HashMap<>(2);
    	System.out.println(claTeacherSign.getStartTime());
    	System.out.println(claTeacherSign.getStopTime());
    	queryMap.put("startTime", claTeacherSign.getStartTime());
    	queryMap.put("stopTime", claTeacherSign.getStopTime());
    	List<ClaStudentSign> ClaStudentSign_check_lst = claStudentSignMapper.selectClaStudentSignBySignTime(queryMap);
    	if(null == ClaStudentSign_check_lst || ClaStudentSign_check_lst.size() == 0)
    	{
    		//更新学生班课表经验
    		ClaCourseStudent claCourseStudent = new ClaCourseStudent();
    		claCourseStudent.setCourseId(claStudentSign.getCourseId());
    		claCourseStudent.setStudentId(claStudentSign.getStudentId());
    		claCourseStudent.setUpdateBy(SecurityUtils.getUsername());
    		claCourseStudent.setUpdateTime(DateUtils.getNowDate());	
    		claCourseStudentMapper.updateClaCourseStudentSignInfo(claCourseStudent);
    	}
    	
    	//插入学生记录签到表
    	rows=claStudentSignMapper.studentSignIn(claStudentSign);
    		
		return rows;
	}
}
