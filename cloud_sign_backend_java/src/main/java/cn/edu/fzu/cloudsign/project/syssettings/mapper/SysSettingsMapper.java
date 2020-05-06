package cn.edu.fzu.cloudsign.project.syssettings.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.syssettings.domain.SysSettings;

/**
 * 系统参数Mapper接口
 * 
 * @author linkai
 * @date 2020-05-06
 */
public interface SysSettingsMapper 
{
    /**
     * 查询系统参数
     * 
     * @param sysSettingsId 系统参数ID
     * @return 系统参数
     */
    public SysSettings selectSysSettingsById(Long sysSettingsId);

    /**
     * 查询系统参数列表
     * 
     * @param sysSettings 系统参数
     * @return 系统参数集合
     */
    public List<SysSettings> selectSysSettingsList(SysSettings sysSettings);

    /**
     * 新增系统参数
     * 
     * @param sysSettings 系统参数
     * @return 结果
     */
    public int insertSysSettings(SysSettings sysSettings);

    /**
     * 修改系统参数
     * 
     * @param sysSettings 系统参数
     * @return 结果
     */
    public int updateSysSettings(SysSettings sysSettings);

    /**
     * 删除系统参数
     * 
     * @param sysSettingsId 系统参数ID
     * @return 结果
     */
    public int deleteSysSettingsById(Long sysSettingsId);

    /**
     * 批量删除系统参数
     * 
     * @param sysSettingsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSettingsByIds(Long[] sysSettingsIds);
}
