package com.learn.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Created by fengshaomin on 2018/10/19 0019.
 */
@Configuration
public class MyAppFileConfig implements WebMvcConfigurer {


    @Value("${web.file-path}")
    private String file_path;
    @Value("${web.qrcode-path}")
    private String qrcode_path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + file_path);
        registry.addResourceHandler("/qrcode/**").addResourceLocations("file:" + qrcode_path);
    }
}
