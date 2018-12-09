package com.yll.springcloud.democonsumermovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServerUrl}")
    private String userServerUrl;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject(userServerUrl+ id, User.class);
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
}

