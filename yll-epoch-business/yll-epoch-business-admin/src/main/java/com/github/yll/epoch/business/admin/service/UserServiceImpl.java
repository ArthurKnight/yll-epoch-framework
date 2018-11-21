package com.github.yll.epoch.business.admin.service;


import com.github.yll.epoch.business.admin.dao.UserDao;
import com.github.yll.epoch.business.admin.exception.SelfException;
import com.github.yll.epoch.business.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author luliang_yu
 * @date 2018-11-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(Long id) {
        try{
            return userDao.getUserById(id);
        }catch (Exception e){
            throw new SelfException("userService处理异常",e);
        }
    }
}
