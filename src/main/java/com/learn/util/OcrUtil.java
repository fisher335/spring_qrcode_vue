package com.learn.util;

import com.baidu.aip.ocr.AipOcr;
import com.learn.Entity.OcrClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
@Configuration
public class OcrUtil {

    @Autowired
    private OcrClient client;

    public  String getOcr(byte[] file) {


        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        JSONObject res = client.basicGeneral(file, options);
        System.out.println(res.toString());
        return res.toString();
    }
}
