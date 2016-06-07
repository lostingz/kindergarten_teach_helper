/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.parser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.teach.config.ResourceURL;
import com.teach.queue.RequestQueue;
import com.teach.tool.HtmlTool;
import com.teach.type.CoursewareType;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class CoursewareParser {
    private RequestQueue requestQueue;

    public CoursewareParser(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public boolean getUrlTaskList(CoursewareType type) {
        String url = CoursewareType.getCoursewareListUrl(type);
        if (!StringUtils.isBlank(url)) {
            try {
                Document doc = HtmlTool.getDocument(url);
                int totalPage = getTotalPage(doc);
                String baseUrl = url.substring(0, url.indexOf(".htm") - 1);
                for (int i = 1; i <= totalPage; i++) {
                    getItemUrl(baseUrl + i + ".htm");
                }
            } catch (IOException e) {
                System.out.println("获取列表失败");
            }
            return true;
        } else {
            System.out.println("！！！没有该类别的课件,请重新选择");
            return false;
        }
    }

    private void getItemUrl(String url) {
        try {
            Document doc = HtmlTool.getDocument(url);
            Elements elems = doc.select("#scbox_mid2 ul li");
            for (Element elem : elems) {
                Element item = elem.select("p").first().select("a").first();
                requestQueue.addRequest(ResourceURL.BASE + item.attr("href"));
            }
        } catch (IOException e) {
            System.out.println("获取列表项页面url失败");
        }
    }

    private int getTotalPage(Document doc) {
        int total = 0;
        String href = doc.select("#scbox_mid2 #green-blacks a").last().attr("href");
        total = Integer.parseInt(href.substring(href.indexOf("p/") + 2, href.indexOf(".htm")));
        return total;
    }

    public static void parse() {
        System.out.println("================================课件生成系统================================\n");
        System.out.println("生成的文档存储在D:\\课件 目录下面,按照 类型 分别存在不同文件夹,生成过程中可以随时关闭,直接关闭这个黑色的框就停止了\n");
        System.out.println("输入课件的类型（键盘输入对应得数字，回车即可）\n");
        Scanner sc = new Scanner(System.in);
        List<CoursewareType> typeList = CoursewareType.getCoursewareNames();
        for (int i = 0; i < typeList.size(); i++) {
            System.out.println((i + 1) + "." + typeList.get(i).getName());
        }
        System.out.println("\n输入课件的类型（键盘输入对应得数字，回车即可）\n");
        System.out.println("请输入:");
        int pType = sc.nextInt();
        CoursewareType type = CoursewareType.getCoursewareTypeByOrdinal(pType - 1);
        RequestQueue requestQueue = new RequestQueue();
        requestQueue.setCoursewareType(type);
        CoursewareParser p = new CoursewareParser(requestQueue);
        p.getUrlTaskList(requestQueue.getCoursewareType());
        new Thread(new CoursewareGenerator(requestQueue)).start();
    }
}
