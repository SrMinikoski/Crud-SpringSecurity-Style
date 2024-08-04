package com.springboot.crudspring1.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/listar").setViewName("listar");
        registry.addViewController("/cadastrar").setViewName("cadastrar");
        registry.addViewController("/alterar").setViewName("alterar");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/save").setViewName("save");
    }

}