package com.gusi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusi.demo.pojo.User;
import com.gusi.demo.service.UserService;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-12-22
 * <p>
 * Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id)
    {
        User user = userService.queryUser(id);
        return user;
    }
}