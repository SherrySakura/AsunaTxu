package com.asuna.textutils.controller;

import com.asuna.textutils.entity.*;
import com.asuna.textutils.service.DashHandler;
import com.asuna.textutils.service.LevelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashController {

    @Autowired
    private DashHandler handler;

    @Autowired
    private LevelHandler levelHandler;

    /**
     * 启动我的账户主页时会自动调用这个接口，用于获取当日最新的mission信息
     * @param id 用户uuid
     * @return
     */
    @RequestMapping(value = "/all")
    public Object refreshAll(String id){
        TemplateResultBase<Mission> result = handler.getNewestMissionById(id);
        return result;
    }

    /**
     * 启动我的账户主页时会自动调用这个接口，用于获取当日最新的Account信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/currentAccount")
    public Object getCurrentAccount(String id){
        TemplateResultBase<Account> result = handler.getCurrentAccount(id);
        return result;
    }

    @RequestMapping(value = "/task")
    public Object getAllTaskLimit(Integer uuid){
        TemplateResultBase<List<Task>> result = handler.getCurrentAllTaskLimit(uuid, 5);
        return result;
    }

    @RequestMapping(value = "/detail")
    public Object getAllTask(Integer uuid){
        TemplateTableResult<List<Task>> result = handler.getCurrentAllTaskLimit(uuid, -1);
        return result;
    }

    @RequestMapping(value = "/more")
    public Object getMore(Integer uuid, Long taskUid){
        TemplateResultBase<Task> result = handler.getCertainTask(uuid, taskUid);
        return result;
    }

    @RequestMapping(value = "/rate")
    public Object getRate(Integer uuid){
        TemplateResultBase<RateResult> result = handler.getTypeCount(uuid);
        return result;
    }

    @RequestMapping(value = "/level")
    public Object getLevelInfo(Integer lv){
        return levelHandler.getLevelByLv(lv);
    }

    /**
     * 判断用户登录频次
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/active")
    public Object getActive(Integer uuid){
        return null;
    }
}
