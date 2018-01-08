package org.consume.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // feign 负载均衡（service层）
@EnableHystrix//RPC 不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。断路打开后，可以直接返回一个固定值。
@EnableHystrixDashboard//  断路器web监控//http://localhost:8764/hystrix  数据源：http://localhost:8764/hystrix.stream，title:任意

//@EnableCircuitBreaker 
@EnableZuulProxy // zuul负载均衡（http层）
public class ConsumeHelloApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeHelloApp.class, args);
    }

    @Bean
    @LoadBalanced //ribbon
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}