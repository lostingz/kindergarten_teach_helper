/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.teach.config.ResourceURL;
import com.teach.queue.RequestQueue;
import com.teach.tool.HtmlTool;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class CoursewareGenerator implements Runnable {

    private RequestQueue requestQueue;

    public CoursewareGenerator(RequestQueue requestQueue) {
        super();
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            if (requestQueue.isComplete()) {
                System.out.println("finish");
                break;
            } else {
                final String itemUrl = requestQueue.getRequest();
                generate(itemUrl);
            }
        }
    }

    private void generate(String url) {
        try {
            Document doc = HtmlTool.getDocument(url);
            String title = doc.select(".art_top h2").text();
            String videoUrl = doc.select(".art_mid .nr embed").attr("src");
            if (videoUrl.indexOf("http") < 0) {
                videoUrl = ResourceURL.BASE + videoUrl;
            }
            String mime = videoUrl.substring(videoUrl.lastIndexOf("."), videoUrl.length());
            HtmlTool.downFromUrl(videoUrl, title, requestQueue.getCoursewareType().getName(), mime);
           
        } catch (IOException e) {
            System.out.println("获取详情页面失败");
        }
    }
}
