package com.asuna.textutils.controller;

import com.asuna.textutils.entity.Account;
import com.asuna.textutils.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/set")
    public Object setPair(String uuid){
        Account account = new Account();
        account.setId(Integer.valueOf(uuid));
        account.setAccount("new account");
        account.setBalance(123.45);
        account.setMail("854300805@qq.com");
        account.setPhone("13300009999");
        account.setGender(true);
        account.setPassword("qwerty90");
        redisUtils.set(uuid, account);
        return account;
    }

    @RequestMapping(value = "/get")
    public Object getPair(String uuid){
        Account account = (Account) redisUtils.get(uuid);
        return account;
    }
}
