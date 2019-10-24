package com.asuna.textutils.http;

import com.asuna.textutils.entity.TemplateResultBase;
import com.asuna.textutils.entity.TranslateResult;
import com.asuna.textutils.utils.GenUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TransRequest {
    private String url;
    private Map<String, String> params;

    public TransRequest(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
    }

    /**
     * 向有道后台请求翻译数据
     * @return
     * @throws IOException
     */
    public TemplateResultBase<TranslateResult> excute() throws IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> paramList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            paramList.add(new BasicNameValuePair(key, value));
        }
        post.setEntity(new UrlEncodedFormEntity(paramList,"UTF-8"));
        CloseableHttpResponse response = httpClient.execute(post);
        Header[] contentType = response.getHeaders("Content-Type");
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
        TemplateResultBase<TranslateResult> result = GenUtils.jsonToTranslateResult(json);
        return result;
    }
}
