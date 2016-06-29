/**
 * Chsi
 * Created on 2016年6月29日
 */
package com.teach.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.teach.config.FileCofig;
import com.teach.queue.RequestQueue;
import com.teach.tool.HtmlTool;
import com.teach.tool.WordUtil;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class BookReviewGenerator implements Runnable {
    private RequestQueue requestQueue;

    public BookReviewGenerator(RequestQueue requestQueue) {
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
            String title = doc.select(".maincontainer .entry strong").text();
            String html = doc.select("#content .content").html();
            System.out.println(title);
            String type = requestQueue.getBookReviewType().getTitle() + "读后感/";
            WordUtil.convertToWord(html, title, type, FileCofig.BOOKERVIEW);
        } catch (IOException e) {
            System.out.println("获取详情页面失败");
        }
    }
}
