package com.etop.weixin.utils;

import com.etop.weixin.utils.weixinUtils.LogUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http客户端工具类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class HttpUtil {

    private static CloseableHttpClient createHttpClient(boolean isHttps) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        if (isHttps) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        }
        return httpclient;
    }

    private static String createHtml(HttpResponse response) throws IOException {
        HttpEntity httpEntity = response.getEntity();
        String html = null;
        if (httpEntity != null) {
            html = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
        }
        return html;
    }

    /**
     * 发起POST请求，并以字符串形式返回结果
     * @param url 请求的目标URL
     * @param str 请求数据（可为JSON字符串）
     * @return 字符串形式返回的结果，如果出错或没有结果则返回null
     */
    public static String post(String url, String str) {
        try {
            CloseableHttpClient httpclient = createHttpClient(url.startsWith("https://"));
            HttpPost httppost = new HttpPost(url);
            StringEntity s = new StringEntity(str, "UTF-8");
            httppost.setEntity(s);
            return createHtml(httpclient.execute(httppost));
        } catch (NoSuchAlgorithmException | IOException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
            LogUtil.error("post请求失败", e);
        }
        return null;
    }

    /**
     * 发起POST请求，并以字符串形式返回结果
     * @param url 请求的目标URL
     * @param map 请求数据(以名值对的方式传入)
     * @return 字符串形式返回的结果，如果出错或没有结果则返回null
     */

    public static String post(String url, Map<String, String> map) {
        try {
            CloseableHttpClient httpclient = createHttpClient(url.startsWith("https://"));
            HttpPost httppost = new HttpPost(url);
            if (map != null) {
                List<NameValuePair> nvps = new ArrayList<>();
                map.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
                httppost.setEntity(uefEntity);
            }
            return createHtml(httpclient.execute(httppost));
        } catch (NoSuchAlgorithmException | IOException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
            LogUtil.error("post请求失败", e);
        }
        return null;
    }

    /**
     * 发起GET请求，并以字符串形式返回结果
     * @param url 请求的目标URL
     * @return 字符串形式返回的结果，如果出错或没有结果则返回null
     */

    public static String get(String url) {
        try {
            CloseableHttpClient httpclient = createHttpClient(url.startsWith("https://"));
            HttpGet httpGet = new HttpGet(url);
            return createHtml(httpclient.execute(httpGet));
        } catch (NoSuchAlgorithmException | IOException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
            LogUtil.error("get请求失败", e);
        }
        return null;
    }
}
