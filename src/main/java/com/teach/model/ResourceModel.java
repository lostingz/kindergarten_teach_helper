/**
 * Chsi
 * Created on 2016年6月2日
 */
package com.teach.model;


/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class ResourceModel {
    private String url;
    private String title;
    private String datetime;

    public ResourceModel(String url, String title) {
        super();
        this.url = url;
        this.title = title;
    }

    public ResourceModel(String url, String title, String datetime) {
        super();
        this.url = url;
        this.title = title;
        this.datetime = datetime;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
