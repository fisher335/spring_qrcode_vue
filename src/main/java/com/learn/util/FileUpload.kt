package com.learn.util

import cn.hutool.core.io.FileUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.*
import java.net.URLEncoder
import java.text.DecimalFormat
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import javax.servlet.http.HttpServletResponse

@Component
class FileUpload {
    @Value("\${web.file-path:./file}")
    private val upload_path: String? = null

    @Throws(IOException::class)
    fun saveUploadFile(file: ByteArray?, file_name: String) {
        if (!FileUtil.isDirectory(upload_path)) {
            FileUtil.mkdir(upload_path)
        }
        val out = FileOutputStream(upload_path + File.separator + file_name)
        out.write(file)
        out.flush()
        out.close()
    }

    fun get_filelist(isAddDirectory: Boolean): List<Map<String, String>> {
        val directoryPath = upload_path
        val ls = FileUtil.listFileNames(directoryPath)
        //        StaticLog.info(ls.toString());
        var listr = mutableListOf<Map<String, String>>()
        for (fileName in ls) {
            var temp = hashMapOf<String, String>()
            temp["name"] = fileName
            temp["url"] = "/download/$fileName"
            temp["date"] = "2021-12-20"
            temp["size"] = getSize(FileUtil.size(File(directoryPath + File.separator + fileName)).toString())
            listr.add(temp)
        }
        return listr
    }

    @Throws(IOException::class)
    fun download(res: HttpServletResponse, file_name: String) {
        res.setHeader("content-type", "application/octet-stream")
        res.contentType = "application/octet-stream"
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file_name, "UTF-8"))
        val filePath = upload_path + File.separator + file_name
        val inputStream: InputStream = FileInputStream(filePath) // 文件的存放路径
        res.reset()
        res.contentType = "application/octet-stream"
        val filename = File(filePath).name
        res.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"))
        val outputStream = res.outputStream
        val b = ByteArray(1024)
        var len: Int
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while (inputStream.read(b).also { len = it } > 0) {
            outputStream.write(b, 0, len)
        }
        inputStream.close()
    }

    companion object {
        // 过滤特殊字符 p
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        @Throws(PatternSyntaxException::class)
        fun StringFilter(str: String?): String {
            val regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"
            val p = Pattern.compile(regEx)
            val m = p.matcher(str)
            return m.replaceAll("").trim { it <= ' ' }
        }
    }


    fun getSize(o: String?): String {
        val i = Integer.valueOf(o).toLong()
        var result = ""
        val kb: Long = 1024
        val mb = kb * 1024
        val gb = mb * 1024

        /*实现保留小数点两位*/
        val df = DecimalFormat("#.00")
        result = if (i >= gb) {
            df.format((i.toFloat() / gb).toDouble()) + "GB"
        } else if (i >= mb) {
            df.format((i.toFloat() / mb).toDouble()) + "MB"
        } else if (i >= kb) {
            String.format("%.2f", i.toFloat() / kb) + "KB"
        } else {
            i.toString() + "B"
        }
        return result
    }

}