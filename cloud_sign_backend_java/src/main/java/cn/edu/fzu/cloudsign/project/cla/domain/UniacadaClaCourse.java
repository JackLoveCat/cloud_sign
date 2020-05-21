package cn.edu.fzu.cloudsign.project.cla.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("学校院系的开课列表")
public class UniacadaClaCourse implements Serializable {
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

	/** 学生数量 */
	@ApiModelProperty(value = "学生数量")
	private Integer studentNum;

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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
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

}
