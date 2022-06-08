package com.learn.config

import cn.org.rapid_framework.freemarker.directive.BlockDirective
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective
import cn.org.rapid_framework.freemarker.directive.OverrideDirective
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

/**
 * @Author LiuYinXin
 * Created at 2017/5/2.21:21.
 */
@Configuration
open class FreemarkerConfig {
    @Autowired
    var configuration: freemarker.template.Configuration? = null
    @PostConstruct
    fun setSharedVariable() {
        configuration!!.setSharedVariable("block", BlockDirective())
        configuration!!.setSharedVariable("override", OverrideDirective())
        configuration!!.setSharedVariable("extends", ExtendsDirective())
    }
}