package com.learn.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Created by fengshaomin on 2018/10/19 0019.
 */
@Configuration
open class MyAppFileConfig : WebMvcConfigurer {
    @Value("\${web.file-path}")
    private val file_path: String? = null

    @Value("\${web.qrcode-path}")
    private val qrcode_path: String? = null
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:$file_path")
        registry.addResourceHandler("/qrcode/**").addResourceLocations("file:$qrcode_path")
    }
}