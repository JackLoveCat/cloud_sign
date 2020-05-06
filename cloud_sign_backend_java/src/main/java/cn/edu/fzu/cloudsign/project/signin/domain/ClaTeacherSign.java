package cn.edu.fzu.cloudsign.project.signin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 教师发起签到对象 cla_teacher_sign
 * 
 * @author linkai
 * @date 2020-04-26
 */
@ApiModel("教师发起签到")
public class ClaTeacherSign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师发起签到ID */
    @ApiModelProperty("教师发起签到ID")
    private Long teacherSignId;

    /** 教师ID */
    @ApiModelProperty("教师ID")
    private Long teacherId;

    /** 班课ID */
    @ApiModelProperty("班课ID")
    private Long courseId;

    /** 签到类型（0点击签到 1手势签到） */
    @ApiModelProperty("签到类型（0点击签到 1手势签到）")
    private String signType;

    /** 签到手势 */
    @ApiModelProperty("签到手势")
    private String gesture;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopTime;

    /** 发起签到IP地址 */
    @ApiModelProperty("发起签到IP地址")
    private String ipaddr;

    public void setTeacherSignId(Long teacherSignId) 
    {
        this.teacherSignId = teacherSignId;
    }

    public Long getTeacherSignId() 
    {
        return teacherSignId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
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
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setStopTime(Date stopTime) 
    {
        this.stopTime = stopTime;
    }

    public Date getStopTime() 
    {
        return stopTime;
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
            .append("teacherSignId", getTeacherSignId())
            .append("teacherId", getTeacherId())
            .append("courseId", getCourseId())
            .append("signType", getSignType())
            .append("gesture", getGesture())
            .append("startTime", getStartTime())
            .append("stopTime", getStopTime())
            .append("ipaddr", getIpaddr())
            .append("remark", getRemark())
            .toString();
    }
}
