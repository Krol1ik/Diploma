package com.vlad.tms.diploma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")  //данная аннатация ищет в properties "upload.path" и вставляет в переменную ниже
    private String uploadPath;

    //"/login" - шаблон, который будет использовать Spring Security со уже заложенными правилами
    //login - страничка jsp, на которой будет использоваться шаблон
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("title");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/***")  // каждое обращение к серверу по пути /img будет перенаправлять по пути ниже
                .addResourceLocations("file://" + uploadPath + "/");
        registry.addResourceHandler("/static/**")   //
                .addResourceLocations("classpath:/static/");   // при обращение по этому пути, ресурсы будут искаться в корне проекта
    }
}
