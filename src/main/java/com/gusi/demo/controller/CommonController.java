package com.gusi.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController
{
    @RequestMapping("/")
    public String index()
    {
        return "hello spring boot!";
    }
}
