package cn.edu.fzu.cloudsign.project.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.fzu.cloudsign.common.constant.UserConstants;
import cn.edu.fzu.cloudsign.common.exception.CustomException;
import cn.edu.fzu.cloudsign.common.utils.StringUtils;
import cn.edu.fzu.cloudsign.project.cla.mapper.ClaCourseMapper;
import cn.edu.fzu.cloudsign.project.system.domain.SysRole;
import cn.edu.fzu.cloudsign.project.system.domain.SysUser;
import cn.edu.fzu.cloudsign.project.system.domain.SysUserRole;
import cn.edu.fzu.cloudsign.project.system.mapper.SysRoleMapper;
import cn.edu.fzu.cloudsign.project.system.mapper.SysUserMapper;
import cn.edu.fzu.cloudsign.project.system.mapper.SysUserRoleMapper;
import cn.edu.fzu.cloudsign.project.system.service.ISysUserService;

/**
 * 用户 业务层处理
 * 
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
	private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private SysUserRoleMapper userRoleMapper;

	@Autowired
	private ClaCourseMapper claCourseMapper;

	/**
	 * 根据条件分页查询用户列表
	 * 
	 * @param user 用户信息
	 * @return 用户信息集合信息
	 */
	@Override
	public List<SysUser> selectUserList(SysUser user) {
		return userMapper.selectUserList(user);
	}

	/**
	 * 通过用户名查询用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	@Override
	public SysUser selectUserByUserName(String userName) {
		return userMapper.selectUserByUserName(userName);
	}

	/**
	 * 通过用户ID查询用户
	 * 
	 * @param userId 用户ID
	 * @return 用户对象信息
	 */
	@Override
	public SysUser selectUserById(Long userId) {
		return userMapper.selectUserById(userId);
	}

	/**
	 * 查询用户所属角色组
	 * 
	 * @param userName 用户名
	 * @return 结果
	 */
	@Override
	public String selectUserRoleGroup(String userName) {
		List<SysRole> list = roleMapper.selectRolesByUserName(userName);
		StringBuffer idsStr = new StringBuffer();
		for (SysRole role : list) {
			idsStr.append(role.getRoleName()).append(",");
		}
		if (StringUtils.isNotEmpty(idsStr.toString())) {
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

	/**
	 * 校验用户名称是否唯一
	 * 
	 * @param userName 用户名称
	 * @return 结果
	 */
	@Override
	public String checkUserNameUnique(String userName) {
		int count = userMapper.checkUserNameUnique(userName);
		if (count > 0) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验学号是否唯一
	 * 
	 * @param studentNum
	 * @return
	 */
	@Override
	public String checkStudentNumUnique(SysUser user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		SysUser info = userMapper.checkStudentNumUnique(user.getStudentNum());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验用户名称是否唯一
	 *
	 * @param user 用户信息
	 * @return
	 */
	@Override
	public String checkPhoneUnique(SysUser user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验email是否唯一
	 *
	 * @param user 用户信息
	 * @return
	 */
	@Override
	public String checkEmailUnique(SysUser user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		SysUser info = userMapper.checkEmailUnique(user.getEmail());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验用户是否允许操作
	 * 
	 * @param user 用户信息
	 */
	public void checkUserAllowed(SysUser user) {
		if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
			throw new CustomException("不允许操作超级管理员用户");
		}
	}

	/**
	 * 新增保存用户信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertUser(SysUser user) {
		// 新增用户信息
		int rows = userMapper.insertUser(user);
		// 新增用户与角色管理
		insertUserRole(user);
		return rows;
	}

	/**
	 * 修改保存用户信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateUser(SysUser user) {
		Long userId = user.getUserId();
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId(userId);
		// 新增用户与角色管理
		insertUserRole(user);
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户状态
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	public int updateUserStatus(SysUser user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户基本信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	public int updateUserProfile(SysUser user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户头像
	 * 
	 * @param userId 用户ID
	 * @param avatar 头像地址
	 * @return 结果
	 */
	public boolean updateUserAvatar(String userName, String avatar) {
		return userMapper.updateUserAvatar(userName, avatar) > 0;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	public int resetPwd(SysUser user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 结果
	 */
	@Override
	public int resetUserPwd(String userName, String password) {
		return userMapper.resetUserPwd(userName, password);
	}

	/**
	 * 新增用户角色信息
	 * 
	 * @param user 用户对象
	 */
	public void insertUserRole(SysUser user) {
		Long[] roles = user.getRoleIds();
		if (StringUtils.isNotNull(roles)) {
			// 新增用户与角色管理
			List<SysUserRole> list = new ArrayList<SysUserRole>();
			for (Long roleId : roles) {
				SysUserRole ur = new SysUserRole();
				ur.setUserId(user.getUserId());
				ur.setRoleId(roleId);
				list.add(ur);
			}
			if (list.size() > 0) {
				userRoleMapper.batchUserRole(list);
			}
		}
	}

	/**
	 * 通过用户ID删除用户
	 * 
	 * @param userId 用户ID
	 * @return 结果
	 */
	@Override
	public int deleteUserById(Long userId) {
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId(userId);
		return userMapper.deleteUserById(userId);
	}

	/**
	 * 批量删除用户信息
	 * 
	 * @param userIds 需要删除的用户ID
	 * @return 结果
	 */
	@Override
	public int deleteUserByIds(Long[] userIds) {
		for (Long userId : userIds) {
			checkUserAllowed(new SysUser(userId));
			if (countClaCourseTeacherByUserId(userId) > 0) {
				throw new CustomException("用户已有创建的课程，不能删除");
			}
			if (countClaCourseStudentByUserId(userId) > 0) {
				throw new CustomException("用户已有加入的课程，不能删除");
			}
		}

		return userMapper.deleteUserByIds(userIds);
	}

	/**
	 * 查询教师创建的课程
	 * 
	 * @param userId
	 * @return
	 */
	private int countClaCourseTeacherByUserId(Long userId) {
		return claCourseMapper.countClaCourseTeacherByUserId(userId);
	}

	/**
	 * 查询学生加入的课程
	 * 
	 * @param userId
	 * @return
	 */
	private int countClaCourseStudentByUserId(Long userId) {
		return claCourseMapper.countClaCourseStudentByUserId(userId);
	}

	/**
	 * 通过电话获取用户信息
	 * 
	 * @param phoneNum
	 * @return
	 */
	@Override
	public SysUser selectUserByPhoneNum(String phoneNum) {
		return userMapper.selectUserByPhoneNum(phoneNum);
	}

	/**
	 * 通过邮箱获取用户信息
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public SysUser selectUserByEmail(String email) {
		return userMapper.selectUserByEmail(email);
	}

	/**
	 * 通过账户、手机、邮箱获取用户信息
	 * 
	 * @param account
	 */
	@Override
	public SysUser selectUserByAccount(String account) {
		return userMapper.selectUserByAccount(account);
	}

}
