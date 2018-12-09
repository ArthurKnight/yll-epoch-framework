package com.yll.springcloud.democonsumermovie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 服务消费者
 * @author Arthur
 * @date 2018-12-08 20:58
 */
@RestController
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServerUrl}")
    private String userServerUrl;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://provider-user/"+ id, User.class);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 查询provider-user的元数据信息并返回
     * @return
     */
    @GetMapping("/user-info")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("discovery-eureka-1");
    }

    @GetMapping("/log-instance")
    public void showLog() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("provider-user");
        logger.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return userFeignClient.findByIdFeign(id);
    }
}

