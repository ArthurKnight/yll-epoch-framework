package com.github.yll.epoch.business.admin.service;


import com.github.yll.epoch.business.admin.model.User;
import org.springframework.stereotype.Service;

/**
 * @author luliang_yu
 * @date 2018-11-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Long id) {
        return new User(id,"user");
    }
}
