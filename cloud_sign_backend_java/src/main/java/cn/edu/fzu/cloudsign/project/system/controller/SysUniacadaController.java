package cn.edu.fzu.cloudsign.project.system.controller;

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
import cn.edu.fzu.cloudsign.project.system.domain.SysUniacada;
import cn.edu.fzu.cloudsign.project.system.service.ISysUniacadaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;

/**
 * 学校院系Controller
 * 
 * @author fanxuenan
 * @date 2020-03-21
 */
@Api("学校院系")
@RestController
@RequestMapping("/system/uniacada")
public class SysUniacadaController extends BaseController {
	@Autowired
	private ISysUniacadaService sysUniacadaService;

	/**
	 * 查询学校院系列表
	 */
	@ApiOperation("查询学校院系列表")
	@PreAuthorize("@ss.hasPermi('system:uniacada')")
	@GetMapping("/list")
	public TableDataInfo list(SysUniacada sysUniacada) {
		startPage();
		List<SysUniacada> list = sysUniacadaService.selectSysUniacadaList(sysUniacada);
		return getDataTable(list);
	}

	/**
	 * 获取学校院系详细信息
	 */
	@ApiOperation("获取学校院系详细信息")
	@PreAuthorize("@ss.hasPermi('system:uniacada')")
	@GetMapping(value = "/{uniacadaId}")
	public AjaxResult getInfo(@PathVariable("uniacadaId") Long uniacadaId) {
		return AjaxResult.success(sysUniacadaService.selectSysUniacadaById(uniacadaId));
	}

	/**
	 * 新增学校院系
	 */
	@ApiOperation("新增学校院系")
	@PreAuthorize("@ss.hasPermi('system:uniacada')")
	@Log(title = "学校院系", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody SysUniacada sysUniacada) {
		return toAjax(sysUniacadaService.insertSysUniacada(sysUniacada));
	}

	/**
	 * 修改学校院系
	 */
	@ApiOperation("修改学校院系")
	@PreAuthorize("@ss.hasPermi('system:uniacada')")
	@Log(title = "学校院系", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody SysUniacada sysUniacada) {
		return toAjax(sysUniacadaService.updateSysUniacada(sysUniacada));
	}

	/**
	 * 删除学校院系
	 */
	@ApiOperation("删除学校院系")
	@PreAuthorize("@ss.hasPermi('system:uniacada')")
	@Log(title = "学校院系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uniacadaIds}")
	public AjaxResult remove(@PathVariable Long[] uniacadaIds) {
		return toAjax(sysUniacadaService.deleteSysUniacadaByIds(uniacadaIds));
	}

	/**
	 * 查询学校
	 */
	@ApiOperation("查询学校")
	@GetMapping("/listuni")
	public AjaxResult listUniversity() {
		return AjaxResult.success(sysUniacadaService.selectUniversityList());
	}

	/**
	 * 按学校查询学院
	 */
	@ApiOperation("按学校查询学院，与查询学校接口级联查询，用于创建用户或班课时查询得到学校院系id")
	@GetMapping(value = "/listacabyuni/{uniName}")
	public AjaxResult listAcademyByUniversity(@PathVariable("uniName") String uniName) {
		return AjaxResult.success(sysUniacadaService.selectAcademyListByUniversity(uniName));
	}
}
