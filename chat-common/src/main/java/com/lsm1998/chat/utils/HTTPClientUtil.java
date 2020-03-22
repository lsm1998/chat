/*
 * 作者：刘时明
 * 时间：2020/3/21-23:26
 * 作用：
 */
package com.lsm1998.chat.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HTTPClientUtil
{
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * 普通表单提交
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static HttpResponse<String> post(String url, Map<String, String> paramMap, Map<String, String> headers) throws IOException, InterruptedException
    {
        StringBuilder param = new StringBuilder();
        if (paramMap != null)
        {
            paramMap.forEach((k, v) ->
            {
                param.append(k);
                param.append("=");
                param.append(v);
                param.append("&");
            });
        }
        if (param.length() > 0)
        {
            param.delete(param.length() - 1, param.length());
        }
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(param.toString()));
        if (headers != null)
        {
            headers.forEach(builder::header);
        }
        return httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> post(String url, String json, Map<String, String> headers) throws IOException, InterruptedException
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json));
        if (headers != null)
        {
            headers.forEach(builder::header);
        }
        return httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }
}
