package com.ztesoft.dispatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Description:
 * Spring Boot 启动入口类
 *
 * @author huaying.chen
 * @version 1.0-SNAPSHOT
 * @since 2016/11/9
 */
@Controller
@EnableWebMvc
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
@ServletComponentScan
@SpringBootApplication
public class Application {

    @Value("${web.view.index}")
    private String webViewIndex;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String webViewIndex() {
        return "redirect:" + webViewIndex;
    }
 // 显示声明CommonsMultipartResolver为mutipartResolver
 	@Bean(name = "multipartResolver")
 	public MultipartResolver multipartResolver() {
 		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
 		resolver.setDefaultEncoding("UTF-8");
 		resolver.setResolveLazily(true);// resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
 		resolver.setMaxInMemorySize(40960);
 		resolver.setMaxUploadSize(50 * 1024 * 1024);// 上传文件大小 50M 50*1024*1024
 		System.out.println("1111");
 		return resolver;
 	}
}
