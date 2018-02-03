package com.yd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zhengcanbiao
 * @Description:
 * @Date: Created by zhengcanbiao on 2017/11/8.
 */
@Controller
@RequestMapping("/goods")
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("#{configProperties['export2Excel.fileName']}")
    private String fileName;


    /**
     * 获取商品列表
     */
    @RequestMapping(value = "export2Excel", method = RequestMethod.POST)
    public void export2Excel(HttpServletResponse response) throws Exception {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "--请求参数：--" + fileName);

    }

}
