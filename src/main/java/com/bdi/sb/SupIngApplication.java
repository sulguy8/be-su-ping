package com.bdi.sb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan({"com.bdi.sb.mapper"})
@ConfigurationProperties("com.bdi.sb.config")
@PropertySource({"env.properties"})
public class SupIngApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupIngApplication.class, args);
	}

}
