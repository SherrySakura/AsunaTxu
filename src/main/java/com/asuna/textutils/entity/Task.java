package com.asuna.textutils.entity;

public class Task {
    private String id;
    private String taskType;
    private String time;
    private int length;
    private int fee;
    private String statues;
    private String ori;
    private String handled;

    public Task() {
    }

    public Task(String id, String taskType, String time, int length, int fee, String statues, String ori, String handled) {
        this.id = id;
        this.taskType = taskType;
        this.time = time;
        this.length = length;
        this.fee = fee;
        this.statues = statues;
        this.ori = ori;
        this.handled = handled;
    }

    public Task(String id, String taskType, String time, int length, int fee, String statues, String ori) {
        this.id = id;
        this.taskType = taskType;
        this.time = time;
        this.length = length;
        this.fee = fee;
        this.statues = statues;
        this.ori = ori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getOri() {
        return ori;
    }

    public void setOri(String ori) {
        this.ori = ori;
    }

    public String getHandled() {
        return handled;
    }

    public void setHandled(String handled) {
        this.handled = handled;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskType='" + taskType + '\'' +
                ", time='" + time + '\'' +
                ", length=" + length +
                ", fee=" + fee +
                ", statues='" + statues + '\'' +
                ", ori='" + ori + '\'' +
                ", handled='" + handled + '\'' +
                '}';
    }
}
