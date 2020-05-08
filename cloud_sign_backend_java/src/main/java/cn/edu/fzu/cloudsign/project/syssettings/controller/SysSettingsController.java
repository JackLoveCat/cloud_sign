package cn.edu.fzu.cloudsign.project.syssettings.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.project.syssettings.domain.SysSettings;
import cn.edu.fzu.cloudsign.project.syssettings.service.ISysSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;

/**
 * 系统参数Controller
 * 
 * @author linkai
 * @date 2020-05-06
 */
@Api("系统参数设置")
@RestController
@RequestMapping("/syssettings/settings")
public class SysSettingsController extends BaseController
{
    @Autowired
    private ISysSettingsService sysSettingsService;

    /**
     * 查询系统参数列表
     */
    @ApiOperation("查询系统参数列表")
    @PreAuthorize("@ss.hasPermi('syssettings:settings')")
    @GetMapping("/list")
    public TableDataInfo list(SysSettings sysSettings)
    {
        startPage();
        List<SysSettings> list = sysSettingsService.selectSysSettingsList(sysSettings);
        return getDataTable(list);
    }

    /**
     * 获取系统参数详细信息
     */
    @ApiOperation("获取系统参数详细信息")
    @PreAuthorize("@ss.hasPermi('syssettings:settings')")
    @GetMapping(value = "/{sysSettingsId}")
    public AjaxResult getInfo(@PathVariable("sysSettingsId") Long sysSettingsId)
    {
        return AjaxResult.success(sysSettingsService.selectSysSettingsById(sysSettingsId));
    }

    /**
     * 新增系统参数
     */
    @ApiOperation("新增系统参数")
    @PreAuthorize("@ss.hasPermi('syssettings:settings')")
    @Log(title = "系统参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSettings sysSettings)
    {
        return toAjax(sysSettingsService.insertSysSettings(sysSettings));
    }

    /**
     * 修改系统参数
     */
    @ApiOperation("修改系统参数")
    @PreAuthorize("@ss.hasPermi('syssettings:settings')")
    @Log(title = "系统参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSettings sysSettings)
    {
        return toAjax(sysSettingsService.updateSysSettings(sysSettings));
    }

    /**
     * 删除系统参数
     */
    @ApiOperation("删除系统参数")
    @PreAuthorize("@ss.hasPermi('syssettings:settings')")
    @Log(title = "系统参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sysSettingsIds}")
    public AjaxResult remove(@PathVariable Long[] sysSettingsIds)
    {
        return toAjax(sysSettingsService.deleteSysSettingsByIds(sysSettingsIds));
    }
    
    /**
     * 启用系统参数
     */
    @ApiOperation("启用系统参数")
    @PreAuthorize("@ss.hasPermi('syssettings:settings')")
    @Log(title = "系统参数", businessType = BusinessType.UPDATE)
    @PutMapping("setValid/{sysSettingsId}")
    public AjaxResult setValid(@PathVariable("sysSettingsId") Long sysSettingsId)
    {
        return toAjax(sysSettingsService.setValid(sysSettingsId));
    }
}
