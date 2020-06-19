package com.asuna.textutils.controller;

import com.asuna.textutils.dao.AccountDAO;
import com.asuna.textutils.entity.Account;
import com.asuna.textutils.utils.security.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 7200)
public class AdministratorController {

    @Autowired
    public AccountDAO accountDAO;

    @RequestMapping(value = "/administrator/uap")
    public Object updateAllPassword() throws IOException, NoSuchAlgorithmException {
        List<Account> accounts = accountDAO.getAllAccountPassword();
        for (Account acc:
             accounts) {
            try {
                String newPs = RSAUtils.encrypt(acc.getPassword());
                acc.setPassword(newPs);
            } catch (InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }
}
