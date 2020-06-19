package com.asuna.textutils;

import com.asuna.textutils.dao.AccountDAO;
import com.asuna.textutils.entity.Account;
import com.asuna.textutils.entity.TranslateAPIProperty;
import com.asuna.textutils.utils.security.RSAUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

    @Test
    public void testRSA() {
        try {
            RSAUtils.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrivateKeyRead() throws IOException, NoSuchAlgorithmException {
        System.out.println(RSAUtils.getPublicKey());
    }

}
