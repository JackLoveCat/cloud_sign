package cn.edu.fzu.cloudsign.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/getdemo")
	public AjaxResult getDemo() {
		List<String> demoStr = new ArrayList<String>();
		demoStr.add("hello world!");
		demoStr.add("cloud sign backend!");

		return AjaxResult.success(demoStr);
	}

}
