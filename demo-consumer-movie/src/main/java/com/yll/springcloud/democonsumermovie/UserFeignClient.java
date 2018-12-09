package com.yll.springcloud.democonsumermovie;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Arthur on 2018/12/9.
 */
@FeignClient(name = "provider-user"/*,fallback = FeignClientFallBack.class,*//*fallbackFactory = FeignClientFallBackFactory.class*/)
public interface UserFeignClient {

    @GetMapping(value = "/{id}")
    User findByIdFeign(@PathVariable(value="id") Long id);
}

@Component
class FeignClientFallBack implements UserFeignClient{
    @Override
    public User findByIdFeign(Long id) {
        User user = new User();
        user.setAge(0);
        user.setId(0L);
        user.setName("默认用户");
        user.setUsername("默认用户名");
        return user;
    }
}
@Component
class FeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger logger = LoggerFactory.getLogger(FeignClientFallBackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findByIdFeign(Long id) {
                logger.info("fallback,reason was:", throwable.getCause());
                User user = new User();
                user.setAge(0);
                user.setId(0L);
                user.setName("默认用户1");
                user.setUsername("默认用户名1");
                return user;
            }
        };
    }
}