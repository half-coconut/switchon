package com.coconut.cases.cccc2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/20 23:06
 * File: HttpClientFor8801
 */

/**
 * 第二周 第6道：使用 HttpClient 或 OkHttp访问 http://localhost:8801
 */
public class HttpClientFor8801 {

    public static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String getRequestToString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801/";
        String response = HttpClientFor8801.getRequestToString(url);
        System.out.println("访问地址为: " + url + "\n响应内容为: " + response);
    }
}
