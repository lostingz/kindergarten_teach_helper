/**
 * Chsi
 * Created on 2016年6月29日
 */
package com.web.redis;



/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class KeyGenerator {
    public static String getKey(Class cls, String methodName, String str) {
        return cls.getCanonicalName() + methodName + str;
    }
}
