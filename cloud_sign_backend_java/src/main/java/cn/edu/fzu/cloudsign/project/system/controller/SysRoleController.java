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
import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;
import cn.edu.fzu.cloudsign.project.system.domain.SysRole;
import cn.edu.fzu.cloudsign.project.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色信息
 * 
 */
@Api("角色管理")
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
	@Autowired
	private ISysRoleService roleService;

	@ApiOperation("获取角色列表")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@GetMapping("/list")
	public TableDataInfo list(SysRole role) {
		startPage();
		List<SysRole> list = roleService.selectRoleList(role);
		return getDataTable(list);
	}

	/**
	 * 根据角色编号获取详细信息
	 */
	@ApiOperation("根据角色编号获取详细信息")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@GetMapping(value = "/{roleId}")
	public AjaxResult getInfo(@PathVariable Long roleId) {
		return AjaxResult.success(roleService.selectRoleById(roleId));
	}

	/**
	 * 新增角色
	 */
	@ApiOperation("新增角色")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@Log(title = "角色管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysRole role) {
		if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
			return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
		}
		role.setCreateBy(SecurityUtils.getUsername());
		return toAjax(roleService.insertRole(role));

	}

	/**
	 * 修改保存角色
	 */
	@ApiOperation("修改角色")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysRole role) {
		roleService.checkRoleAllowed(role);
		if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
			return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
		}
		role.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(roleService.updateRole(role));
	}

	/**
	 * 状态修改
	 */
	@ApiOperation("角色状态修改")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
	@PutMapping("/changeStatus")
	public AjaxResult changeStatus(@RequestBody SysRole role) {
		roleService.checkRoleAllowed(role);
		role.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(roleService.updateRoleStatus(role));
	}

	/**
	 * 删除角色
	 */
	@ApiOperation("删除角色")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@Log(title = "角色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
	public AjaxResult remove(@PathVariable Long[] roleIds) {
		return toAjax(roleService.deleteRoleByIds(roleIds));
	}

	/**
	 * 获取角色选择框列表
	 */
	@PreAuthorize("@ss.hasPermi('system:role')")
	@GetMapping("/optionselect")
	public AjaxResult optionselect() {
		return AjaxResult.success(roleService.selectRoleAll());
	}

	/**
	 * 查询教师和学生角色
	 */
	@ApiOperation("查询教师和学生角色")
	@PreAuthorize("@ss.hasPermi('system:role')")
	@GetMapping(value = "/teacherandstudent")
	public AjaxResult getTeacherAndStudentRole() {
		return AjaxResult.success(roleService.selectTeacherAndStudentRole());
	}
}