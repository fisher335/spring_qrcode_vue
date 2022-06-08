package com.learn.config

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Created by fengshaomin on 2018/10/9 0009.
 */
@ControllerAdvice
open class GlobalExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun errorHandler(e: Exception, map: Model): String? {
        map.addAttribute("code", "00000000")
        println(e.fillInStackTrace())
        map.addAttribute("msg", e.message)
        return e.message
    }
}