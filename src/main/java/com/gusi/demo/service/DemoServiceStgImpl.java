package com.gusi.demo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("stg")
public class DemoServiceStgImpl implements DemoService
{
    @Override
    public void demoMethod(String name)
    {
        System.out.println(name + " call stg method!");
    }
}
