package cn.edu.fzu.cloudsign.project.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色表 sys_role
 * 
 */
@ApiModel("角色")
public class SysRole extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 角色ID */
	@ApiModelProperty("角色ID")
	private Long roleId;

	/** 角色名称 */
	@ApiModelProperty(value = "角色名称", required = true)
	private String roleName;

	/** 是否固定角色 */
	@ApiModelProperty(value = "是否固定角色", required = true)
	private String editable;

	/** 角色排序 */
	@ApiModelProperty(value = "角色排序", required = true)
	private String roleSort;

	/** 角色状态（0正常 1停用） */
	@ApiModelProperty(value = "角色状态（0正常 1停用）", required = true)
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	@ApiModelProperty(hidden = true)
	private String delFlag;

	/** 用户是否存在此角色标识 默认不存在 */
	@ApiModelProperty(hidden = true)
	private boolean flag = false;

	/** 菜单组 */
	@ApiModelProperty("维护角色时，传入角色有权限访问的菜单ID")
	private Long[] menuIds;

	public SysRole() {

	}

	public SysRole(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	public boolean isAdmin() {
		return isAdmin(this.roleId);
	}

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	public static boolean isAdmin(Long roleId) {
		return roleId != null && 1L == roleId;
	}

	@NotBlank(message = "角色名称不能为空")
	@Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@NotBlank(message = "显示顺序不能为空")
	public String getRoleSort() {
		return roleSort;
	}

	public void setRoleSort(String roleSort) {
		this.roleSort = roleSort;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Long[] menuIds) {
		this.menuIds = menuIds;
	}

	@NotBlank(message = "是否固定角色不能为空")
	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("roleId", getRoleId())
				.append("roleName", getRoleName()).append("editable", getEditable()).append("roleSort", getRoleSort())
				.append("dataScope", getDataScope()).append("status", getStatus()).append("delFlag", getDelFlag())
				.append("createBy", getCreateBy()).append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).append("remark", getRemark())
				.toString();
	}
}
