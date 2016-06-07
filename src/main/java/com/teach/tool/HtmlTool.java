/**
 * Chsi
 * Created on 2016年6月2日
 */
package com.teach.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.teach.config.FileCofig;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class HtmlTool {
    public static Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").timeout(5000)
                .get();
        return doc;
    }

    public static void downFromUrl(String url, String title, String type, String mime) throws ClientProtocolException,
            IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        byte[] b = new byte[1024];
        int len = 0;
        File dir = new File(FileCofig.KEJIAN + type + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(FileCofig.KEJIAN + type + "/" + title + mime);
        if (!f.exists()) {
            f.createNewFile();
        }
        OutputStream out = new FileOutputStream(f);
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        out.close();
        System.out.println("下载" + title + "完成");
    }
}
