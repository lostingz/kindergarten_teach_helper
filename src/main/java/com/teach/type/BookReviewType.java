/**
 * Chsi
 * Created on 2016年6月29日
 */
package com.teach.type;

import java.util.Arrays;
import java.util.List;

import com.teach.config.ResourceURL;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public enum BookReviewType {
    LONG("长篇", "fenlei/9"),
    HUNDRED_8("800字", "fenlei/7"),
    THOUSAND_1("1000字", "fenlei/8"),
    READ("开卷有益", "fenlei/23"),
    CHILD_PARENT("亲子", "fenlei/18");
    private String title;
    private String subUrl;

    private BookReviewType(String title, String subUrl) {
        this.title = title;
        this.subUrl = subUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public static List<BookReviewType> getBookReviewNames() {
        return Arrays.asList(LONG,HUNDRED_8,THOUSAND_1, READ,CHILD_PARENT);
    }

    public static String getBookReviewListUrl(BookReviewType type) {
        return ResourceURL.BOOKREVIEWBASE + type.getSubUrl();
    }
}
