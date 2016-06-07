/**
 * Chsi
 * Created on 2016年6月2日
 */
package com.teach.type;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public enum ClassType {
    BIG("大班"),
    MIDDLE("中班"),
    SMALL("小班"),
    MINI("托班");

    private ClassType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
