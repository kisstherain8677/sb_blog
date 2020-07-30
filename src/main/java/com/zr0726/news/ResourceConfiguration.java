//package com.zr0726.news;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class ResourceConfiguration extends WebMvcConfigurerAdapter {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //addResourceHandler中的是访问路径，可以修改为其他的字符串
//        //addResourceLocations中的是实际路径
//        registry.addResourceHandler("/model/**").addResourceLocations("classpath:/model/");
//        super.addResourceHandlers(registry);
//    }
//}