package com.asuna.textutils.service;

import com.asuna.textutils.dao.MissionDAO;
import com.asuna.textutils.entity.Mission;
import com.asuna.textutils.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class MissionHandler {

    private Mission globalMission;
    private int uuid;

    @Autowired
    private MissionDAO missionDAO;

    public void setUuid(int uuid){
        this.uuid = uuid;
    }

    public void getCurrentMission(){
        globalMission = missionDAO.getAllMissionById(uuid);
    }

    public Mission getTodayMission(){
        updateMission();
        return globalMission;
    }

    public void updateMission(){
        if (globalMission == null){
            getCurrentMission();
        }
        if (checkDate()){
            clearToday();

        }
    }

    public void increaseCommit(int count){
        updateMission();
        globalMission.increaseCommit(count);
    }

    public void increaseTrans(int count){
        updateMission();
        globalMission.increaseTrans(count);
    }

    public void createNewMission(int uuid){
        TimeUtils utils = new TimeUtils(TimeUtils.standerdEN);
        String now = utils.convertNowToString();
        String[] nows = now.split(" ");
        missionDAO.insertEmptyMission(uuid, nows[0], nows[1]);
    }

    public void save(){
        globalMission.now();
        missionDAO.updateMission(uuid, globalMission);
    }

    public void clearToday(){
        globalMission.clearToday();
        globalMission.now();
        missionDAO.insertNewDayMission(globalMission);
    }

    private boolean checkDate(){
        Date now = new Date();
        TimeUtils timeUtils = new TimeUtils(TimeUtils.DATE);
        try {
            Date his = timeUtils.convertStringToDate(globalMission.getDate());
            if (now.getDate() - his.getDate() >= 1){
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
