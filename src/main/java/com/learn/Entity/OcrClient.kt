package com.learn.Entity

import com.baidu.aip.ocr.AipOcr
import org.springframework.stereotype.Component


//单例模式
@Component
object OcrClient : AipOcr("16667601","T3xjr0oIM7Pv6cOBU4N6cn90","IK4bMvckG5yMYEAswKRYeGGFlfQddUlV") {
    const val APP_ID = "16667601"
    const val API_KEY = "T3xjr0oIM7Pv6cOBU4N6cn90"
    const val SECRET_KEY = "IK4bMvckG5yMYEAswKRYeGGFlfQddUlV"
}