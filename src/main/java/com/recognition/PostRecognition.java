package com.recognition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PostRecognition {

    public String getLicenseNumber(File file) throws IOException {
        String url = "https://api-cn.faceplusplus.com/imagepp/v1/licenseplate";
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpClient client = HttpClients.createDefault();

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("api_key", "0si_RJpnfeEv02V_E8NeLJf77KQyfT5R");
        builder.addTextBody("api_secret", "QX28Zy7q01LeiXvHJ-bGamllW5JImWEZ");
        builder.addBinaryBody(
                "image_file",
                new FileInputStream(file),
                ContentType.APPLICATION_OCTET_STREAM,
                file.getName()
        );
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("responseEntity is >" + responseEntity);
        String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
        System.out.println(sResponse);
        Map map = JSON.parseObject(sResponse, Map.class);
        System.out.println(map);
        JSONArray jsonArray = (JSONArray) map.get("results");
        JSONObject jsonObject =(JSONObject) jsonArray.get(0);
        System.out.println(jsonObject.getString("license_plate_number"));
        return jsonObject.getString("license_plate_number");
    }

}
