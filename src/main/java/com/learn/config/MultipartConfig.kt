package com.learn.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

public class MultipartConfig {
    @Value("${projectPath:}")
    private String projectPath;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = StringUtils.isNotBlank(projectPath) ? projectPath + "/data/tmp" : "/data/tmp";
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
