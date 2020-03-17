package cn.edu.fzu.cloudsign.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fzu.cloudsign.demo.domain.Demo;
import cn.edu.fzu.cloudsign.framework.web.domain.AjaxResult;

/**
 *
 */
@RestController
@RequestMapping("/demo2")
public class DemoController2 {


	@PostMapping("/postdemo")
	public AjaxResult postDemo(@RequestBody Demo demo) {
		System.out.println(demo.getKey() + ":" + demo.getValue());
		return AjaxResult.success();
	}
}
