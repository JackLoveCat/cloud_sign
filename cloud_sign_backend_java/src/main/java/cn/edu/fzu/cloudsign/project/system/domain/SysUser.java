package cn.edu.fzu.cloudsign.project.system.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户对象 sys_user
 * 
 */
@ApiModel("用户")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	@ApiModelProperty("用户ID")
	private Long userId;

	/** 学校院系ID */
	@ApiModelProperty("学校院系ID")
	private Long uniacadaId;

	/** 用户账号 */
	@ApiModelProperty(value = "用户账号", required = true)
	private String userName;

	/** 用户昵称 */
	@ApiModelProperty(value = "用户昵称", required = true)
	private String nickName;

	/** 学号 */
	@ApiModelProperty("学号")
	private String studentNum;

	/** 用户邮箱 */
	@ApiModelProperty("用户邮箱")
	private String email;

	/** 手机号码 */
	@ApiModelProperty("手机号码")
	private String phonenumber;

	/** 用户头像 */
	@ApiModelProperty("用户头像")
	private String avatar;

	/** 密码 */
	@ApiModelProperty("密码")
	private String password;

	/** 盐加密 */
	@ApiModelProperty(hidden = true)
	private String salt;

	/** 帐号状态（0正常 1停用） */
	@ApiModelProperty("帐号状态（0正常 1停用）")
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	@ApiModelProperty(hidden = true)
	private String delFlag;

	/** 最后登陆IP */
	@ApiModelProperty("最后登陆IP")
	private String loginIp;

	/** 最后登陆时间 */
	@ApiModelProperty("最后登陆时间 ")
	private Date loginDate;

	/** 学校院系 */
	@ApiModelProperty("学校院系")
	private SysUniacada uniacada;

	/** 角色对象 */
	@ApiModelProperty("所拥有角色列表")
	private List<SysRole> roles;

	/** 角色组 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Long[] roleIds;

	public SysUser() {

	}

	public SysUser(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ApiModelProperty(hidden = true)
	public boolean isAdmin() {
		return isAdmin(this.userId);
	}

	@ApiModelProperty(hidden = true)
	public static boolean isAdmin(Long userId) {
		return userId != null && 1L == userId;
	}

	@Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@NotBlank(message = "用户账号不能为空")
	@Pattern(regexp = "^.*[^\\d].*$", message = "用户账号不能为纯数字")
	@Pattern(regexp = "^(?!.*@).*$", message = "用户账号不能包含@")
	@Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
	@Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号码格式不正确")
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@JsonProperty
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getUniacadaId() {
		return uniacadaId;
	}

	public void setUniacadaId(Long uniacadaId) {
		this.uniacadaId = uniacadaId;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public SysUniacada getUniacada() {
		return uniacada;
	}

	public void setUniacada(SysUniacada uniacada) {
		this.uniacada = uniacada;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId())
				.append("uniacadaId", getUniacadaId()).append("userName", getUserName())
				.append("nickName", getNickName()).append("email", getEmail()).append("phonenumber", getPhonenumber())
				.append("studentNum", getStudentNum()).append("avatar", getAvatar()).append("password", getPassword())
				.append("salt", getSalt()).append("status", getStatus()).append("delFlag", getDelFlag())
				.append("loginIp", getLoginIp()).append("loginDate", getLoginDate()).append("createBy", getCreateBy())
				.append("createTime", getCreateTime()).append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime()).append("remark", getRemark()).append("uniacada", getUniacada())
				.toString();
	}

}
