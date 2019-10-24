package com.asuna.textutils.dao;

import com.asuna.textutils.entity.Mission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MissionDAO {

    @Select("select id, userid, date, time, commit_count, commit_today, commit_char_count, commit_char_today, trans_char_count, trans_char_today from mission where userid=#{uuid} order by id desc limit 1")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "userid", property = "userId"),
            @Result(column = "date", property = "date"),
            @Result(column = "time", property = "time"),
            @Result(column = "commit_count", property = "commitCount"),
            @Result(column = "commit_today", property = "commitToday"),
            @Result(column = "commit_char_count", property = "commitCharCount"),
            @Result(column = "commit_char_today", property = "commitCharToday"),
            @Result(column = "trans_char_count", property = "transCharCount"),
            @Result(column = "trans_char_today", property = "transCharToday"),
    })
    Mission getAllMissionById(int uuid);

    @Update("update mission set date=#{mission.date}, time=#{mission.time}, commit_count=#{mission.commitCount}, commit_today=#{mission.commitToday}, commit_char_count=#{mission.commitCharCount}, commit_char_today=#{mission.commitCharToday}, trans_char_count=#{mission.transCharCount}, trans_char_today=#{mission.transCharToday} where userid=#{uuid}")
    void updateMission(@Param("uuid") int uuid, @Param("mission") Mission mission);

    @Insert("insert into mission(`userid`, `date`, `time`) values(#{uuid}, #{date}, #{time})")
    void insertEmptyMission(@Param("uuid") int uuid, @Param("date") String date, @Param("time") String time);

    @Insert("insert into mission(`userid`, `date`, `time`, `commit_count`, `commit_today`, `commit_char_count`, `commit_char_today`, `trans_char_count`, `trans_char_today`) " +
            "values(#{mission.userId}, #{mission.date}, #{mission.time}, #{mission.commitCount}, #{mission.commitToday}, #{mission.commitCharCount}, #{mission.commitCharToday}, #{mission.transCharCount}, #{mission.transCharToday})")
    void insertNewDayMission(@Param("mission") Mission mission);
}
