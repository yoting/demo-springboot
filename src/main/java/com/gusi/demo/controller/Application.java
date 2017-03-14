package com.gusi.demo.controller;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.pool.DruidDataSource;
import com.gusi.demo.filter.UserFilter;
import com.gusi.demo.intercept.TokenInterceptor;
import com.gusi.demo.service.DemoService;

@ComponentScan(basePackages = { "com.gusi.demo.*" })
// @Configuration
// @EnableAutoConfiguration
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	@Value("${app.name}")
	private String name;
	@Autowired
	private Environment env;

	@Autowired
	private DemoService demoService;// 会根据profile指定的环境实例化对应的类

	@PostConstruct
	public void init() {
		demoService.demoMethod(name);
	}

	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));// 用户名
		dataSource.setPassword(env.getProperty("spring.datasource.password"));// 密码
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}

	/**
	 * 配置拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/user/**");
	}

	/**
	 * 配置过滤器
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		UserFilter userFilter = new UserFilter();
		registrationBean.setFilter(userFilter);
		registrationBean.setUrlPatterns(Arrays.asList("/user/*"));
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("hello");
	}
}