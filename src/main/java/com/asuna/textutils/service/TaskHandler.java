package com.asuna.textutils.service;

import com.asuna.textutils.dao.TaskDAO;
import com.asuna.textutils.entity.Task;
import com.asuna.textutils.utils.TaskStatues;
import com.asuna.textutils.utils.TaskType;
import com.asuna.textutils.utils.TimeUtils;
import com.asuna.textutils.utils.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskHandler {

    private final int price = 2;

    @Autowired
    private TaskDAO taskDAO;



    public void insertNewTask(int uuid, Task task){
        try {
            taskDAO.insertTask(task, uuid);
        }catch (Exception e){
            task.setStatues(TaskStatues.ERROR);
        }

    }

    public Task createBlankHandlerTask(int len, String ori, String handled){
        String time = TimeUtils.getCurrentTimeCN();
        String taskStatues = TaskStatues.HANDLED;
        if (handled == null){
            taskStatues = TaskStatues.ERROR;
        }
        long taskUid = Long.parseLong(UuidGenerator.getTaskUuid());
        return new Task(String.valueOf(taskUid), TaskType.BLANK_ENTER, time, len, 0, taskStatues, ori, handled);
    }

    public Task createTranslateHandlerTask(int len, String ori, String handled){
        String time = TimeUtils.getCurrentTimeCN();
        String taskStatues = TaskStatues.HANDLED;
        if (handled == null){
            taskStatues = TaskStatues.ERROR;
        }
        int fee = len * price;
        long taskUid = Long.parseLong(UuidGenerator.getTaskUuid());
        return new Task(String.valueOf(taskUid), TaskType.TRANSLATION, time, len, fee, taskStatues, ori, handled);
    }

    public List<Task> getAllTaskLimit(int uuid){
        return taskDAO.getAllTaskByIdLimit(uuid);
    }

    public List<Task> getAllTask(int uuid){
        return taskDAO.getAllTaskById(uuid);
    }

    public Task getTaskByTaskUid(int uuid, long taskUid){
        return taskDAO.getTaskByTaskUid(uuid, taskUid);
    }

    public int getCountByType(int uuid, String type){
        return taskDAO.getCountByType(uuid, type);
    }
}
