package com.asuna.textutils.dao;

import com.asuna.textutils.entity.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskDAO {

    @Select("select id, task_type, time, length, fee, statues, ori, handled from task where userid=#{uuid} order by id desc limit 5")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "task_type", property = "taskType"),
            @Result(column = "time", property = "time"),
            @Result(column = "length", property = "length"),
            @Result(column = "fee", property = "fee"),
            @Result(column = "statues", property = "statues"),
            @Result(column = "ori", property = "ori"),
            @Result(column = "handled", property = "handled")
    })
    List<Task> getAllTaskByIdLimit(int uuid);

    @Select("select id, task_type, time, length, fee, statues, ori, handled from task where userid=#{uuid} order by id desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "task_type", property = "taskType"),
            @Result(column = "time", property = "time"),
            @Result(column = "length", property = "length"),
            @Result(column = "fee", property = "fee"),
            @Result(column = "statues", property = "statues"),
            @Result(column = "ori", property = "ori"),
            @Result(column = "handled", property = "handled")
    })
    List<Task> getAllTaskById(int uuid);

    @Select("select id, task_type, time, length, fee, statues, ori, handled from task where userid=#{uuid} and id=#{taskUid}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "task_type", property = "taskType"),
            @Result(column = "time", property = "time"),
            @Result(column = "length", property = "length"),
            @Result(column = "fee", property = "fee"),
            @Result(column = "statues", property = "statues"),
            @Result(column = "ori", property = "ori"),
            @Result(column = "handled", property = "handled")
    })
    Task getTaskByTaskUid(@Param("uuid") int uuid, @Param("taskUid") long taskUid);

    @Update("update task set task_type=#{task.taskType}, time=#{task.time}, length=#{task.length}, fee=#{task.fee}, statues=#{task.statues}, ori=#{task.ori}, handled=#{task.handled} where userid = #{uuid}")
    void updateTaskById(@Param("uuid") int uuid, @Param("task") Task task);

    @Insert("insert into task(`id`, `task_type`, `time`, `length`, `fee`, `statues`, `ori`, `handled`, `userid`) values(#{task.id}, #{task.taskType}, #{task.time}, #{task.length}, #{task.fee}, #{task.statues}, #{task.ori}, #{task.handled}, #{uuid})")
    void insertTask(@Param("task") Task task, @Param("uuid") int uuid);

    @Select("select count(*) as res from task where task_type=#{type} and userid=#{uuid}")
    @ResultType(value = int.class)
    int getCountByType(@Param("uuid") int uuid, @Param("type") String type);
}
