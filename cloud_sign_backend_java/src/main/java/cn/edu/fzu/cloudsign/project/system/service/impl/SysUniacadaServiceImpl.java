package cn.edu.fzu.cloudsign.project.system.service.impl;

import java.util.List;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.system.mapper.SysUniacadaMapper;
import cn.edu.fzu.cloudsign.project.system.domain.SysUniacada;
import cn.edu.fzu.cloudsign.project.system.service.ISysUniacadaService;

/**
 * 学校院系Service业务层处理
 * 
 * @author fanxuenan
 * @date 2020-03-21
 */
@Service
public class SysUniacadaServiceImpl implements ISysUniacadaService {
	@Autowired
	private SysUniacadaMapper sysUniacadaMapper;

	/**
	 * 查询学校院系
	 * 
	 * @param uniacadaId 学校院系ID
	 * @return 学校院系
	 */
	@Override
	public SysUniacada selectSysUniacadaById(Long uniacadaId) {
		return sysUniacadaMapper.selectSysUniacadaById(uniacadaId);
	}

	/**
	 * 查询学校院系列表
	 * 
	 * @param sysUniacada 学校院系
	 * @return 学校院系
	 */
	@Override
	public List<SysUniacada> selectSysUniacadaList(SysUniacada sysUniacada) {
		return sysUniacadaMapper.selectSysUniacadaList(sysUniacada);
	}

	/**
	 * 新增学校院系
	 * 
	 * @param sysUniacada 学校院系
	 * @return 结果
	 */
	@Override
	public int insertSysUniacada(SysUniacada sysUniacada) {
		sysUniacada.setCreateTime(DateUtils.getNowDate());
		return sysUniacadaMapper.insertSysUniacada(sysUniacada);
	}

	/**
	 * 修改学校院系
	 * 
	 * @param sysUniacada 学校院系
	 * @return 结果
	 */
	@Override
	public int updateSysUniacada(SysUniacada sysUniacada) {
		sysUniacada.setUpdateTime(DateUtils.getNowDate());
		return sysUniacadaMapper.updateSysUniacada(sysUniacada);
	}

	/**
	 * 批量删除学校院系
	 * 
	 * @param uniacadaIds 需要删除的学校院系ID
	 * @return 结果
	 */
	@Override
	public int deleteSysUniacadaByIds(Long[] uniacadaIds) {
		return sysUniacadaMapper.deleteSysUniacadaByIds(uniacadaIds);
	}

	/**
	 * 删除学校院系信息
	 * 
	 * @param uniacadaId 学校院系ID
	 * @return 结果
	 */
	@Override
	public int deleteSysUniacadaById(Long uniacadaId) {
		return sysUniacadaMapper.deleteSysUniacadaById(uniacadaId);
	}

	/**
	 * 查询学校
	 * 
	 * @return
	 */
	@Override
	public List<String> selectUniversityList() {
		return sysUniacadaMapper.selectUniversityList();
	}

	/**
	 * 按学校查询学院
	 * 
	 * @param uniName
	 * @return
	 */
	@Override
	public List<SysUniacada> selectAcademyListByUniversity(String uniName) {
		return sysUniacadaMapper.selectAcademyListByUniversity(uniName);
	}
}
