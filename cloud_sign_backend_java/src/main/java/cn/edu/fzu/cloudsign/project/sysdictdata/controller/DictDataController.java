package cn.edu.fzu.cloudsign.project.sysdictdata.controller;

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
import cn.edu.fzu.cloudsign.project.sysdictdata.domain.DictData;
import cn.edu.fzu.cloudsign.project.sysdictdata.service.IDictDataService;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;

/**
 * 字典信息Controller
 * 
 * @author linkai
 * @date 2020-03-27
 */
@RestController
@RequestMapping("/sysdictdata/data")
public class DictDataController extends BaseController
{
    @Autowired
    private IDictDataService dictDataService;

    /**
     * 查询字典信息列表
     */
    @PreAuthorize("@ss.hasPermi('sysdictdata:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(DictData dictData)
    {
        startPage();
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 获取字典信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('sysdictdata:data:query')")
    @GetMapping(value = "/{dictDataId}")
    public AjaxResult getInfo(@PathVariable("dictDataId") Long dictDataId)
    {
        return AjaxResult.success(dictDataService.selectDictDataById(dictDataId));
    }

    /**
     * 新增字典信息
     */
    @PreAuthorize("@ss.hasPermi('sysdictdata:data:add')")
    @Log(title = "字典信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictData dictData)
    {
        return toAjax(dictDataService.insertDictData(dictData));
    }

    /**
     * 修改字典信息
     */
    @PreAuthorize("@ss.hasPermi('sysdictdata:data:edit')")
    @Log(title = "字典信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictData dictData)
    {
        return toAjax(dictDataService.updateDictData(dictData));
    }

    /**
     * 删除字典信息
     */
    @PreAuthorize("@ss.hasPermi('sysdictdata:data:remove')")
    @Log(title = "字典信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictDataIds}")
    public AjaxResult remove(@PathVariable Long[] dictDataIds)
    {
        return toAjax(dictDataService.deleteDictDataByIds(dictDataIds));
    }
}
