package com.learn.Entity;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.stereotype.Component;


@Component
public class OcrClient extends AipOcr {
    public static final String APP_ID = "16667601";
    public static final String API_KEY = "T3xjr0oIM7Pv6cOBU4N6cn90";
    public static final String SECRET_KEY = "IK4bMvckG5yMYEAswKRYeGGFlfQddUlV";
    public OcrClient() {
        super(APP_ID, API_KEY, SECRET_KEY);
    }
}
