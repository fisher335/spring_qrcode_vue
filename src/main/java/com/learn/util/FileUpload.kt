package com.learn.util;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Component
public class FileUpload {
    @Value("${web.file-path:./file}")
    private String upload_path;

    public void saveUploadFile(byte[] file, String file_name) throws IOException {

        if (!FileUtil.isDirectory(upload_path)) {
            FileUtil.mkdir(upload_path);
        }
        FileOutputStream out = new FileOutputStream(upload_path + File.separator + file_name);
        out.write(file);
        out.flush();
        out.close();
    }

    public List<Map<String, String>> get_filelist(boolean isAddDirectory) {

        String directoryPath = upload_path;
        List<String> ls = FileUtil.listFileNames(directoryPath);
//        StaticLog.info(ls.toString());
        List<Map<String, String>> list = new ArrayList<>();
        for (String s : ls) {
            list.add(new HashMap<String, String>() {{
                put("name", s);
                put("url", "/download/" + s);
                put("date", "2021-12-20");
                put("size", String.valueOf(FileUtil.size(new File(directoryPath + File.separator + s))));
            }});
        }
        return list;
    }

    // 过滤特殊字符 p
    // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
    // 清除掉所有特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    public void download(HttpServletResponse res, String file_name) throws IOException {

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file_name, "UTF-8"));


        String filePath = upload_path + File.separator + file_name;
        InputStream inputStream = new FileInputStream(filePath);// 文件的存放路径
        res.reset();
        res.setContentType("application/octet-stream");
        String filename = new File(filePath).getName();
        res.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = res.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }
}
