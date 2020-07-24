package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    // Retrieve all Tasks (database)
    @RequestMapping(method = RequestMethod.GET, value = "getAllTasks")
    public List<TaskDto> getAllTasks() {

        return taskMapper.mapTaskDtoList(dbService.getAllTasks());

    }

    // Retrieve Task (database) - v2
    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {

        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));

    }

    // Delete Task (database) - v2
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException{

        if (dbService.getTaskById(taskId).isPresent())
            dbService.deleteTaskById(taskId);
        else
            throw new TaskNotFoundException();

    }

    // Update Task (database)
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {

        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));

    }

    // Create Task (database)
    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {

        dbService.saveTask(taskMapper.mapToTask(taskDto));

    }

}
