package com.funtl.hello.dubbo.service.user.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.funtl.hello.dubbo.service.user.api.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Reference(version = "${user.service.version}")
    private UserService userService;

    @HystrixCommand(fallbackMethod = "haiError")
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHai(){
        return userService.sayHai();

    }

    /**
     * Hytrix熔断方法
     * @return
     */
    public String haiError(){
        return "Hello hystrix";
    }

}
