package cn.edu.fzu.cloudsign.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 增加允许跨域访问配置
 * 
 * @author fanxu
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	/**
	 * 允许跨域访问
	 *
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(true)
				.maxAge(3600);
	}
}
