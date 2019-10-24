package com.asuna.textutils.entity;

import com.asuna.textutils.utils.TimeUtils;

/**
 * 每日更新的数据实体，记录用户提交的次数统计
 */
public class Mission {
    private int id;
    private int userId;
    private String date;
    private String time;
    private int commitCount;
    private int commitToday;
    private int commitCharCount;
    private int commitCharToday;
    private int transCharCount;
    private int transCharToday;

    public Mission() {
    }

    public Mission(int id, int userId, String date, String time, int commitCount, int commitToday, int commitCharCount, int commitCharToday, int transCharCount, int transCharToday) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.commitCount = commitCount;
        this.commitToday = commitToday;
        this.commitCharCount = commitCharCount;
        this.commitCharToday = commitCharToday;
        this.transCharCount = transCharCount;
        this.transCharToday = transCharToday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    public int getCommitToday() {
        return commitToday;
    }

    public void setCommitToday(int commitToday) {
        this.commitToday = commitToday;
    }

    public int getCommitCharCount() {
        return commitCharCount;
    }

    public void setCommitCharCount(int commitCharCount) {
        this.commitCharCount = commitCharCount;
    }

    public int getCommitCharToday() {
        return commitCharToday;
    }

    public void setCommitCharToday(int commitCharToday) {
        this.commitCharToday = commitCharToday;
    }

    public int getTransCharCount() {
        return transCharCount;
    }

    public void setTransCharCount(int transCharCount) {
        this.transCharCount = transCharCount;
    }

    public int getTransCharToday() {
        return transCharToday;
    }

    public void setTransCharToday(int transCharToday) {
        this.transCharToday = transCharToday;
    }

    public void increaseCommit(int count){
        this.commitCharToday += count;
        this.commitCharCount += count;
        this.commitToday++;
        this.commitCount++;
    }

    public void increaseTrans(int count){
        this.transCharToday += count;
        this.transCharCount += count;
        this.commitToday++;
        this.commitCount++;
    }

    public void clearToday(){
        this.commitToday = 0;
        this.commitCharToday = 0;
        this.transCharToday = 0;
    }

    public void now(){
        String now = TimeUtils.getCurrentTimeEN();
        String[] nows = now.split(" ");
        this.date = nows[0];
        this.time = nows[1];
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", commitCount=" + commitCount +
                ", commitToday=" + commitToday +
                ", commitCharCount=" + commitCharCount +
                ", commitCharToday=" + commitCharToday +
                ", transCharCount=" + transCharCount +
                ", transCharToday=" + transCharToday +
                '}';
    }
}
