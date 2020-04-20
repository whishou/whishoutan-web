package com.whishoutan.main.dao;


import com.whishoutan.main.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    //select * from task
    @Select("select * from task")
    List<Task>  findAll();
}
