/**
 * Chsi
 * Created on 2016年6月29日
 */
package com.web.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.teach.model.ResourceModel;
import com.teach.type.ClassType;
import com.teach.type.PlanType;
import com.web.redis.KeyGenerator;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
@Service("planCacheService")
public class PlanServiceCacheImpl extends PlanServiceImpl {
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
            List<ResourceModel> result = super.getUrlTaskList(type, planType);
            if (result.size() > 0) {
                redisTemplate.opsForValue().set(key, result);
            }
            return result;
        }
    }
}
