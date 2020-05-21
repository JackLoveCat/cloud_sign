package cn.edu.fzu.cloudsign.project.cla.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("班课的学员")
public class ClaCourseMember implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 班课ID */
	@ApiModelProperty("班课ID")
	private Long courseId;

	/** 课程名称 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;

	/** 学校院系名称 */
	@ApiModelProperty(value = "学校院系名称")
	private String uniacadaName;

	/** 学校名称 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String universityName;

	/** 院系名称 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String academyName;

	/** 教师名称 */
	@ApiModelProperty(value = "教师名称")
	private String teacherName;

	/** 学生名称 */
	@ApiModelProperty(value = "学生名称")
	private String studentName;

	/** 学生学号 */
	@ApiModelProperty(value = "学生学号")
	private String studentNo;

	/** 学生手机号 */
	@ApiModelProperty(value = "学生手机号")
	private String studentPhonenumber;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUniacadaName() {
		return universityName + " " + academyName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentPhonenumber() {
		return studentPhonenumber;
	}

	public void setStudentPhonenumber(String studentPhonenumber) {
		this.studentPhonenumber = studentPhonenumber;
	}

}
