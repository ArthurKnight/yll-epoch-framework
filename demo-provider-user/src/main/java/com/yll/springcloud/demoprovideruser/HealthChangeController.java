package com.yll.springcloud.demoprovideruser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arthur
 * @date 2018-12-09 17:30
 */
@RestController
public class HealthChangeController {

    @Autowired
    UserAppHealthCheck userAppHealthCheck;

    @RequestMapping("/up")
    public String up(@RequestParam("up") Boolean up) {
        userAppHealthCheck.setUp(up);

        return up.toString();
    }

}
