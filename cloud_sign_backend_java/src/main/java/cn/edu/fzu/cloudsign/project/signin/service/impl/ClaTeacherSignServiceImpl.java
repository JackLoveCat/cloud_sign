package cn.edu.fzu.cloudsign.project.signin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.fzu.cloudsign.project.signin.mapper.ClaStudentSignMapper;
import cn.edu.fzu.cloudsign.project.signin.mapper.ClaTeacherSignMapper;
import cn.edu.fzu.cloudsign.common.exception.CustomException;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;
import cn.edu.fzu.cloudsign.common.utils.SigninEncryptionUtils;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourseTeacher;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseStudentMapper;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseTeacherMapper;
import cn.edu.fzu.cloudsign.project.cla.service.impl.ClaCourseTeacherServiceImpl;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaTeacherSign;
import cn.edu.fzu.cloudsign.project.signin.service.IClaTeacherSignService;

/**
 * 教师发起签到Service业务层处理
 * 
 * @author linkai
 * @date 2020-04-26
 */
@Service
public class ClaTeacherSignServiceImpl implements IClaTeacherSignService 
{
    @Autowired
    private ClaTeacherSignMapper claTeacherSignMapper;
    
    @Autowired
	private ClaStudentSignMapper claStudentSignMapper;
    
	@Autowired
	private ClaCourseStudentMapper claCourseStudentMapper;

	@Autowired
	private ClaCourseTeacherMapper claCourseTeacherMapper;

    /**
     * 查询教师发起签到
     * 
     * @param teacherSignId 教师发起签到ID
     * @return 教师发起签到
     */
    @Override
    public ClaTeacherSign selectClaTeacherSignById(Long teacherSignId)
    {
        return claTeacherSignMapper.selectClaTeacherSignById(teacherSignId);
    }

    /**
     * 查询教师发起签到列表
     * 
     * @param claTeacherSign 教师发起签到
     * @return 教师发起签到
     */
    @Override
    public List<ClaTeacherSign> selectClaTeacherSignList(ClaTeacherSign claTeacherSign)
    {
        return claTeacherSignMapper.selectClaTeacherSignList(claTeacherSign);
    }

    /**
     * 新增教师发起签到
     * 
     * @param claTeacherSign 教师发起签到
     * @return 结果
     */
    @Override
    public int insertClaTeacherSign(ClaTeacherSign claTeacherSign)
    {
        return claTeacherSignMapper.insertClaTeacherSign(claTeacherSign);
    }

    /**
     * 修改教师发起签到
     * 
     * @param claTeacherSign 教师发起签到
     * @return 结果
     */
    @Override
    public int updateClaTeacherSign(ClaTeacherSign claTeacherSign)
    {
        return claTeacherSignMapper.updateClaTeacherSign(claTeacherSign);
    }

    /**
     * 批量删除教师发起签到
     * 
     * @param teacherSignIds 需要删除的教师发起签到ID
     * @return 结果
     */
    @Override
    public int deleteClaTeacherSignByIds(Long[] teacherSignIds)
    {
        return claTeacherSignMapper.deleteClaTeacherSignByIds(teacherSignIds);
    }

    /**
     * 删除教师发起签到信息
     * 
     * @param teacherSignId 教师发起签到ID
     * @return 结果
     */
    @Override
    public int deleteClaTeacherSignById(Long teacherSignId)
    {
        return claTeacherSignMapper.deleteClaTeacherSignById(teacherSignId);
    }
    
    @Override
    public int teacherSignIn(ClaTeacherSign claTeacherSign)
    {
    	int rows=0;
    	//查询下是否有这门班课正在发起的有效签到-还在时间范围内
    	ClaTeacherSign claTeacherSign_check=getSignInCourseInfo(claTeacherSign.getCourseId());
		if(null != claTeacherSign_check )
		{
			throw new CustomException("这门班课存在正在发起的有效签到！");
		}
		
    	//encrypt start
    	try {

//            String test = "123456789";
    		if(!StringUtils.isEmpty(claTeacherSign.getGesture()))
    		{
    			String gesture=claTeacherSign.getGesture();

                SigninEncryptionUtils des = new SigninEncryptionUtils("cloudsignbackend");// 自定义密钥

                System.out.println("加密前的字符：" + gesture);

                System.out.println("加密后的字符：" + des.encrypt(gesture));

                System.out.println("解密后的字符：" + des.decrypt(des.encrypt(gesture)));
                
                claTeacherSign.setGesture(des.encrypt(gesture));
                
    		}
    		

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("签到手势加密异常！");
        }
    	//encrypt end
    	
    	//插入教师发起签到表
    	rows=claTeacherSignMapper.insertClaTeacherSign(claTeacherSign);
  	
   
    	//更新教师班课表	
		ClaCourseTeacher claCourseTeacher = new ClaCourseTeacher();
		claCourseTeacher.setCourseId(claTeacherSign.getCourseId());
		claCourseTeacher.setTeacherId(claTeacherSign.getTeacherId());
		claCourseTeacher.setUpdateBy(SecurityUtils.getUsername());
		claCourseTeacher.setUpdateTime(DateUtils.getNowDate());
		claCourseTeacherMapper.updateClaCourseTeacherSignInfo(claCourseTeacher);
		
		return rows;
//        return claTeacherSignMapper.insertClaTeacherSign(claTeacherSign);
    }
    
    

    @Override
    public int teacherSignInStop(ClaTeacherSign claTeacherSign)
    {
        return claTeacherSignMapper.updateClaTeacherSign(claTeacherSign);
    }
    

	@Override
	public ClaTeacherSign getSignInCourseInfo(Long courseId) {
		return claTeacherSignMapper.getSignInCourseInfo(courseId);
	}

	@Override
	public List<ClaTeacherSign> getSignInCourseList(Long teacherId) {
		return claTeacherSignMapper.getSignInCourseList(teacherId);
	}

//	@Override
//	public List<String> getSignInStudentList(ClaTeacherSign claTeacherSign) {
//		return claStudentSignMapper.getSignInStudentList(claTeacherSign);
//	}
}
