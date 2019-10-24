package com.asuna.textutils;

import com.asuna.textutils.dao.AccountDAO;
import com.asuna.textutils.entity.Account;
import com.asuna.textutils.entity.TranslateAPIProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextUtilsApplicationTests {

    @Autowired
    private TranslateAPIProperty translateAPIProperty;

    @Autowired
    private AccountDAO dao;
    @Test
    public void contextLoads() {
        Account account = dao.getAccountByName("asuna");
        System.out.println(account);
    }

}
