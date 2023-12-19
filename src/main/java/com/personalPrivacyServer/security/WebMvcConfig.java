/*==================================================================
프로젝트명 : 개인정보 동의서
작성지 : 신정호
작성일 : 2023년 12월 06일
용도 : 개인정보 동의서 컨트롤러
==================================================================*/

package com.personalPrivacyServer.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///C:/testImage/");
//                .addResourceLocations("file:///home/ubuntu/Pictures/");
//                .addResourceLocations("file:///home/cubox/Pictures/");
//                .addResourceLocations("file:///home/cubox/image/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 허용하려는 IP 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
