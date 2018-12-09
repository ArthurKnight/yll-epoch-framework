package com.yll.springcloud.democonsumermovie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Arthur on 2018/12/9.
 */
@FeignClient(name = "provider-user")
public interface UserFeignClient {

    @GetMapping(value = "/{id}")
    User findByIdFeign(@PathVariable(value="id") Long id);
}
