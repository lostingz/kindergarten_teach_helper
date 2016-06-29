/**
 * Chsi
 * Created on 2016年6月27日
 */
package com.web.plan.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.teach.config.ResourceURL;
import com.teach.model.ResourceModel;
import com.teach.tool.HtmlTool;
import com.teach.type.ClassType;
import com.teach.type.PlanType;
import com.web.plan.service.PlanService;
import com.web.redis.KeyGenerator;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
@Service("planService")
public class PlanServiceImpl implements PlanService {

    @Autowired
    private RedisTemplate<String, List<ResourceModel>> redisTemplate;
    @Autowired
    private StringRedisTemplate redisStringTemplate;

    @Override
    public List<ResourceModel> getUrlTaskList(ClassType type, PlanType planType) {
        String key = KeyGenerator.getKey(getClass(), "getUrlTaskList",
                String.valueOf(type.ordinal()) + String.valueOf(planType.ordinal()));
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.opsForValue().get(key);
        } else {
            String url = PlanType.getPlanListUrl(type, planType);
            List<ResourceModel> result = new ArrayList<ResourceModel>();
            if (!StringUtils.isBlank(url)) {
                try {
                    Document doc = HtmlTool.getDocument(url);
                    int totalPage = getTotalPage(doc, url);
                    if (totalPage != -1) {
                        String baseUrl = url.substring(0, url.indexOf(".htm") - 1);
                        for (int i = 1; i <= totalPage; i++) {
                            result.addAll(getItemUrlList(baseUrl + i + ".htm"));
                        }
                    }
                } catch (IOException e) {
                    System.out.println("获取列表失败");
                }
            } else {
                System.out.println("！！！没有该类别的教案,请重新选择");
            }
            if (result.size() > 0) {
                redisTemplate.opsForValue().set(key, result);
            }
            return result;
        }
    }

    private int getTotalPage(Document doc, String url) {
        String key = KeyGenerator.getKey(getClass(), "getTotalPage", url);
        if (redisStringTemplate.hasKey(key)) {
            return Integer.parseInt(redisStringTemplate.opsForValue().get(key));
        } else {
            int total = 0;
            try {
                String href = doc.select(".navlist_mid #green-blacks a").last().attr("href");
                total = Integer.parseInt(href.substring(href.indexOf("p/") + 2, href.indexOf(".htm")));
            } catch (Exception e) {
                total = -1;
            }
            if (total != 0) {
                redisStringTemplate.opsForValue().set(key, String.valueOf(total));
            }
            return total;
        }

    }

    private List<ResourceModel> getItemUrlList(String url) {
        List<ResourceModel> result = new ArrayList<ResourceModel>();
        try {
            Document doc = HtmlTool.getDocument(url);
            Elements elems = doc.select(".navlist_mid .ja_list ol li a");
            for (Element elem : elems) {
                result.add(new ResourceModel(ResourceURL.BASE + elem.attr("href"), elem.attr("title"), elem
                        .siblingElements().get(0).text()));
            }
        } catch (IOException e) {
            System.out.println("获取列表项页面url失败");
        }
        return result;
    }
}
