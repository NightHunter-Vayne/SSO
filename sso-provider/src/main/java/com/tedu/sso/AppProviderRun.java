package com.tedu.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

import redis.clients.jedis.Jedis;

@SpringBootApplication(exclude = {//启动时 排除数据库信息
		DruidDataSourceAutoConfigure.class})
@EnableEurekaClient
@MapperScan(basePackages = "com.tedu.sso.mapper")
public class AppProviderRun {
	@Bean
	public Jedis jedis() {
		return new Jedis("192.168.222.101", 6379);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AppProviderRun.class);
	}
}
