package cn.edu.fzu.cloudsign.project.cla.domain;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;

/**
 * 班课信息对象 cla_course
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */
public class ClaCourse extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 班课ID */
	private Long courseId;

	/** 课程名称 */

	private String courseName;

	/** 班课号 */

	private String courseNum;

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

	/** 学校院系ID */

	private Long uniacadaId;

	/** 学习要求 */

	private String studyRequirement;

	/** 教学进度 */

	private String lectureProgress;

	/** 考试安排 */

	private String examArrangement;

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@NotBlank(message = "课程名称名称不能为空")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@NotBlank(message = "班级名称不能为空")
	public String getClassName() {
		return className;
	}

	public void setCoursePage(String coursePage) {
		this.coursePage = coursePage;
	}

	public String getCoursePage() {
		return coursePage;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	public String getTextbook() {
		return textbook;
	}

	public void setUniacadaId(Long uniacadaId) {
		this.uniacadaId = uniacadaId;
	}

	public Long getUniacadaId() {
		return uniacadaId;
	}

	public void setStudyRequirement(String studyRequirement) {
		this.studyRequirement = studyRequirement;
	}

	public String getStudyRequirement() {
		return studyRequirement;
	}

	public void setLectureProgress(String lectureProgress) {
		this.lectureProgress = lectureProgress;
	}

	public String getLectureProgress() {
		return lectureProgress;
	}

	public void setExamArrangement(String examArrangement) {
		this.examArrangement = examArrangement;
	}

	public String getExamArrangement() {
		return examArrangement;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("courseId", getCourseId())
				.append("courseName", getCourseName()).append("courseNum", getCourseNum())
				.append("className", getClassName()).append("coursePage", getCoursePage())
				.append("semester", getSemester()).append("curriculum", getCurriculum())
				.append("textbook", getTextbook()).append("uniacadaId", getUniacadaId())
				.append("studyRequirement", getStudyRequirement()).append("lectureProgress", getLectureProgress())
				.append("examArrangement", getExamArrangement()).append("createBy", getCreateBy())
				.append("createTime", getCreateTime()).append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime()).append("remark", getRemark()).toString();
	}
}
