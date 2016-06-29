/**
 * Chsi
 * Created on 2016年6月27日
 */
package com.web.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teach.model.ResourceModel;
import com.teach.type.ClassType;
import com.teach.type.PlanType;
import com.web.plan.service.PlanService;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/plan")
public class PlanController {

    @Autowired
    private PlanService planCacheService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<ResourceModel> getPlan(Integer c, Integer pt) {
        return planCacheService.getUrlTaskList(ClassType.getClassTypeByOrdinal(c), PlanType.getPlanTypeByOrdinal(pt));
    }
}
