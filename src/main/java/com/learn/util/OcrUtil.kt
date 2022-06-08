package com.learn.util

import com.learn.Entity.OcrClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
open class OcrUtil {
    @Autowired
    private val client: OcrClient? = null
    fun getOcr(file: ByteArray?): String {
        val options = HashMap<String, String>()
        options["language_type"] = "CHN_ENG"
        options["detect_direction"] = "true"
        options["detect_language"] = "true"
        options["probability"] = "true"
        val res = client!!.basicGeneral(file, options)
        println(res.toString())
        return res.toString()
    }
}