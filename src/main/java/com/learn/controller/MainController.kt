package com.learn.controller

import cn.hutool.http.HttpUtil
import com.learn.Entity.Result
import com.learn.util.FileUpload
import com.learn.util.Qrcode
import com.learn.util.ResultUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.util.*
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author fengshaomin
 * @date 2018/7/20 0020
 */
@Controller
@RequestMapping(value = ["/"])
class MainController {
    @Autowired
    private val qrcode: Qrcode? = null

    @Autowired
    private val fileupload: FileUpload? = null

    @Value("\${web.file-path}")
    private val uploadPath: String? = null
    private val logger = Logger.getLogger(javaClass.toString())
    @RequestMapping(value = [""])
    fun hello(): String {
        return "index"
    }

    @RequestMapping(value = ["/index"])
    fun index(): String {
        return "index"
    }

    @GetMapping(value = ["/qrcode"])
    fun qrcode(): String {
        return "index"
    }

    @RequestMapping(value = ["/qrcode"], method = [RequestMethod.POST])
    @ResponseBody
    @Throws(IOException::class)
    fun qrcode(map: Model?, request: HttpServletRequest): Result<*> {
        val url = request.getParameter("url")
        val fileName = qrcode!!.create_qrcode(url)
        return ResultUtil.success(fileName)
    }

    @RequestMapping(value = ["/download/{file_name}"])
    @Throws(UnsupportedEncodingException::class)
    fun downloadImg(@PathVariable("file_name") fileName: String?, res: HttpServletResponse?) {
        qrcode!!.download(res, fileName)
    }

    @RequestMapping(value = ["/filedown/{file_name}"])
    @Throws(UnsupportedEncodingException::class)
    fun downloadFile(@PathVariable("file_name") fileName: String, res: HttpServletResponse) {
        fileupload?.download(res, fileName)
    }

    @GetMapping(value = ["/upload"])
    fun uploadFileGet(): String {
        return "upload"
    }

    @PostMapping(value = ["/upload"])
    @Throws(IOException::class)
    fun uploadFilePost(@RequestParam("file") file: MultipartFile): String {
        val fileName = file.originalFilename
        val tmpFile = File(fileName)
        val realName = tmpFile.name
        logger.info("upload file -----------------------$realName")
        fileupload!!.saveUploadFile(file.bytes, realName)
        return "redirect:/file"
    }

    @ResponseBody
    @RequestMapping(value = ["/file"])
    fun listFile(): Result<*> {
        val result = fileupload!!.get_filelist(false)
        return ResultUtil.success(result)
    }

    @RequestMapping(value = ["/zhuang"])
    fun zhuang(map: Model): String {
        map.addAttribute("name", "冯文韬")
        map.addAttribute("tel", "15110202919")
        map.addAttribute("addr", "海淀区厢黄旗东路柳浪家园南里26号楼1单元701")
        return "zhuang"
    }

    @RequestMapping(value = ["/list"])
    fun listHeader(request: HttpServletRequest, map: Model): String {
        val headerList: Enumeration<*> = request.headerNames
        val tmpMap = HashMap<String, String>(16)
        while (headerList.hasMoreElements()) {
            val name = headerList.nextElement().toString()
            val value = request.getHeader(name)
            tmpMap.put(name,value)
        }
        tmpMap["ip"] = request.remoteAddr
        map.addAttribute("headers", tmpMap)
        return "list"
    }

    @ResponseBody
    @RequestMapping(value = ["/test"])
    fun getFolder(request: HttpServletRequest?, map: Model?): String {
        return HttpUtil.get("http://web.bjsasc.com")
    }
}