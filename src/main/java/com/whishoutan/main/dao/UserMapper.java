package com.whishoutan.main.dao;

import com.whishoutan.main.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where userName = #{userName} and password = #{password}")
    User findUserByUserAndPassword(User user);
}
