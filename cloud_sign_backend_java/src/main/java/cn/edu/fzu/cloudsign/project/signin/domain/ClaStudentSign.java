package cn.edu.fzu.cloudsign.project.signin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 学生签到记录对象 cla_student_sign
 * 
 * @author linkai
 * @date 2020-04-26
 */
public class ClaStudentSign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生签到记录ID */
    @ApiModelProperty("学生签到记录ID")
    private Long studentSignId;

    /** 学生ID */
    @ApiModelProperty("学生ID")
    private Long studentId;

    /** 班课ID */
    @ApiModelProperty("班课ID")
    private Long courseId;

    /** 签到类型（0点击签到 1手势签到） */
    @ApiModelProperty("签到类型（0点击签到 1手势签到）")
    private String signType;

    /** 签到手势 */
    @ApiModelProperty("签到手势")
    private String gesture;

    /** 签到时间 */
    @ApiModelProperty("签到时间")
    private Date signTime;

    /** 签到IP地址 */
    @ApiModelProperty("签到IP地址")
    private String ipaddr;

    public void setStudentSignId(Long studentSignId) 
    {
        this.studentSignId = studentSignId;
    }

    public Long getStudentSignId() 
    {
        return studentSignId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setSignType(String signType) 
    {
        this.signType = signType;
    }

    public String getSignType() 
    {
        return signType;
    }
    public void setGesture(String gesture) 
    {
        this.gesture = gesture;
    }

    public String getGesture() 
    {
        return gesture;
    }
    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
    }
    public void setIpaddr(String ipaddr) 
    {
        this.ipaddr = ipaddr;
    }

    public String getIpaddr() 
    {
        return ipaddr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentSignId", getStudentSignId())
            .append("studentId", getStudentId())
            .append("courseId", getCourseId())
            .append("signType", getSignType())
            .append("gesture", getGesture())
            .append("signTime", getSignTime())
            .append("ipaddr", getIpaddr())
            .append("remark", getRemark())
            .toString();
    }
}
