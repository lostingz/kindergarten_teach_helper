/**
 * Chsi
 * Created on 2016年6月29日
 */
package com.teach.parser;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.teach.config.ResourceURL;
import com.teach.queue.RequestQueue;
import com.teach.tool.HtmlTool;
import com.teach.type.BookReviewType;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class BookReviewParser {
    private RequestQueue requestQueue;

    public BookReviewParser(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public boolean getUrlTaskList(BookReviewType type) {
        String url = BookReviewType.getBookReviewListUrl(type);
        if (StringUtils.isNotBlank(url)) {
            try {
                Document doc = HtmlTool.getDocument(url);
                Elements elems = doc.select(".catearticlelist ul");
                for (Element element : elems) {
                    Elements items = element.select("li");
                    if (null != items) {
                        String itemUrl = ResourceURL.BOOKREVIEWBASE + items.get(0).select("a").attr("href");
                        requestQueue.addRequest(itemUrl);
                        /*
                         * String title = items.get(0).select("a").text();
                         * String datetime =
                         * items.get(2).select("span").first().text();
                         * System.out.println(title + datetime + itemUrl);
                         */
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            System.out.println("！！！没有该类别的读后感,请重新选择");
            return false;
        }
    }

    public static void parse() {
        RequestQueue q = new RequestQueue();
        System.out.println("================================读后感生成系统================================\n");
        System.out.println("生成的文档存储在D:\\读后感 目录下面,按照选择的类型 生成,生成过程中可以随时关闭,直接关闭这个黑色的框就停止了\n");
        System.out.println("输入读后感的类型（键盘输入对应得数字，回车即可）\n");
        System.out.println("1.长篇     2.800字      3.1000字      4.开卷有益     5.亲子 \n");
        System.out.println("请输入:");
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        while (!StringUtil.in(type, "1", "2", "3", "4", "5")) {
            System.out.println("输错了只能输入 1-5:) ");
            System.out.println("1.长篇     2.800字      3.1000字      4.开卷有益     5.亲子 \n");
            System.out.println("请输入:");
            type = sc.next();
        }
        if (type.equals("1")) {
            q.setBookReviewType(BookReviewType.LONG);
        } else if (type.equals("2")) {
            q.setBookReviewType(BookReviewType.HUNDRED_8);
        } else if (type.equals("3")) {
            q.setBookReviewType(BookReviewType.THOUSAND_1);
        } else if (type.equals("4")) {
            q.setBookReviewType(BookReviewType.READ);
        } else if (type.equals("5")) {
            q.setBookReviewType(BookReviewType.CHILD_PARENT);
        }
        BookReviewParser p = new BookReviewParser(q);
        if (p.getUrlTaskList(q.getBookReviewType())) {
            new Thread(new BookReviewGenerator(q)).start();
        } else {
            parse();
        }
    }
}
