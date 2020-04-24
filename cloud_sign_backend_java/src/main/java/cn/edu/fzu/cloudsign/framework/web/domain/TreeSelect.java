package cn.edu.fzu.cloudsign.framework.web.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import cn.edu.fzu.cloudsign.project.system.domain.SysMenu;

/**
 * Treeselect树结构实体类
 * 
 */
public class TreeSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 节点ID */
	private Long id;

	/** 节点名称 */
	private String label;

	/** 菜单链接 */
	private String url;

	/** 菜单啊图标 */
	private String icon;

	/** 子节点 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<TreeSelect> children;

	public TreeSelect() {

	}

	public TreeSelect(SysMenu menu) {
		this.id = menu.getMenuId();
		this.label = menu.getMenuName();
		this.url = menu.getLink();
		this.icon = menu.getIcon();
		this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<TreeSelect> getChildren() {
		return children;
	}

	public void setChildren(List<TreeSelect> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
