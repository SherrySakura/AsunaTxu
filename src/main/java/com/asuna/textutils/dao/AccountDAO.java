package com.asuna.textutils.dao;

import com.asuna.textutils.entity.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountDAO {

    @Select("select id, name, password from user where name=#{name}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "account"),
            @Result(column = "password", property = "password"),
    }
    )
    Account getAccountByName(String name);

    @Select("select id, name, gender, mail, phone, balance, level, exp from user where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "account"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "mail", property = "mail"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "balance", property = "balance"),
            @Result(column = "level", property = "level"),
            @Result(column = "exp", property = "exp"),
    })
    Account getCurrentAccountById(int id);

    @Insert("insert into user(`name`,`password`,`mail`,`phone`,`gender`) values('${account}', '${password}', '${mail}', '${phone}', ${gender})")
    void insertAccount(Account account);

    @Update("update user set level=#{ac.level}, exp=#{ac.exp} where id=#{uuid}")
    void updateAccount(@Param("ac") Account account, @Param("uuid") int uuid);
}
