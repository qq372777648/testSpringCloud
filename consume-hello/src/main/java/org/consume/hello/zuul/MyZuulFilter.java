package org.consume.hello.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/** 
* @author: liangzhenwei
* @create：2018年1月5日 下午6:39:27 
* @company:广州荔支网络技术有限公司
*/

//http://localhost:8764/feign/hi?name=asdfsd   -----------------pass
//http://localhost:8764/api-a/feign/hi?name=forez---------------notpass
//http://localhost:8764/api-a/feign/hi?name=forez&token=asd-----pass
@Component
public class MyZuulFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);
    @Override
    public String filterType() {
        return "pre";
//        pre：路由之前
//        routing：路由之时
//        post： 路由之后
//        error：发送错误调用
//        filterOrder：过滤的顺序
//        shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
//        run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }
}