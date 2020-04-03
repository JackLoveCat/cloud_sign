package cn.edu.fzu.cloudsign.project.system.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;

/**
 * 菜单权限表 sys_menu
 * 
 */
public class SysMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 菜单ID */
	private Long menuId;

	/** 菜单名称 */
	private String menuName;

	/** 父菜单名称 */
	private String parentName;

	/** 父菜单ID */
	private Long parentId;

	/** 菜单图标 */
	private String icon;

	/** 菜单链接 */
	private String link;

	/** 显示顺序 */
	private String orderNum;

	/** 是否菜单 */
	private String isMenu;

	private String isPage;

	/** 权限标识 */
	private String perms;

	/** 子菜单 */
	private List<SysMenu> children = new ArrayList<SysMenu>();

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@NotBlank(message = "菜单名称不能为空")
	@Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@NotBlank(message = "显示顺序不能为空")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("menuId", getMenuId())
				.append("menuName", getMenuName()).append("parentId", getParentId()).append("orderNum", getOrderNum())
				.append("icon", getIcon()).append("perms", getPerms()).append("createBy", getCreateBy())
				.append("createTime", getCreateTime()).append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime()).append("remark", getRemark()).toString();
	}
}
