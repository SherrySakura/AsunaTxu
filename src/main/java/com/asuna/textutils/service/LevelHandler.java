package com.asuna.textutils.service;

import com.asuna.textutils.dao.LevelDAO;
import com.asuna.textutils.entity.Level;
import com.asuna.textutils.entity.TemplateResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelHandler {

    @Autowired
    private LevelDAO levelDAO;

    public void initLevel(){
        int lv = 1;
        int exp = 1000;
        while (lv <= 100){
            Level level = new Level(lv, exp);
            System.out.println(level);
            levelDAO.insertLevel(level);
            if (lv < 10){
                exp += 500 * lv;
            }else if (lv < 20){
                exp += 1000 * lv;
            }else if (lv < 30){
                exp += 1500 * lv;
            }else if (lv < 40){
                exp += 2000 * lv;
            }else if (lv < 50){
                exp += 3000 * lv;
            }else if (lv < 60){
                exp += 4000 * lv;
            }else if (lv < 70){
                exp += 5000 * lv;
            }else if (lv < 80){
                exp += 7000 * lv;
            }else if (lv < 90){
                exp += 9000 * lv;
            }else {
                exp += 12000 * lv;
            }
            lv++;
        }
    }

    public TemplateResultBase<Level> getLevelByLv(int lv){
        TemplateResultBase<Level> result = new TemplateResultBase<>();
        Level lvNow = levelDAO.getLevelInfoByRank(lv);
        result.setStatus(200, lvNow, "等级已查询");
        return result;
    }
}
