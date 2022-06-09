package com.learn.Entity

import com.baidu.aip.ocr.AipOcr
import org.springframework.stereotype.Component

@Component
 class OcrClient : AipOcr("16667601","T3xjr0oIM7Pv6cOBU4N6cn90","IK4bMvckG5yMYEAswKRYeGGFlfQddUlV") {
    val APP_ID = "16667601"
    val API_KEY = "T3xjr0oIM7Pv6cOBU4N6cn90"
    val SECRET_KEY = "IK4bMvckG5yMYEAswKRYeGGFlfQddUlV"
}