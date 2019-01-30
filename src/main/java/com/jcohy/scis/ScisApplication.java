package com.jcohy.scis;

import com.jcohy.scis.interception.CommonIntercepter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Configuration
@MapperScan("com.jcohy.scis.mapper")
public class ScisApplication extends WebMvcConfigurerAdapter{

	@Autowired
	private CommonIntercepter commonIntercepter;


	public static void main(String[] args) {
		SpringApplication.run(ScisApplication.class, args);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonIntercepter).addPathPatterns("/admin/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//登录首页
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("login");
		//修改密码
		registry.addViewController("/admin/update").setViewName("/update");
		//管理员主页，从登录
		registry.addViewController("/admin/main").setViewName("/admin/main");
		registry.addViewController("/admin/index").setViewName("/admin/index");
		registry.addViewController("/admin/notice").setViewName("/admin/notice");
		registry.addViewController("/admin/student/index").setViewName("/admin/student/index");
		//订单管理
		registry.addViewController("/admin/order/index").setViewName("/admin/order/index");
		//产品管理
		registry.addViewController("/admin/product/index").setViewName("/admin/product/index");
		//图片管理
		registry.addViewController("/admin/picture/index").setViewName("/admin/picture/index");
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize("102400KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
