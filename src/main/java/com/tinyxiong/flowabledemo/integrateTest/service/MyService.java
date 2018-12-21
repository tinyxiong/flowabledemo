package com.tinyxiong.flowabledemo.integrateTest.service;

import com.tinyxiong.flowabledemo.demo.domain.TestUser;
import com.tinyxiong.flowabledemo.demo.mapper.TestUserMapper;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyService {

    private RuntimeService runtimeService;
    private TaskService taskService;
    private TestUserMapper testUserMapper;

    public void startProcess(Integer assigneeId) {
        TestUser testUser = testUserMapper.selectOne(assigneeId);

        Map<String, Object> variables = new HashMap<>();
        variables.put("person", testUser);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Transactional
    public void createDemoUsers() {
        if (testUserMapper.selectTestUser().size() == 0) {
            testUserMapper.insertTestUser(new TestUser("test1", "123"));
            testUserMapper.insertTestUser(new TestUser("test2", "123"));
        }
    }

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setTestUserMapper(TestUserMapper testUserMapper) {
        this.testUserMapper = testUserMapper;
    }
}
