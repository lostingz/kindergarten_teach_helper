/**
 * Chsi
 * Created on 2016年3月8日
 */
package com.baidu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.baidu.config.BaiduConstants;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class URLUtil {
    private static String[] dic = new String[] {"0:7", "1:d", "2:g", "3:j", "4:m", "5:o", "6:r", "7:u", "8:1", "9:4", "a:0",
            "b:8", "c:5", "d:2", "e:v", "f:s", "g:n", "h:k", "i:h", "j:e", "k:b", "l:9", "m:6", "n:3", "o:w", "p:t", "q:q",
            "r:p", "s:l", "t:i", "u:f", "v:c", "w:a"};

    private static String getChar(String key) {
        for (int i = 0; i < dic.length; i++) {
            String[] kv = dic[i].split(":");
            if (key.equals(kv[0])) {
                return kv[1];
            }
        }
        return key;
    }

    public static String decodeToUrl(String encodeUrl) {
        StringBuffer url = new StringBuffer();
        encodeUrl = encodeUrl.replace("AzdH3F", "/");
        encodeUrl = encodeUrl.replace("_z2C$q", ":");
        encodeUrl = encodeUrl.replace("_z&e3B", ".");
        for (int i = 0; i < encodeUrl.length(); i++) {
            String key = String.valueOf(encodeUrl.charAt(i));
            String value = getChar(key);
            url.append(value);
        }
        System.out.println(url.toString());
        return url.toString();
    }

    public static String getQueryUrl(String word, String pageNum, String returnNum) {
        return BaiduConstants.URL + "&word=" + word + "&pn=" + pageNum + "&rn=" + returnNum + "";
    }

    public static boolean isHttps(String url) {
        return url.substring(0, 5).equals("https");
    }

    public static void downloadImgByUrl(String url, String keyword) {
        HttpClient httpClient = HttpClients.createMinimal();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();// 设置请求和传输超时时间
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            byte[] b = new byte[1024];
            int len = 0;
            File folder = new File(BaiduConstants.IMG_PATH + keyword.trim() + "/");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            OutputStream out = new FileOutputStream(BaiduConstants.IMG_PATH + keyword.trim() + "/"
                    + System.currentTimeMillis() + ".jpg");
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.close();
            System.out.println(url + "下载完成");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
