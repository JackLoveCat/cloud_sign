package cn.edu.fzu.cloudsign.project.signin.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
import cn.edu.fzu.cloudsign.project.cla.domain.ClaCourse;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaStudentSign;
import cn.edu.fzu.cloudsign.project.signin.domain.ClaTeacherSign;
import cn.edu.fzu.cloudsign.project.signin.service.IClaStudentSignService;
import cn.edu.fzu.cloudsign.project.signin.service.IClaTeacherSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.edu.fzu.cloudsign.framework.web.controller.BaseController;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;
import cn.edu.fzu.cloudsign.framework.web.page.TableDataInfo;

/**
 * 学生签到记录Controller
 * 
 * @author linkai
 * @date 2020-04-26
 */
@Api("签到管理")
@RestController
@RequestMapping("/signin/sign")
public class ClaStudentSignController extends BaseController
{
    @Autowired
    private IClaStudentSignService claStudentSignService;
    
    @Autowired
    private IClaTeacherSignService claTeacherSignService;

    /**
     * 查询学生签到记录列表
     */
    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @GetMapping("/list")
    public TableDataInfo list(ClaStudentSign claStudentSign)
    {
        startPage();
        List<ClaStudentSign> list = claStudentSignService.selectClaStudentSignList(claStudentSign);
        return getDataTable(list);
    }

    /**
     * 获取学生签到记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @GetMapping(value = "/{studentSignId}")
    public AjaxResult getInfo(@PathVariable("studentSignId") Long studentSignId)
    {
        return AjaxResult.success(claStudentSignService.selectClaStudentSignById(studentSignId));
    }

    /**
     * 新增学生签到记录
     */
    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @Log(title = "学生签到记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaStudentSign claStudentSign)
    {
        return toAjax(claStudentSignService.insertClaStudentSign(claStudentSign));
    }

    /**
     * 修改学生签到记录
     */
    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @Log(title = "学生签到记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaStudentSign claStudentSign)
    {
        return toAjax(claStudentSignService.updateClaStudentSign(claStudentSign));
    }

    /**
     * 删除学生签到记录
     */
    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @Log(title = "学生签到记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentSignIds}")
    public AjaxResult remove(@PathVariable Long[] studentSignIds)
    {
        return toAjax(claStudentSignService.deleteClaStudentSignByIds(studentSignIds));
    }
    
    
    /**
     * 教师发起签到
     */
    @ApiOperation("教师发起签到")
//    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @Log(title = "教师发起签到", businessType = BusinessType.INSERT)
    @PostMapping("/teacherSignIn")
    public AjaxResult teacherSignIn(@Validated @RequestBody ClaTeacherSign claTeacherSign)
    {
        return toAjax(claTeacherSignService.teacherSignIn(claTeacherSign));
    }
    
    /**
     * 教师结束签到
     */
    @ApiOperation("教师结束签到")
//    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @Log(title = "教师结束签到", businessType = BusinessType.UPDATE)
    @PostMapping("/teacherSignInStop")
    public AjaxResult teacherSignInStop(@Validated @RequestBody ClaTeacherSign claTeacherSign)
    {
        return toAjax(claTeacherSignService.teacherSignInStop(claTeacherSign));
    }
    
    
    /**
     * 学生/教师查询正在进行中的班课签到
     */
    @ApiOperation("学生/教师查询正在进行中的班课签到")
    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @GetMapping(value = "/getSignInCourseInfo/{courseId}")
    public AjaxResult getSignInCourseInfo(@PathVariable("courseId") Long courseId)
    {
//        return AjaxResult.success(claTeacherSignService.getSignInCourseInfo(courseId));
//    	return claTeacherSignService.getSignInCourseInfo(courseId);
    	return AjaxResult.success(claTeacherSignService.getSignInCourseInfo(courseId));
    }
    
    /**
     * 学生记录签到
     */
    @ApiOperation("学生签到")
//    @PreAuthorize("@ss.hasPermi('signin:sign')")
    @Log(title = "学生签到", businessType = BusinessType.INSERT)
    @PostMapping("/studentSignIn")
    public AjaxResult studentSignIn(@Validated @RequestBody ClaStudentSign claStudentSign)
    {
        return toAjax(claStudentSignService.studentSignIn(claStudentSign));
    }
//    public AjaxResult studentSignIn(@PathVariable("userId") Long userId,@PathVariable("courseId") Long courseId,@PathVariable("gesture") String gesture)
//    {
//        return toAjax(claStudentSignService.studentSignIn(userId,courseId,gesture));
//    }
}
