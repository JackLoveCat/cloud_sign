package cn.edu.fzu.cloudsign.project.system.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fzu.cloudsign.common.constant.Constants;
import cn.edu.fzu.cloudsign.common.constant.UserConstants;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;
import cn.edu.fzu.cloudsign.common.utils.ServletUtils;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;
import cn.edu.fzu.cloudsign.framework.security.service.SysLoginService;
import cn.edu.fzu.cloudsign.framework.security.service.SysPermissionService;
import cn.edu.fzu.cloudsign.framework.security.service.TokenService;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.project.system.domain.SysUser;
import cn.edu.fzu.cloudsign.project.system.domain.UserIdentifier;
import cn.edu.fzu.cloudsign.project.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 登录验证
 * 
 */
@Api("登录验证")
@RestController
public class SysLoginController extends BaseController {
	@Autowired
	private SysLoginService loginService;

	@Autowired
	private ISysUserService userService;

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 登录方法
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param captcha  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	@ApiOperation("登录")
	@PostMapping("/login")
	public AjaxResult login(@Validated @RequestBody UserIdentifier userIdentifier) {
		AjaxResult ajax = AjaxResult.success();
		SysUser user = userService.selectUserByAccount(userIdentifier.getAccount());
		if (StringUtils.isNull(user) || StringUtils.isEmpty(user.getUserName())) {
			return AjaxResult.error("用户不存在:" + userIdentifier.getAccount());
		}
		// 生成令牌
		String token = loginService.login(user.getUserName(), userIdentifier.getPassward(), null, null);
		ajax.put(Constants.TOKEN, token);
		return ajax;
	}

	/**
	 * 登录方法
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param captcha  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	/*
	 * @ApiOperation("登录")
	 * 
	 * @PostMapping("/login") public AjaxResult login(String username, String
	 * password, String code, String uuid) { AjaxResult ajax = AjaxResult.success();
	 * // 生成令牌 String token = loginService.login(username, password, code, uuid);
	 * ajax.put(Constants.TOKEN, token); return ajax; }
	 */

	/**
	 * 登录方法
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param captcha  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	/*
	 * @ApiOperation("用手机号登录")
	 * 
	 * @PostMapping("/loginbyphone") public AjaxResult loginByPhoneNum(String
	 * phonenum, String password, String code, String uuid) { AjaxResult ajax =
	 * AjaxResult.success(); SysUser user =
	 * userService.selectUserByPhoneNum(phonenum); if (StringUtils.isNull(user) ||
	 * StringUtils.isEmpty(user.getUserName())) { return
	 * AjaxResult.error("用户登录失败，手机号码:" + phonenum + ",不存在"); } // 生成令牌 String token
	 * = loginService.login(user.getUserName(), password, code, uuid);
	 * ajax.put(Constants.TOKEN, token); return ajax; }
	 */

	/**
	 * 登录方法
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param captcha  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	/*
	 * @ApiOperation("用邮箱登录")
	 * 
	 * @PostMapping("/loginbyemail") public AjaxResult loginByEmail(String email,
	 * String password, String code, String uuid) { AjaxResult ajax =
	 * AjaxResult.success(); SysUser user = userService.selectUserByEmail(email); if
	 * (StringUtils.isNull(user) || StringUtils.isEmpty(user.getUserName())) {
	 * return AjaxResult.error("用户登录失败，邮箱:" + email + ",不存在"); } // 生成令牌 String
	 * token = loginService.login(user.getUserName(), password, code, uuid);
	 * ajax.put(Constants.TOKEN, token); return ajax; }
	 */

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation("用户注册")
	@Log(title = "用户管理", businessType = BusinessType.INSERT)
	@PostMapping("/register")
	public AjaxResult register(@Validated @RequestBody SysUser user) {
		if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkStudentNumUnique(user))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，学号已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
		}
		user.setCreateBy(user.getUserName());
		if (StringUtils.isEmpty(user.getPassword())) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，用户密码不能为空");
		}
		user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
		return toAjax(userService.insertUser(user));
	}

	/**
	 * 获取用户信息
	 * 
	 * @return 用户信息
	 */
//	@ApiOperation("获取当前登录用户信息")
	@GetMapping("getInfo")
	public AjaxResult getInfo() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		// 权限集合
		Set<String> permissions = permissionService.getMenuPermission(user);
		AjaxResult ajax = AjaxResult.success();
		ajax.put("user", user);
		ajax.put("permissions", permissions);
		return ajax;
	}
}
