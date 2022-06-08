package com.learn.controller

import cn.hutool.json.JSONUtil
import cn.hutool.log.StaticLog
import com.learn.util.OcrUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*
import javax.servlet.http.HttpServletResponse

/**
 * @author Administrator
 */
@RestController
class AppendController {
    @Autowired
    private val ocrUtil: OcrUtil? = null
    @RequestMapping(value = ["/wiki/"])
    @Throws(IOException::class)
    fun getWiki(response: HttpServletResponse) {
        response.sendRedirect("http://www.baidu.com")
    }

    @get:GetMapping(value = ["/ocr"])
    val ocr: String
        get() = "ocr"

    @PostMapping(value = ["/ocr/"])
    @Throws(IOException::class)
    fun postOcr(@RequestParam("file") file: MultipartFile, map: Model): String {
        val result = ocrUtil!!.getOcr(file.bytes)
        val js = JSONUtil.parseObj(result)
        StaticLog.info(js.toString())
        val jsarray = js.getJSONArray("words_result")
        var resultOcr = StringBuffer()
        for (i in jsarray.indices) {
            val jsWord = JSONUtil.parseObj(jsarray[i])
            resultOcr = resultOcr.append(jsWord["words"].toString() + "<br>")
        }
        map.addAttribute("ocr", resultOcr.toString())
        return "ocr"
    }

    @ResponseBody
    @RequestMapping(value = ["/es"])
    @Throws(IOException::class)
    fun cryption(@RequestParam params: Map<String?, Any>): Map<String, String> {
        val un = params["un"].toString()
        val pd = params["pd"].toString()
        val auth = String.format("%s:%s", un, pd)
        val auth1 = baseCry(auth)
        val headerAuth = "Authorization: Basic $auth1"
        val authString = baseCry(headerAuth)
        val rst: MutableMap<String, String> = HashMap(4)
        rst["header"] = headerAuth
        rst["stringAuth"] = authString
        return rst
    }

    @Throws(UnsupportedEncodingException::class)
    fun baseCry(s: String): String {
        return String(Base64.getEncoder().encode(s.toByteArray(charset("utf-8"))), Charset.forName("utf-8"))
    }
}