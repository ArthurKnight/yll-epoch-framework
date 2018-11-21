package com.github.yll.epoch.business.admin.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luliang_yu
 * @date 2018-11-21
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    /**
     * 格式化
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //前端日期字符串格式化为java.util.Date
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


    }

    /**
     * 跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    /**
     * URI到视图的映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
