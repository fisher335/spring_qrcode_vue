package com.learn.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.learn.util.OcrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class AppendController {

    @Autowired
    private OcrUtil ocrUtil;

    @RequestMapping(value = "/wiki/")
    public void getWiki(HttpServletResponse response) throws IOException {

        response.sendRedirect("http://www.baidu.com");
    }


    @GetMapping(value = "/ocr")
    public String getOcr() {
        return "ocr";
    }

    @PostMapping(value = "/ocr/")
    public String postOcr(@RequestParam("file") MultipartFile file, Model map) throws IOException {
        String result = ocrUtil.getOcr(file.getBytes());
        JSONObject js = JSONUtil.parseObj(result);
        StaticLog.info(js.toString());
        JSONArray jsarray = js.getJSONArray("words_result");
        StringBuffer resultOcr = new StringBuffer();
        for (int i = 0; i < jsarray.size(); i++) {
            JSONObject jsWord = JSONUtil.parseObj(jsarray.get(i));
            resultOcr = resultOcr.append(jsWord.get("words") + "<br>");
        }
        map.addAttribute("ocr", resultOcr.toString());
        return "ocr";
    }

    @ResponseBody
    @RequestMapping(value = "/es")
    public Map<String, String> cryption(@RequestParam Map<String, Object> params) throws IOException {

        String un = params.get("un").toString();
        String pd = params.get("pd").toString();
        String auth = String.format("%s:%s", un, pd);
        String auth1 = baseCry(auth);
        String headerAuth = "Authorization: Basic " + auth1;
        String authString = baseCry(headerAuth);
        Map<String, String> rst = new HashMap<>(4);
        rst.put("header", headerAuth);
        rst.put("stringAuth", authString);
        return rst;
    }

    public String baseCry(String s) throws UnsupportedEncodingException {
        String r = new String(Base64.getEncoder().encode(s.getBytes("utf-8")), "utf-8");
        return r;
    }
}
