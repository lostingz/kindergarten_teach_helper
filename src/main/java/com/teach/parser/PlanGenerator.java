/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.teach.queue.RequestQueue;
import com.teach.tool.HtmlTool;
import com.teach.tool.WordUtil;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class PlanGenerator implements Runnable {

    private RequestQueue requestQueue;

    public PlanGenerator(RequestQueue requestQueue) {
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
        Document doc;
        try {
            doc = HtmlTool.getDocument(url);
            String title = doc.select(".art_top h2").text();
            String html = doc.select(".art_mid .nr").html();
            String type = requestQueue.getClassType().getName() + "教案/" + requestQueue.getPlanType().getName();
            WordUtil.convertToWord(html, title, type);
        } catch (IOException e) {
            System.out.println("获取详情页面失败");
        }
    }
}
