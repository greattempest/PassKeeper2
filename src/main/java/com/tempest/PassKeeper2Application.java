package com.tempest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.tempest.jdbc.dao"})
//@ComponentScan(basePackages = {"com.tempest.jdbc.service"})
public class PassKeeper2Application {

	public static void main(String[] args) {
		SpringApplication.run(PassKeeper2Application.class, args);
	}

}
