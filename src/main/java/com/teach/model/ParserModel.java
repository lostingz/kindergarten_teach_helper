/**
 * Chsi
 * Created on 2016年6月2日
 */
package com.teach.model;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class ParserModel {
    private int parserId;
    private Class parserClass;

    public int getParserId() {
        return parserId;
    }

    public void setParserId(int parserId) {
        this.parserId = parserId;
    }

    public Class getParserClass() {
        return parserClass;
    }

    public void setParserClass(Class parserClass) {
        this.parserClass = parserClass;
    }

}
