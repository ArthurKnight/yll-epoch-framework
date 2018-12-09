package com.yll.springcloud.demoprovideruser;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author Arthur
 * @date 2018-12-09 17:28
 */
@Component
public class UserAppHealthCheck implements HealthIndicator {


    private boolean up = true;

    @Override
    public Health health() {
        if (up) {
            //自定义监控内容
            return new Health.Builder().withDetail("aaa_cnt", 10)
                    .withDetail("user_app_status", "up").up().build();
        } else {
            return new Health.Builder().withDetail("error", "client is down").down().build();
        }

    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
