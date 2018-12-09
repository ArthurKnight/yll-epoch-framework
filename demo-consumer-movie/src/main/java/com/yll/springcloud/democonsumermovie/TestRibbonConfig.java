package com.yll.springcloud.democonsumermovie;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon配置
 *
 * @author Arthur
 * @date 2018-12-09 18:39
 */
@Configuration
@RibbonClient(name = "provider-user",configuration = RibbonConfiguration.class)
public class TestRibbonConfig {
}
