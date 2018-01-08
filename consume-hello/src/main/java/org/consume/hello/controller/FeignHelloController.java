package org.consume.hello.controller;

import org.consume.hello.feign.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author: liangzhenwei
* @create：2018年1月5日 下午4:37:09 
* @company:广州荔支网络技术有限公司
*/
@RestController
public class FeignHelloController {
//http://localhost:8764/feign/hi?name=forez
    @Autowired
    SchedualServiceHi schedualServiceHi;
    @RequestMapping(value = "/feign/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }


}
