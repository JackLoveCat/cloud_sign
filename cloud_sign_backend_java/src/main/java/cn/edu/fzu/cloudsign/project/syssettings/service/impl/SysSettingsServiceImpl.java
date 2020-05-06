package cn.edu.fzu.cloudsign.project.syssettings.service.impl;

import java.util.List;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.syssettings.mapper.SysSettingsMapper;
import cn.edu.fzu.cloudsign.project.syssettings.domain.SysSettings;
import cn.edu.fzu.cloudsign.project.syssettings.service.ISysSettingsService;

/**
 * 系统参数Service业务层处理
 * 
 * @author linkai
 * @date 2020-05-06
 */
@Service
public class SysSettingsServiceImpl implements ISysSettingsService 
{
    @Autowired
    private SysSettingsMapper sysSettingsMapper;

    /**
     * 查询系统参数
     * 
     * @param sysSettingsId 系统参数ID
     * @return 系统参数
     */
    @Override
    public SysSettings selectSysSettingsById(Long sysSettingsId)
    {
        return sysSettingsMapper.selectSysSettingsById(sysSettingsId);
    }

    /**
     * 查询系统参数列表
     * 
     * @param sysSettings 系统参数
     * @return 系统参数
     */
    @Override
    public List<SysSettings> selectSysSettingsList(SysSettings sysSettings)
    {
        return sysSettingsMapper.selectSysSettingsList(sysSettings);
    }

    /**
     * 新增系统参数
     * 
     * @param sysSettings 系统参数
     * @return 结果
     */
    @Override
    public int insertSysSettings(SysSettings sysSettings)
    {
        sysSettings.setCreateTime(DateUtils.getNowDate());
        return sysSettingsMapper.insertSysSettings(sysSettings);
    }

    /**
     * 修改系统参数
     * 
     * @param sysSettings 系统参数
     * @return 结果
     */
    @Override
    public int updateSysSettings(SysSettings sysSettings)
    {
        sysSettings.setUpdateTime(DateUtils.getNowDate());
        return sysSettingsMapper.updateSysSettings(sysSettings);
    }

    /**
     * 批量删除系统参数
     * 
     * @param sysSettingsIds 需要删除的系统参数ID
     * @return 结果
     */
    @Override
    public int deleteSysSettingsByIds(Long[] sysSettingsIds)
    {
        return sysSettingsMapper.deleteSysSettingsByIds(sysSettingsIds);
    }

    /**
     * 删除系统参数信息
     * 
     * @param sysSettingsId 系统参数ID
     * @return 结果
     */
    @Override
    public int deleteSysSettingsById(Long sysSettingsId)
    {
        return sysSettingsMapper.deleteSysSettingsById(sysSettingsId);
    }
}
