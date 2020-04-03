package cn.edu.fzu.cloudsign.project.cla.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;

/**
 * 班课和教师信息对象 cla_course_teacher
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
public class ClaCourseTeacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班课ID */
    private Long courseId;

    /** 任课教师ID */

    private Long teacherId;

    /** 发起签到次数 */

    private Long sign;

    /** 满经验值 */

    private Long fullExp;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setSign(Long sign) 
    {
        this.sign = sign;
    }

    public Long getSign() 
    {
        return sign;
    }
    public void setFullExp(Long fullExp) 
    {
        this.fullExp = fullExp;
    }

    public Long getFullExp() 
    {
        return fullExp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("sign", getSign())
            .append("fullExp", getFullExp())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
