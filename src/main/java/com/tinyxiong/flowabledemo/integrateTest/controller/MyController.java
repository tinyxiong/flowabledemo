package com.tinyxiong.flowabledemo.integrateTest.controller;

import com.tinyxiong.flowabledemo.integrateTest.service.MyService;
import lombok.Data;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class MyController {

    private MyService myService;

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
        myService.startProcess(startProcessRepresentation.getAssigneeId());
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @Data
    static class TaskRepresentation {

        private String id;
        private String name;

        TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    @Data
    private static class StartProcessRepresentation {
        private Integer assigneeId;
    }

    @Autowired
    public void setMyService(MyService myService) {
        this.myService = myService;
    }

}
