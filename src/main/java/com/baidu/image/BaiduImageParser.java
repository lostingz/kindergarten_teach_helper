/**
 * Chsi
 * Created on 2016年6月16日
 */
package com.baidu.image;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class BaiduImageParser {
    public static void parse() {
        System.out.println("================================图片下载系统================================\n");
        System.out.println("生成的文档存储在D:\\图片 目录下面,按照 搜索关键字 分别存在不同文件夹,生成过程中可以随时关闭,直接关闭这个黑色的框就停止了\n");
        System.out.println("输入要下载的图片的关键字（如 月亮 即可下载月亮的图片）（键盘输入对应得数字，回车即可）\n");
        Scanner sc = new Scanner(System.in);
        String keyword = sc.next();
        System.out.println("输入要下载图片的个数（指定要下载多少个图片）（键盘输入对应得数字，回车即可）\n");
        String num = sc.next();
        if (StringUtils.isNumeric(num)) {
            ImageSpider.getBaiduImage(keyword, Integer.parseInt(num));
        } else {
            System.out.println("输入的不是数字");
        }
    }
}
