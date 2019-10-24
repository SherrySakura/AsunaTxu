package com.asuna.textutils.service;

import com.asuna.textutils.dao.AccountDAO;
import com.asuna.textutils.entity.Account;
import com.asuna.textutils.entity.Level;
import com.asuna.textutils.entity.TemplateResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHandler {

    private Account gloableAccount;
    private int uuid;

    @Autowired
    private AccountDAO dao;

    @Autowired
    private MissionHandler missionHandler;

    @Autowired
    private LevelHandler levelHandler;

    public void setUuid(int uuid){
        this.uuid = uuid;
    }

    private void getActiveAccount(int id){
        this.uuid = id;
        gloableAccount = dao.getCurrentAccountById(uuid);
    }

    public TemplateResultBase<Account> loginHandler(String name, String password){
        TemplateResultBase<Account> result = new TemplateResultBase<>();
        Account account = dao.getAccountByName(name);
        System.out.println(account);
        if (account == null){
            result.setStatus(105, null, "用户不存在");
        }else if (!password.equals(account.getPassword())){
            result.setStatus(106, null, "密码错误");
        }else {
            result.setStatus(200, account, "欢迎");
        }
        return result;
    }

    public TemplateResultBase<Account> registHandler(Account account){
        TemplateResultBase<Account> result = new TemplateResultBase<>();
        try {
            Account isExsist = dao.getAccountByName(account.getAccount());
            if (isExsist == null){
                dao.insertAccount(account);
            }else {
                result.setStatus(407, null, "账号已存在，请直接登录");
                return result;
            }
        }catch (Exception e){
            result.setStatus(501, null, "服务端繁忙");
            return result;
        }
        Account newAccount = dao.getAccountByName(account.getAccount());
        result.setStatus(200, newAccount, "成功注册");
        missionHandler.createNewMission(newAccount.getId());
        return result;
    }

    public void updateExp(int expGain){
        getActiveAccount(uuid);
        gloableAccount.setExp(gloableAccount.getExp() + expGain);
        int currentExp = gloableAccount.getExp();
        TemplateResultBase<Level> currentLevelResult = levelHandler.getLevelByLv(gloableAccount.getLevel());
        int maxExp = currentLevelResult.getData().getExp();
        int up = 0;
        while (currentExp >= maxExp){
            up++;
            currentExp -= maxExp;
            TemplateResultBase<Level> nextLevelResult = levelHandler.getLevelByLv(gloableAccount.getLevel() + 1);
            maxExp = nextLevelResult.getData().getExp();
        }
        gloableAccount.levelUp(up, currentExp);
        save();
    }

    private void save(){
        dao.updateAccount(gloableAccount, uuid);
    }


}
