package com.evan.wj.service.Impl;

import com.evan.wj.dao.UserDao;
import com.evan.wj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl  {
    @Autowired
    UserDao userDao;

    public boolean isExist(String username){
        User user =  getByUsername(username);
        return null!=user;
    }

    public User getByUsername(String username){
       return userDao.findByUsername(username);
    }

    public User get(String username,String password){
        return userDao.getByUsernameAndPassword(username,password);
    }

    public void save(User user){
        userDao.save(user);
    }

}
