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
import cn.edu.fzu.cloudsign.common.utils.ServletUtils;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;
import cn.edu.fzu.cloudsign.framework.security.service.TokenService;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.project.system.domain.SysMenu;
import cn.edu.fzu.cloudsign.project.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单信息
 * 
 */
@Api("菜单管理")
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
	@Autowired
	private ISysMenuService menuService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 获取菜单列表
	 */
	@ApiOperation("获取菜单列表")
	@GetMapping("/list")
	public AjaxResult list(SysMenu menu) {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		Long userId = loginUser.getUser().getUserId();
		List<SysMenu> menus = menuService.selectMenuList(menu, userId);
		return AjaxResult.success(menus);
	}

	/**
	 * 获取菜单下拉树列表
	 */
	@ApiOperation("获取菜单树")
	@GetMapping("/treeselect")
	public AjaxResult treeselect(SysMenu menu) {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		Long userId = loginUser.getUser().getUserId();
		List<SysMenu> menus = menuService.selectMenuList(menu, userId);
		return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
	}

	/**
	 * 根据菜单编号获取详细信息
	 */
	@ApiOperation("根据菜单编号获取详细信息")
	@PreAuthorize("@ss.hasPermi('system:menu:query')")
	@GetMapping(value = "/{menuId}")
	public AjaxResult getInfo(@PathVariable Long menuId) {
		return AjaxResult.success(menuService.selectMenuById(menuId));
	}

	/**
	 * 新增菜单
	 */
	@ApiOperation("新增菜单")
	@PreAuthorize("@ss.hasPermi('system:menu:add')")
	@Log(title = "菜单管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody SysMenu menu) {
		if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
			return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
		}
		menu.setCreateBy(SecurityUtils.getUsername());
		return toAjax(menuService.insertMenu(menu));
	}

	/**
	 * 修改菜单
	 */
	@ApiOperation("修改菜单")
	@PreAuthorize("@ss.hasPermi('system:menu:edit')")
	@Log(title = "菜单管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody SysMenu menu) {
		if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
			return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
		}
		menu.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(menuService.updateMenu(menu));
	}

	/**
	 * 删除菜单
	 */
	@ApiOperation("删除菜单")
	@PreAuthorize("@ss.hasPermi('system:menu:remove')")
	@Log(title = "菜单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{menuId}")
	public AjaxResult remove(@PathVariable("menuId") Long menuId) {
		if (menuService.hasChildByMenuId(menuId)) {
			return AjaxResult.error("存在子菜单,不允许删除");
		}
		if (menuService.checkMenuExistRole(menuId)) {
			return AjaxResult.error("菜单已分配,不允许删除");
		}
		return toAjax(menuService.deleteMenuById(menuId));
	}
}