package cn.edu.fzu.cloudsign.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的接口配置
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/** 系统基础配置 */
	@Autowired
	private CloudSignConfig cloudSignConfig;

	/**
	 * 创建API
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
				.apiInfo(apiInfo())
				// 设置哪些接口暴露给Swagger展示
				.select()
				// 扫描所有有注解的api，用这种方式更灵活
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any()).build();
	}

	/**
	 * 添加摘要信息
	 */
	private ApiInfo apiInfo() {
		// 用ApiInfoBuilder进行定制
		return new ApiInfoBuilder()
				// 设置标题
				.title("标题：到云系统_接口文档")
				// 描述
				.description("描述：")
				// 作者信息
				.contact(new Contact(cloudSignConfig.getName(), null, null))
				// 版本
				.version("版本号:" + cloudSignConfig.getVersion()).build();
	}
}
