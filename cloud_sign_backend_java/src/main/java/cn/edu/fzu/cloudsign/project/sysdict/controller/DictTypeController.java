package cn.edu.fzu.cloudsign.project.sysdict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;
import cn.edu.fzu.cloudsign.project.sysdict.domain.DictType;
import cn.edu.fzu.cloudsign.project.sysdict.service.IDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 字典类型Controller
 * 
 * @author linkai
 * @date 2020-03-27
 */
@Api("字典类型管理")
@RestController
@RequestMapping("/sysdict/type")
public class DictTypeController extends BaseController
{
    @Autowired
    private IDictTypeService dictTypeService;

    /**
     * 查询字典类型列表
     */
    @ApiOperation("查询字典类型列表")
    @PreAuthorize("@ss.hasPermi('sysdict:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(DictType dictType)
    {
        startPage();
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//		Long userId = loginUser.getUser().getUserId();
        List<DictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 获取字典类型详细信息
     */
    @ApiOperation("获取字典类型详细信息")
    @PreAuthorize("@ss.hasPermi('sysdict:type:query')")
    @GetMapping(value = "/{dictTypeId}")
    public AjaxResult getInfo(@PathVariable("dictTypeId") Long dictTypeId)
    {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictTypeId));
    }

    /**
     * 新增字典类型
     */
    @ApiOperation("新增字典类型")
    @PreAuthorize("@ss.hasPermi('sysdict:type:add')")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictType dictType)
    {
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    /**
     * 修改字典类型
     */
    @ApiOperation("修改字典类型")
    @PreAuthorize("@ss.hasPermi('sysdict:type:edit')")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictType dictType)
    {
        return toAjax(dictTypeService.updateDictType(dictType));
    }

    /**
     * 删除字典类型
     */
    @ApiOperation("删除字典类型")
    @PreAuthorize("@ss.hasPermi('sysdict:type:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictTypeIds}")
    public AjaxResult remove(@PathVariable Long[] dictTypeIds)
    {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictTypeIds));
    }
}
