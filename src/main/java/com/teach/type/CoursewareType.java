/**
 * Chsi
 * Created on 2016年6月7日
 */
package com.teach.type;

import java.util.Arrays;
import java.util.List;

import com.teach.config.ResourceURL;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public enum CoursewareType {
    SCIENCE("科学", 767),
    LANGUAGE("语言", 351),
    ART("美术", 353),
    MUSIC("音乐", 352),
    SOCIETY("社会常识", 768),
    MATH("数学", 769),
    HEALTH("健康", 354);
    
    private String name;
    private int id;

    private CoursewareType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static String getCoursewareListUrl(CoursewareType type) {
        return ResourceURL.COURSEWARE + type.id + "/p/1.htm";
    }

    public static List<CoursewareType> getCoursewareNames() {
        return Arrays.asList(SCIENCE, LANGUAGE, ART, MUSIC, SOCIETY, MATH, HEALTH);
    }

    public static CoursewareType getCoursewareTypeByOrdinal(int ordinal) {
        for (CoursewareType type : CoursewareType.values()) {
            if (type.ordinal() == ordinal) {
                return type;
            }
        }
        return CoursewareType.ART;
    }
}
