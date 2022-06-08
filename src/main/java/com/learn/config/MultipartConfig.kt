package com.learn.config

import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import javax.servlet.MultipartConfigElement

class MultipartConfig {
    @Value("\${projectPath:}")
    private val projectPath: String? = null
    @Bean
    fun multipartConfigElement(): MultipartConfigElement {
        val factory = MultipartConfigFactory()
        val location = if (StringUtils.isNotBlank(projectPath)) "$projectPath/data/tmp" else "/data/tmp"
        factory.setLocation(location)
        return factory.createMultipartConfig()
    }
}