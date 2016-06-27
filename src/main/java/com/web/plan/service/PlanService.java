/**
 * Chsi
 * Created on 2016年6月27日
 */
package com.web.plan.service;

import java.util.List;

import com.teach.model.ResourceModel;
import com.teach.type.ClassType;
import com.teach.type.PlanType;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public interface PlanService {
    List<ResourceModel> getUrlTaskList(ClassType type, PlanType planType);
}
