package com.learn.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by fengshaomin on 2018/10/9 0009.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception e, Model map) {
        map.addAttribute("code", "00000000");
        System.out.println(e.fillInStackTrace());
        map.addAttribute("msg", e.getMessage());
        return e.getMessage();
    }
}
