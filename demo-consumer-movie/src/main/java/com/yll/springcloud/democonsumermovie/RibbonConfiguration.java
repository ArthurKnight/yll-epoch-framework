package com.yll.springcloud.democonsumermovie;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon的配置类
 *
 * @author Arthur
 * @date 2018-12-09 18:34
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        //return new BestAvailableRule();
        return new RandomRule();
    }

    /*@Bean
    public IPing ribbonPing() {
        return new PingUrl();
    }*/

   /* @Bean
    public ServerList<Server> ribbonServerList(IClientConfig config) {
        return new RibbonClientDefaultConfigurationTestsConfig.BazServiceList(config);
    }*/

    @Bean
    public ServerListSubsetFilter serverListFilter() {
        ServerListSubsetFilter filter = new ServerListSubsetFilter();
        return filter;
    }
}
