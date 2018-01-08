package org.consume.hello.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/** 
* @author: liangzhenwei
* @create：2018年1月5日 下午4:36:47 
* @company:广州荔支网络技术有限公司
*/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
//        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    	return restTemplate.getForObject("http://service-hi/hi?name="+name,String.class);
    	
//    	why?????  ---版本问题，切换到 <version>Dalston.RC1</version>
//    	I/O error on GET request for "http://service-hi/hi?name=forezp":service-hi; nested exception is java.net.UnknownHostException: service-hi
    	
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}