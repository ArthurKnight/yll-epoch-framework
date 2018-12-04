package com.github.yll.epoch.business.admin.controller.test;

import com.github.yll.epoch.business.admin.controller.PlayerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Redis
 * @author luliang_yu
 * @date 2018-12-04
 */

public class RedisChannelListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisChannelListener.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] channel = message.getChannel();
        byte[] bs = message.getBody();
        try {
            String content = new String(bs, "UTF-8");
            String channelStr = new String(channel, "UTF-8");
            logger.info("get:"+content+" from "+channelStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
