package com.asuna.textutils.service;

import com.asuna.textutils.dao.AccountDAO;
import com.asuna.textutils.dao.MissionDAO;
import com.asuna.textutils.entity.*;
import com.asuna.textutils.utils.GenUtils;
import com.asuna.textutils.utils.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashHandler {
    @Autowired
    private MissionHandler missionHandler;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TaskHandler taskHandler;

    public TemplateResultBase<Mission> getNewestMissionById(String uuid){
        TemplateResultBase<Mission> result = new TemplateResultBase<>();
        if (!GenUtils.validateID(uuid)){
            result.setStatus(400, null, "请求ID不合法");
            return result;
        }
        int id = Integer.valueOf(uuid);
        missionHandler.setUuid(id);
        Mission mission = missionHandler.getTodayMission();
        if (mission == null){
            result.setStatus(500, null, "查询出错");
        }else{
            result.setStatus(200, mission, "查询成功");
        }
        return result;
    }

    public TemplateResultBase<Account> getCurrentAccount(String id){
        TemplateResultBase<Account> result = new TemplateResultBase<>();
        if (!GenUtils.validateID(id)){
            result.setStatus(400, null, "请求ID不合法");
            return result;
        }
        int uuid = Integer.valueOf(id);
        Account curAccount = accountDAO.getCurrentAccountById(uuid);
        if (curAccount == null){
            result.setStatus(501, null, "查询出错");
        }else{
            result.setStatus(200, curAccount, "查询成功");
        }
        return result;
    }

    public TemplateTableResult<List<Task>> getCurrentAllTaskLimit(Integer uuid, int limit){
        TemplateTableResult<List<Task>> result = new TemplateTableResult<>();
        List<Task> tasks = null;
        if (limit == -1){
            tasks = taskHandler.getAllTask(uuid);
        }else {
            tasks = taskHandler.getAllTaskLimit(uuid);
        }
        if (tasks == null){
            result.setStatus(500, null, "任务历史请求错误", 0);
        }else if (tasks.isEmpty()){
            result.setStatus(200, null, "无历史记录", 0);
        }else {
            System.out.println(tasks);
            result.setStatus(200, tasks, "查询成功", tasks.size());
        }
        return result;
    }

    public TemplateResultBase<Task> getCertainTask(Integer uuid, Long taskUid){
        TemplateResultBase<Task> result = new TemplateResultBase<>();
        Task cerTask = taskHandler.getTaskByTaskUid(uuid, taskUid);
        if (cerTask == null){
            result.setStatus(500, null, "查询错误");
        }else {
            result.setStatus(200, cerTask, "查询成功");
        }
        return result;
    }

    public TemplateResultBase<RateResult> getTypeCount(Integer uuid){
        TemplateResultBase<RateResult> result = new TemplateResultBase<>();
        int blk = taskHandler.getCountByType(uuid, TaskType.BLANK_ENTER);
        int trn = taskHandler.getCountByType(uuid, TaskType.TRANSLATION);
        RateResult rate = new RateResult(blk, trn);
        result.setStatus(200, rate, "查询成功");
        return result;
    }
}
