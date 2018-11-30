package com.github.yll.epoch.business.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author luliang_yu
 * @date 2018-11-26
 */
@Configuration
public class WeChatConfig {

    @Value("wechat.appid")
    public static String APP_ID;

    @Value("wechat.appsecret")
    public static String APP_SECRET;
}
