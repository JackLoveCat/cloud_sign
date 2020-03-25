package cn.edu.fzu.cloudsign.project.cla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fzu.cloudsign.common.utils.ServletUtils;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.annotation.Log;
import cn.edu.fzu.cloudsign.framework.aspectj.lang.enums.BusinessType;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;
import cn.edu.fzu.cloudsign.framework.security.service.TokenService;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourse;
import cn.edu.fzu.cloudsign.project.cla.service.IClaCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 班课信息Controller
 * 
 * @author fanxuenan
 * @date 2020-03-23
 */

@Api("班课管理")
@RestController
@RequestMapping("/cla/course")
public class ClaCourseController extends BaseController {
	@Autowired
	private IClaCourseService claCourseService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 查询班课信息列表
	 */
	@ApiOperation("查询班课列表")
	@PreAuthorize("@ss.hasPermi('')")
	@GetMapping("/list")
	public TableDataInfo list(ClaCourse claCourse) {
		startPage();
		List<ClaCourse> list = claCourseService.selectClaCourseList(claCourse);
		return getDataTable(list);
	}

	/**
	 * 查询我创建的班课信息列表
	 */
	@ApiOperation("查询我创建的班课信息列表")
	@PreAuthorize("@ss.hasPermi('cla:course:create')")
	@GetMapping("/listmycreate")
	public TableDataInfo listMyCreate(ClaCourse claCourse) {
		startPage();
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		Long userId = loginUser.getUser().getUserId();
		List<ClaCourse> list = claCourseService.selectMyCreateClaCourse(claCourse, userId);
		return getDataTable(list);
	}

	/**
	 * 查询我加入的班课信息列表
	 */
	@ApiOperation("查询我加入的班课信息列表")
	@PreAuthorize("@ss.hasPermi('cla:course:join')")
	@GetMapping("/listmyjoin")
	public TableDataInfo listMyJoin(ClaCourse claCourse) {
		startPage();
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		Long userId = loginUser.getUser().getUserId();
		List<ClaCourse> list = claCourseService.selectMyJoinClaCourse(claCourse, userId);
		return getDataTable(list);
	}

	/**
	 * 获取班课信息详细信息
	 */
	@ApiOperation("获取班课详情")
	@PreAuthorize("@ss.hasAnyPermi('cla:course:create,cla:course:join')")
	@GetMapping(value = "/{courseId}")
	public AjaxResult getInfo(@PathVariable("courseId") Long courseId) {
		return AjaxResult.success(claCourseService.selectClaCourseById(courseId));
	}

	/**
	 * 获取班课信息详细信息
	 */
	@ApiOperation("根据班课号获取班课详情")
	@PreAuthorize("@ss.hasPermi('cla:course:join')")
	@GetMapping(value = "/coursenum/{coursenum}")
	public AjaxResult getInfoByCourseNum(@PathVariable("coursenum") String coursenum) {
		return AjaxResult.success(claCourseService.selectClaCourseByCourseNum(coursenum));
	}

	/**
	 * 新增班课信息
	 */
	@ApiOperation("新增班课信息")
	@PreAuthorize("@ss.hasPermi('cla:course:list')")
	@Log(title = "班课信息", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody ClaCourse claCourse) {
		return toAjax(claCourseService.insertClaCourse(claCourse));
	}

	/**
	 * 修改班课信息
	 */
	@ApiOperation("修改班课信息")
	@PreAuthorize("@ss.hasPermi('cla:course:create')")
	@Log(title = "班课信息", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody ClaCourse claCourse) {
		return toAjax(claCourseService.updateClaCourse(claCourse));
	}

	/**
	 * 删除班课信息
	 */
	@ApiOperation("删除班课信息")
	@PreAuthorize("@ss.hasPermi('cla:course:create')")
	@Log(title = "班课信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
	public AjaxResult remove(@PathVariable Long[] courseIds) {
		return toAjax(claCourseService.deleteClaCourseByIds(courseIds));
	}

	/**
	 * @param courseIds
	 * @return
	 */
	@ApiOperation("创建班课")
	@PreAuthorize("@ss.hasPermi('cla:course:create')")
	@Log(title = "班课信息", businessType = BusinessType.INSERT)
	@PostMapping("/create")
	public AjaxResult create(@Validated @RequestBody ClaCourse claCourse) {
		return toAjax(claCourseService.createClaCourse(claCourse));
	}

	/**
	 * @param courseIds
	 * @return
	 */
	@ApiOperation("加入班课")
	@PreAuthorize("@ss.hasPermi('cla:course:join')")
	@Log(title = "班课信息", businessType = BusinessType.INSERT)
	@PostMapping("/join")
	public AjaxResult join(@RequestBody ClaCourse claCourse) {
		return toAjax(claCourseService.joinClaCourse(claCourse));
	}
}
