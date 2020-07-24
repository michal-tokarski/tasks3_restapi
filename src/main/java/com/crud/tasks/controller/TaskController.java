package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    // Retrieve all Tasks (simple)
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {

        return new ArrayList<>();

    }

    // Retrieve all Tasks (database)
    @RequestMapping(method = RequestMethod.GET, value = "getTasksFromDb")
    public List<TaskDto> getTasksFromDb() {

        return taskMapper.mapTaskDtoList(dbService.getAllTasks());

    }

    // Retrieve single Task (simple)
    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(long taskId) {

        return new TaskDto(1L, "test circle", "test_content");

    }

    // Retrieve single Task (database) - v1
    @RequestMapping(method = RequestMethod.GET, value = "getTaskFromDb_v1")
    public TaskDto getTaskFromDb_v1(long taskId) {

        return taskMapper.mapToTaskDto (dbService.getTaskById_v1(taskId));

    }

    // Retrieve single Task (database) - v2
    @RequestMapping(method = RequestMethod.GET, value = "getTaskFromDb_v2")
    public TaskDto getTaskFromDb_v2(@RequestParam Long taskId) throws TaskNotFoundException {

        return taskMapper.mapToTaskDto(dbService.getTaskById_v2(taskId).orElseThrow(TaskNotFoundException::new));

    }

    // Delete single Task (simple)
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(long taskId) {
    }

    // Delete single Task (database)
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTaskFromDb")
    public void deleteTaskFromDb(@RequestParam Long taskId) throws TaskNotFoundException{

        dbService.deleteTaskById(taskId);

    }

    // Update Task (simple)
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask (TaskDto taskDto) {

        return new TaskDto(1L, "Edited test circle", "Test content");

    }

    // Update Task (database)
    @RequestMapping(method = RequestMethod.PUT, value = "updateTaskInDb")
    public TaskDto updateTaskInDb(@RequestBody TaskDto taskDto) {

        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));

    }

    // Create Task (simple)
    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask (TaskDto taskDto) {

    }

    // Create Task (database)
    @RequestMapping(method = RequestMethod.POST, value = "createTaskInDb", consumes = APPLICATION_JSON_VALUE)
    public void createTaskInDb(@RequestBody TaskDto taskDto) {

        dbService.saveTask(taskMapper.mapToTask(taskDto));

    }

}
