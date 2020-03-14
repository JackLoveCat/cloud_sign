package cn.edu.fzu.cloudsign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CloudSignApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudSignApplication.class, args);
		System.out.println("云签到后端启动成功！");
	}
}
