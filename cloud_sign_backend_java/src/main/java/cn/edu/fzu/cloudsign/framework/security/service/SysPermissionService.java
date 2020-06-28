package cn.edu.fzu.cloudsign.framework.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.fzu.cloudsign.project.system.domain.SysUser;
import cn.edu.fzu.cloudsign.project.system.service.ISysMenuService;

/**
 * 用户权限处理
 * 
 */
@Component
public class SysPermissionService {
	@Autowired
	private ISysMenuService menuService;

	/**
	 * 获取菜单数据权限
	 * 
	 * @param user 用户信息
	 * @return 菜单权限信息
	 */
	public Set<String> getMenuPermission(SysUser user) {
		Set<String> perms = new HashSet<String>();
		// 管理员拥有所有权限
		if (user.isAdmin()) {
			perms.add("*:*:*");
		} else {
			perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
		}
		return perms;
	}
}
