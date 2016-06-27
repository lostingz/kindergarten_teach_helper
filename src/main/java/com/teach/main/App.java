/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.main;

import java.util.Scanner;

import org.jsoup.helper.StringUtil;

import com.baidu.image.BaiduImageParser;
import com.teach.parser.CoursewareParser;
import com.teach.parser.PlanParser;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class App {
    public static void main(String[] args) {
        System.out.println("================================幼儿园文件助手================================\n");
        System.out.println("选择系统（键盘输入对应得数字，回车即可）\n");
        System.out.println("1.教案生成系统           2.课件生成系统      3.图片下载系统\n");
        System.out.println("请输入:");
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        while (!StringUtil.in(type, "1", "2", "3")) {
            System.out.println("输错了只能输入 1-3，:) ");
            System.out.println("1.教案生成系统           2.课件生成系统      3.图片下载系统\n");
            System.out.println("请输入:");
            type = sc.next();
        }
        if (type.equals("1")) {
            PlanParser.parse();
        } else if (type.equals("2")) {
            CoursewareParser.parse();
        } else if (type.equals("3")) {
            BaiduImageParser.parse();
        }
    }
}
