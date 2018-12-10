package com.yll.springcloud.demozuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Zuul聚合微服务
 *
 * Zuul
 * 身份认证与安全：识别每个资源的验证请求，并拒绝那些与要求不符的请求。
 * 审查与监控：在边缘位置追踪有意义的数据和统计结果，从而带来精确的生产视图
 * 动态路由：动态地将请求路由到不同的后端集群
 * 压力测试：逐渐增加指向集群的流量以了解性能。
 * 负载分配：为每一种负载类型分配对应容量，并启用超出限定值的请求。
 * 静态响应处理：在边缘位置直接建立部分响应，从而避免其转发到内部集群。
 * 多区域弹性：跨越AWS Region进行请求路由，旨在实现ELB使用的多样化，以及让系统的边缘更贴近系统使用者。
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class DemoZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoZuulApplication.class, args);
    }

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
