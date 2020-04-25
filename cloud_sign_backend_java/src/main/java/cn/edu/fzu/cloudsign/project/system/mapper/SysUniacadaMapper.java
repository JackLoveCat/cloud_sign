package cn.edu.fzu.cloudsign.project.system.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.system.domain.SysUniacada;

/**
 * 学校院系Mapper接口
 * 
 * @author fanxuenan
 * @date 2020-03-21
 */
public interface SysUniacadaMapper {
	/**
	 * 查询学校院系
	 * 
	 * @param uniacadaId 学校院系ID
	 * @return 学校院系
	 */
	public SysUniacada selectSysUniacadaById(Long uniacadaId);

	/**
	 * 查询学校院系列表
	 * 
	 * @param sysUniacada 学校院系
	 * @return 学校院系集合
	 */
	public List<SysUniacada> selectSysUniacadaList(SysUniacada sysUniacada);

	/**
	 * 新增学校院系
	 * 
	 * @param sysUniacada 学校院系
	 * @return 结果
	 */
	public int insertSysUniacada(SysUniacada sysUniacada);

	/**
	 * 修改学校院系
	 * 
	 * @param sysUniacada 学校院系
	 * @return 结果
	 */
	public int updateSysUniacada(SysUniacada sysUniacada);

	/**
	 * 删除学校院系
	 * 
	 * @param uniacadaId 学校院系ID
	 * @return 结果
	 */
	public int deleteSysUniacadaById(Long uniacadaId);

	/**
	 * 批量删除学校院系
	 * 
	 * @param uniacadaIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteSysUniacadaByIds(Long[] uniacadaIds);

	/**
	 * 查询学校
	 * 
	 * @return
	 */
	public List<String> selectUniversityList();

	/**
	 * 按学校查询学院
	 * 
	 * @param uniName
	 * @return
	 */
	public List<SysUniacada> selectAcademyListByUniversity(String uniName);
}
