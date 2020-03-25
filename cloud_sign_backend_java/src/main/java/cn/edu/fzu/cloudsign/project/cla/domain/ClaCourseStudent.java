package cn.edu.fzu.cloudsign.project.cla.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;

/**
 * 班课和学生信息对象 cla_course_student
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
public class ClaCourseStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班课ID */
    private Long courseId;

    /** 学生ID（用户ID） */
    private Long studentId;

    /** 实际签到次数 */

    private Long actualSign;

    /** 实际经验值 */

    private Long actualExp;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setActualSign(Long actualSign) 
    {
        this.actualSign = actualSign;
    }

    public Long getActualSign() 
    {
        return actualSign;
    }
    public void setActualExp(Long actualExp) 
    {
        this.actualExp = actualExp;
    }

    public Long getActualExp() 
    {
        return actualExp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("studentId", getStudentId())
            .append("actualSign", getActualSign())
            .append("actualExp", getActualExp())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
