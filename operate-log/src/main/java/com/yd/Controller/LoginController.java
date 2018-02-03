package com.yd.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
@Controller
@RequestMapping("/Login")
public class LoginController {

    @RequestMapping("")
    public String Login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
