package com.asuna.textutils.dao;


import com.asuna.textutils.entity.Level;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LevelDAO {

    @Insert("insert into level(`level`, `exp`) values(#{lv.level}, #{lv.exp})")
    void insertLevel(@Param("lv") Level lv);

    @Select("select level, exp from level where level=#{lv}")
    @Results({
            @Result(column = "level", property = "level"),
            @Result(column = "exp", property = "exp")
    })
    Level getLevelInfoByRank(int lv);
}
