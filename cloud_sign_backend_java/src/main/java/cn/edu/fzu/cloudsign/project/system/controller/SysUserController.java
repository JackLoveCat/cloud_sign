package cn.edu.fzu.cloudsign.project.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fzu.cloudsign.common.constant.UserConstants;
import cn.edu.fzu.cloudsign.common.utils.SecurityUtils;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;
import cn.edu.fzu.cloudsign.project.system.domain.SysUser;
import cn.edu.fzu.cloudsign.project.system.service.ISysRoleService;
import cn.edu.fzu.cloudsign.project.system.service.ISysUserService;

/**
 * 用户信息
 * 
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
	@Autowired
	private ISysUserService userService;

	@Autowired
	private ISysRoleService roleService;

	/**
	 * 获取用户列表
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@GetMapping("/list")
	public TableDataInfo list(SysUser user) {
		startPage();
		List<SysUser> list = userService.selectUserList(user);
		return getDataTable(list);
	}

	/**
	 * 根据用户编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@GetMapping(value = { "/", "/{userId}" })
	public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
		AjaxResult ajax = AjaxResult.success();
		ajax.put("allRoles", roleService.selectRoleAll());
		if (StringUtils.isNotNull(userId)) {
			ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
			ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
		}
		return ajax;
	}

	/**
	 * 新增用户
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@Log(title = "用户管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysUser user) {
		if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkStudentNumUnique(user))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，学号已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，用户密码不能为空");
		}
		user.setCreateBy(SecurityUtils.getUsername());
		user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
		return toAjax(userService.insertUser(user));
	}

	/**
	 * 修改用户
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysUser user) {
		userService.checkUserAllowed(user);
		if (UserConstants.NOT_UNIQUE.equals(userService.checkStudentNumUnique(user))) {
			return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，学号已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
			return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
			return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
		}
		user.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(userService.updateUser(user));
	}

	/**
	 * 删除用户
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
	public AjaxResult remove(@PathVariable Long[] userIds) {
		return toAjax(userService.deleteUserByIds(userIds));
	}

	/**
	 * 重置密码
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping("/resetPwd")
	public AjaxResult resetPwd(@RequestBody SysUser user) {
		userService.checkUserAllowed(user);
		user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
		user.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(userService.resetPwd(user));
	}

	/**
	 * 状态修改
	 */
	@PreAuthorize("@ss.hasPermi('system:user')")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PutMapping("/changeStatus")
	public AjaxResult changeStatus(@RequestBody SysUser user) {
		userService.checkUserAllowed(user);
		user.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(userService.updateUserStatus(user));
	}

}