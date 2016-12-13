package com.gusi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusi.demo.dao.UserDao;
import com.gusi.demo.pojo.User;

@Service
public class UserService
{
    @Autowired
    private UserDao<User> userDao;

    public User queryUser(long id)
    {
        return userDao.getObjectById(id);
    }
}
