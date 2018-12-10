package com.yll.springcloud.demozuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul过滤器
 *
 * @author luliang_yu
 * @date 2018-12-10
 */

public class PreRequestLogFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PreRequestLogFilter.class);

    /**
     * 过滤器类型
     * pre route post error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否要执行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info("send "+request.getMethod()+" to "+request.getRequestURL().toString());
        return null;
    }
}
