/**
 * Chsi
 * Created on 2016年6月27日
 */
package com.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
@Controller
@EnableAutoConfiguration
public class TestController {

    @Value("${name}")
    private String name;
    @Autowired
    private DataSource ds;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() throws Exception {
        System.out.println(ds.toString());
        return "asdfdddd" + name;
    }
}
