/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.parser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.teach.config.ResourceURL;
import com.teach.queue.RequestQueue;
import com.teach.tool.HtmlTool;
import com.teach.type.ClassType;
import com.teach.type.PlanType;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class PlanParser {

    private RequestQueue requestQueue;

    public PlanParser(RequestQueue requestQueue) {
        super();
        this.requestQueue = requestQueue;
    }

    public boolean getUrlTaskList(ClassType type, PlanType planType) {
        String url = PlanType.getPlanListUrl(type, planType);
        if (!StringUtils.isBlank(url)) {
            try {
                Document doc = HtmlTool.getDocument(url);
                int totalPage = getTotalPage(doc);
                String baseUrl = url.substring(0, url.indexOf(".htm") - 1);
                for (int i = 1; i <= totalPage; i++) {
                    getItemUrl(baseUrl + i + ".htm");
                }
                // requestQueue.setComplete(true);
            } catch (IOException e) {
                System.out.println("获取列表失败");
            }
            return true;
        } else {
            System.out.println("！！！没有该类别的教案,请重新选择");
            return false;
        }
    }

    private void getItemUrl(String url) {
        try {
            Document doc = HtmlTool.getDocument(url);
            Elements elems = doc.select(".navlist_mid .ja_list ol li a");
            for (Element elem : elems) {
                requestQueue.addRequest(ResourceURL.BASE + elem.attr("href"));
            }
        } catch (IOException e) {
            System.out.println("获取列表项页面url失败");
        }
    }

    private int getTotalPage(Document doc) {
        int total = 0;
        String href = doc.select(".navlist_mid #green-blacks a").last().attr("href");
        total = Integer.parseInt(href.substring(href.indexOf("p/") + 2, href.indexOf(".htm")));
        return total;
    }

    public static void parse() {
        RequestQueue q = new RequestQueue();
        System.out.println("================================教案生成系统================================\n");
        System.out.println("生成的文档存储在D:\\教案 目录下面,按照 班->科目 生成,生成过程中可以随时关闭,直接关闭这个黑色的框就停止了\n");
        System.out.println("输入班的类型（键盘输入对应得数字，回车即可）\n");
        System.out.println("1.大班           2.中班           3.小班     4.托班 \n");
        System.out.println("请输入:");
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        while (!StringUtil.in(type, "1", "2", "3", "4")) {
            System.out.println("输错了只能输入 1-4，:) ");
            System.out.println("1.大班           2.中班           3.小班     4.托班 \n");
            type = sc.next();
        }
        if (type.equals("1")) {
            q.setClassType(ClassType.BIG);
        } else if (type.equals("2")) {
            q.setClassType(ClassType.MIDDLE);
        } else if (type.equals("3")) {
            q.setClassType(ClassType.SMALL);
        } else if (type.equals("4")) {
            q.setClassType(ClassType.MINI);
        }
        System.out.println("下面是该类型班的所有科目教案\n");
        List<PlanType> typeList = PlanType.getPlanNames();
        for (int i = 0; i < typeList.size(); i++) {
            System.out.println((i + 1) + "." + typeList.get(i).getName());
        }
        System.out.println("\n输入科目的类型（键盘输入对应得数字，回车即可）\n");
        System.out.println("请输入:");
        int pType = sc.nextInt();
        PlanType planType = PlanType.getPlanTypeByOrdinal(pType - 1);
        q.setPlanType(planType);
        PlanParser p = new PlanParser(q);
        if (p.getUrlTaskList(q.getClassType(), q.getPlanType())) {
            new Thread(new PlanGenerator(q)).start();
        } else {
            parse();
        }
    }
}
