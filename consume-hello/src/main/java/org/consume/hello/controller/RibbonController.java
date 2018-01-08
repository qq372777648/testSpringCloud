package org.consume.hello.controller;

import org.consume.hello.ribbon.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author: liangzhenwei
* @create：2018年1月5日 下午4:37:09 
* @company:广州荔支网络技术有限公司
*/
@RestController
public class RibbonController {
    //http://192.168.10.131:8764/ribbon/hi?name=asdfsd
    @Autowired
    HelloService helloService;
    @RequestMapping(value = "/ribbon/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }


}
