package cn.edu.fzu.cloudsign.project.system.service;

import java.util.List;

import cn.edu.fzu.cloudsign.project.system.domain.SysRole;

/**
 * 角色业务层
 * 
 */
public interface ISysRoleService {
	/**
	 * 根据条件分页查询角色数据
	 * 
	 * @param role 角色信息
	 * @return 角色数据集合信息
	 */
	public List<SysRole> selectRoleList(SysRole role);

	/**
	 * 查询所有角色
	 * 
	 * @return 角色列表
	 */
	public List<SysRole> selectRoleAll();

	/**
	 * 根据用户ID获取角色选择框列表
	 * 
	 * @param userId 用户ID
	 * @return 选中角色ID列表
	 */
	public List<Integer> selectRoleListByUserId(Long userId);

	/**
	 * 通过角色ID查询角色
	 * 
	 * @param roleId 角色ID
	 * @return 角色对象信息
	 */
	public SysRole selectRoleById(Long roleId);

	/**
	 * 校验角色名称是否唯一
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	public String checkRoleNameUnique(SysRole role);

	/**
	 * 校验角色是否允许操作
	 * 
	 * @param role 角色信息
	 */
	public void checkRoleAllowed(SysRole role);

	/**
	 * 通过角色ID查询角色使用数量
	 * 
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public int countUserRoleByRoleId(Long roleId);

	/**
	 * 新增保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	public int insertRole(SysRole role);

	/**
	 * 修改保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	public int updateRole(SysRole role);

	/**
	 * 修改角色状态
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	public int updateRoleStatus(SysRole role);

	/**
	 * 通过角色ID删除角色
	 * 
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public int deleteRoleById(Long roleId);

	/**
	 * 批量删除角色信息
	 * 
	 * @param roleIds 需要删除的角色ID
	 * @return 结果
	 */
	public int deleteRoleByIds(Long[] roleIds);
	
    /**
     * 查询教师和学生角色
     * 
     * @return
     */
    public List<SysRole> selectTeacherAndStudentRole();
}
