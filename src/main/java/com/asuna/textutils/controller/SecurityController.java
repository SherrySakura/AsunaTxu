package com.asuna.textutils.controller;

import com.asuna.textutils.entity.TemplateResultBase;
import com.asuna.textutils.utils.security.RSAUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:80", maxAge = 7200)
public class SecurityController {

    @RequestMapping(value = "/key", method = RequestMethod.GET)
     public Object getRSAPublicKey(){
         TemplateResultBase<String> result = new TemplateResultBase<>();
         try {
             String publicKey = RSAUtils.getPublicKey();
             result.setStatus(200, publicKey, "请求公钥成功");
         } catch (IOException | NoSuchAlgorithmException e) {
             e.printStackTrace();
             result.setStatus(500, null, "公钥请求错误");
         }
         return result;
     }
}
