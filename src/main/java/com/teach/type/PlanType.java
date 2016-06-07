/**
 * Chsi
 * Created on 2016年6月2日
 */
package com.teach.type;

import java.util.Arrays;
import java.util.List;

import com.teach.config.ResourceURL;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public enum PlanType {
    SCIENCE("科学", 564, 574, 584, 594),
    LANGUAGE("语言", 565, 575, 585, 595),
    ART("美术", 566, 576, 772, 774),
    MUSIC("音乐", 567, 577, 771, 773),
    SOCIETY("社会", 569, 579, 589, 599),
    TOPIC("主题", 570, 580, 590, 600),
    CHILD_PARENT("亲子", 780, 782, 784, 786),
    HALFDAY("半日", 781, 783, 785, 0),
    HEALTH("健康", 568, 578, 587, 597);

    private String name;
    private int bigClassId;
    private int middleClassId;
    private int smallClassId;
    private int miniClassId;

    private PlanType(String name, int big, int middle, int small, int mini) {
        this.name = name;
        this.bigClassId = big;
        this.middleClassId = middle;
        this.smallClassId = small;
        this.miniClassId = mini;
    }

    public String getName() {
        return name;
    }

    public int getBigClassId() {
        return bigClassId;
    }

    public int getMiddleClassId() {
        return middleClassId;
    }

    public int getSmallClassId() {
        return smallClassId;
    }

    public int getMiniClassId() {
        return miniClassId;
    }

    public static String getPlanListUrl(ClassType type, PlanType planType) {
        if (type.equals(ClassType.BIG)) {
            return ResourceURL.PLAN + planType.bigClassId + "/p/1.htm";
        } else if (type.equals(ClassType.MIDDLE)) {
            return ResourceURL.PLAN + planType.middleClassId + "/p/1.htm";
        } else if (type.equals(ClassType.SMALL)) {
            return ResourceURL.PLAN + planType.smallClassId + "/p/1.htm";
        } else if (type.equals(ClassType.MINI)) {
            if (planType.miniClassId != 0) {
                return ResourceURL.PLAN + planType.miniClassId + "/p/1.htm";
            }
        }
        return null;
    }

    public static List<PlanType> getPlanNames() {
        return Arrays.asList(SCIENCE, LANGUAGE, ART, MUSIC, SOCIETY, TOPIC, CHILD_PARENT, HALFDAY, HEALTH);
    }

    public static PlanType getPlanTypeByOrdinal(int ordinal) {
        for (PlanType type : PlanType.values()) {
            if (type.ordinal() == ordinal) {
                return type;
            }
        }
        return PlanType.ART;
    }

    public static void main(String[] args) {
        getPlanNames();
    }
}
