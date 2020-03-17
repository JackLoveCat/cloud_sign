package cn.edu.fzu.cloudsign.project.cla.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;

/**
 * 班课信息对象 cla_course
 * 
 * @author fanxuenan
 * @date 2020-03-17
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班课ID */
    private Long courseId;

    /** 课程名称 */

    private String courseName;

    /** 班级名称 */

    private String className;

    /** 班课封面 */

    private String coursePage;

    /** 学期 */

    private String semester;

    /** 学校课表班课 */

    private String curriculum;

    /** 云教材 */

    private String textbook;

    /** 学校 */

    private String university;

    /** 学院 */

    private String academy;

    /** 学习要求 */

    private String studyRequirement;

    /** 教学进度 */

    private String lectureProgress;

    /** 考试安排 */

    private String examArrangement;

    /** 创建者ID */

    private Long createUserId;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    public void setCoursePage(String coursePage) 
    {
        this.coursePage = coursePage;
    }

    public String getCoursePage() 
    {
        return coursePage;
    }
    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }
    public void setCurriculum(String curriculum) 
    {
        this.curriculum = curriculum;
    }

    public String getCurriculum() 
    {
        return curriculum;
    }
    public void setTextbook(String textbook) 
    {
        this.textbook = textbook;
    }

    public String getTextbook() 
    {
        return textbook;
    }
    public void setUniversity(String university) 
    {
        this.university = university;
    }

    public String getUniversity() 
    {
        return university;
    }
    public void setAcademy(String academy) 
    {
        this.academy = academy;
    }

    public String getAcademy() 
    {
        return academy;
    }
    public void setStudyRequirement(String studyRequirement) 
    {
        this.studyRequirement = studyRequirement;
    }

    public String getStudyRequirement() 
    {
        return studyRequirement;
    }
    public void setLectureProgress(String lectureProgress) 
    {
        this.lectureProgress = lectureProgress;
    }

    public String getLectureProgress() 
    {
        return lectureProgress;
    }
    public void setExamArrangement(String examArrangement) 
    {
        this.examArrangement = examArrangement;
    }

    public String getExamArrangement() 
    {
        return examArrangement;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("className", getClassName())
            .append("coursePage", getCoursePage())
            .append("semester", getSemester())
            .append("curriculum", getCurriculum())
            .append("textbook", getTextbook())
            .append("university", getUniversity())
            .append("academy", getAcademy())
            .append("studyRequirement", getStudyRequirement())
            .append("lectureProgress", getLectureProgress())
            .append("examArrangement", getExamArrangement())
            .append("createUserId", getCreateUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
