package com.github.yll.epoch.business.admin.config;

import com.github.yll.epoch.business.admin.controller.test.RedisChannelListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author luliang_yu
 * @date 2018-12-04
 */
@Configuration
public class RedisConfig {

    @Bean
    MessageListenerAdapter adapter() {
        return new MessageListenerAdapter(new RedisChannelListener());
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory factory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(listenerAdapter, new PatternTopic("news.**"));
        return container;
    }
}
