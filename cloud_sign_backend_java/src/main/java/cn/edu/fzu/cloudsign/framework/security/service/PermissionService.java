package cn.edu.fzu.cloudsign.framework.security.service;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.fzu.cloudsign.common.utils.ServletUtils;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;

/**
 * TODO 增加自定义权限实现
 */
@Service("ss")
public class PermissionService {
	/** 所有权限标识 */
	private static final String ALL_PERMISSION = "*:*:*";

	private static final String PERMISSION_DELIMETER = ",";

	@Autowired
	private TokenService tokenService;

	/**
	 * 验证用户是否具备某权限
	 * 
	 * @param permission 权限字符串
	 * @return 用户是否具备某权限
	 */
	public boolean hasPermi(String permission) {
//		return true;
		if (StringUtils.isEmpty(permission)) {
			return false;
		}
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions())) {
			return false;
		}
		return hasPermissions(loginUser.getPermissions(), permission);
	}

	/**
	 * 验证用户是否不具备某权限，与 hasPermi逻辑相反
	 *
	 * @param permission 权限字符串
	 * @return 用户是否不具备某权限
	 */
	public boolean lacksPermi(String permission) {
//		return true;
		return hasPermi(permission) != true;
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
	 * @return 用户是否具有以下任意一个权限
	 */
	public boolean hasAnyPermi(String permissions) {
//		return true;
		if (StringUtils.isEmpty(permissions)) {
			return false;
		}
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions())) {
			return false;
		}
		Set<String> authorities = loginUser.getPermissions();
		for (String permission : permissions.split(PERMISSION_DELIMETER)) {
			if (permission != null && hasPermissions(authorities, permission)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否包含权限
	 * 
	 * @param permissions 权限列表
	 * @param permission  权限字符串
	 * @return 用户是否具备某权限
	 */
	private boolean hasPermissions(Set<String> permissions, String permission) {
//		return true;
		return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
	}
}
