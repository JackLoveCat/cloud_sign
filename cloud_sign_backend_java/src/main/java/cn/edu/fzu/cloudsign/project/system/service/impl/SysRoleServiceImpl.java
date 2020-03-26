package cn.edu.fzu.cloudsign.project.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.fzu.cloudsign.common.constant.UserConstants;
import cn.edu.fzu.cloudsign.common.exception.CustomException;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.project.system.domain.SysRole;
import cn.edu.fzu.cloudsign.project.system.domain.SysRoleMenu;
import cn.edu.fzu.cloudsign.project.system.mapper.SysRoleMapper;
import cn.edu.fzu.cloudsign.project.system.mapper.SysRoleMenuMapper;
import cn.edu.fzu.cloudsign.project.system.mapper.SysUserRoleMapper;
import cn.edu.fzu.cloudsign.project.system.service.ISysRoleService;

/**
 * 角色 业务层处理
 * 
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private SysRoleMenuMapper roleMenuMapper;

	@Autowired
	private SysUserRoleMapper userRoleMapper;

	/**
	 * 根据条件分页查询角色数据
	 * 
	 * @param role 角色信息
	 * @return 角色数据集合信息
	 */
	@Override
	public List<SysRole> selectRoleList(SysRole role) {
		return roleMapper.selectRoleList(role);
	}

	/**
	 * 查询所有角色
	 * 
	 * @return 角色列表
	 */
	public List<SysRole> selectRoleAll() {
		return roleMapper.selectRoleAll();
	}

	/**
	 * 根据用户ID获取角色选择框列表
	 * 
	 * @param userId 用户ID
	 * @return 选中角色ID列表
	 */
	public List<Integer> selectRoleListByUserId(Long userId) {
		return roleMapper.selectRoleListByUserId(userId);
	}

	/**
	 * 通过角色ID查询角色
	 * 
	 * @param roleId 角色ID
	 * @return 角色对象信息
	 */
	public SysRole selectRoleById(Long roleId) {
		return roleMapper.selectRoleById(roleId);
	}

	/**
	 * 校验角色名称是否唯一
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	public String checkRoleNameUnique(SysRole role) {
		Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
		SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
		if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验角色是否允许操作
	 * 
	 * @param role 角色信息
	 */
	public void checkRoleAllowed(SysRole role) {
		if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin()) {
			throw new CustomException("不允许操作超级管理员角色");
		}
	}

	/**
	 * 通过角色ID查询角色使用数量
	 * 
	 * @param roleId 角色ID
	 * @return 结果
	 */
	@Override
	public int countUserRoleByRoleId(Long roleId) {
		return userRoleMapper.countUserRoleByRoleId(roleId);
	}

	/**
	 * 新增保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertRole(SysRole role) {
		// 新增角色信息
		roleMapper.insertRole(role);
		return insertRoleMenu(role);
	}

	/**
	 * 修改保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateRole(SysRole role) {
		// 修改角色信息
		roleMapper.updateRole(role);
		// 删除角色与菜单关联
		roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
		return insertRoleMenu(role);
	}

	/**
	 * 修改角色状态
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	public int updateRoleStatus(SysRole role) {
		return roleMapper.updateRole(role);
	}

	/**
	 * 新增角色菜单信息
	 * 
	 * @param role 角色对象
	 */
	public int insertRoleMenu(SysRole role) {
		int rows = 1;
		// 新增用户与角色管理
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		for (Long menuId : role.getMenuIds()) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setRoleId(role.getRoleId());
			rm.setMenuId(menuId);
			list.add(rm);
		}
		if (list.size() > 0) {
			rows = roleMenuMapper.batchRoleMenu(list);
		}
		return rows;
	}

	/**
	 * 通过角色ID删除角色
	 * 
	 * @param roleId 角色ID
	 * @return 结果
	 */
	@Override
	public int deleteRoleById(Long roleId) {
		return roleMapper.deleteRoleById(roleId);
	}

	/**
	 * 批量删除角色信息
	 * 
	 * @param roleIds 需要删除的角色ID
	 * @return 结果
	 */
	public int deleteRoleByIds(Long[] roleIds) {
		for (Long roleId : roleIds) {
			checkRoleAllowed(new SysRole(roleId));
			SysRole role = selectRoleById(roleId);
			if (countUserRoleByRoleId(roleId) > 0) {
				throw new CustomException(String.format("%1$s已分配,不能删除", role.getRoleName()));
			}
		}
		return roleMapper.deleteRoleByIds(roleIds);
	}

	/**
	 * 查询教师和学生角色
	 * 
	 * @return
	 */
	@Override
	public List<SysRole> selectTeacherAndStudentRole() {
		return roleMapper.selectTeacherAndStudentRole();
	}
}
