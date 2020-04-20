package com.whishoutan.main.controller;


import com.whishoutan.main.dao.TaskMapper;
import com.whishoutan.main.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskMapper taskMapper;

    //select
    //http://localhost:8080/findAll
    @RequestMapping("findAll")
    public String findAll()
    {
        List<Task> taskList = taskMapper.findAll();

        for (Task task : taskList)
        {
            System.out.println(task.getId()+"|"+task.getTarget()+"|"+task.isIf_finish());
        }
        return "index";
    }

}
