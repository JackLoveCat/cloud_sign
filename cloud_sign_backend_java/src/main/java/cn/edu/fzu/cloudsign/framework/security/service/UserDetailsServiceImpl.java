package cn.edu.fzu.cloudsign.framework.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.edu.fzu.cloudsign.common.enums.UserStatus;
import cn.edu.fzu.cloudsign.common.exception.BaseException;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;
import cn.edu.fzu.cloudsign.project.system.domain.SysUser;
import cn.edu.fzu.cloudsign.project.system.service.ISysUserService;

/**
 * 用户验证处理
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private ISysUserService userService;

	@Autowired
	private SysPermissionService permissionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userService.selectUserByUserName(username);
		if (StringUtils.isNull(user)) {
			log.info("登录用户：{} 不存在.", username);
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		} else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
			log.info("登录用户：{} 已被删除.", username);
			throw new BaseException("对不起，您的账号：" + username + " 已被删除");
		} else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
			log.info("登录用户：{} 已被停用.", username);
			throw new BaseException("对不起，您的账号：" + username + " 已停用");
		}

		return createLoginUser(user);
	}

	/**
	 * 
	 * 实现根据登录用户获取相应权限
	 * 
	 * @return
	 */
	public UserDetails createLoginUser(SysUser user) {
		return new LoginUser(user, permissionService.getMenuPermission(user));
	}

}
