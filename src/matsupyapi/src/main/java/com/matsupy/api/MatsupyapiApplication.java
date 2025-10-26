package com.matsupy.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.matsupy.api",   // アプリ本体
	    "com.matsupy.cmn" // 共通Jar
	})
@MapperScan("com.matsupy.cmn.mapper")
public class MatsupyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatsupyapiApplication.class, args);
	}

}
