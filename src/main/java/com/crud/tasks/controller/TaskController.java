package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController

// @RequestMapping("/v1/task")     // --- previous version ---
@RequestMapping("/v1")          // --- endpoint refactoring ---
public class TaskController {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskMapper taskMapper;

    // Retrieve all Tasks (database)
    // @RequestMapping(method = RequestMethod.GET, value = "getTasks")     // --- previous version ---
    @RequestMapping(method = RequestMethod.GET, value = "/tasks")       // --- endpoint refactoring ---
    public List<TaskDto> getTasks() {

        return taskMapper.mapTaskDtoList(dbService.getAllTasks());

    }

    // Retrieve Task (database) - v2
    // @RequestMapping(method = RequestMethod.GET, value = "getTask")                      // --- previous version ---
    // public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {    // --- previous version ---
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")               // --- endpoint refactoring ---
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {       // --- endpoint refactoring ---

        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));

    }

    // Create Task (database)
    // @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)  // --- previous version ---
    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)      // --- endpoint refactoring ---
    public void createTask(@RequestBody TaskDto taskDto) {

        dbService.saveTask(taskMapper.mapToTask(taskDto));

    }

    // Update Task (database)
    // @RequestMapping(method = RequestMethod.PUT, value = "updateTask")        // --- previous version ---
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")            // --- endpoint refactoring ---
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {

        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));

    }

    // Delete Task (database) - v2
    // @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")                 // --- previous version ---
    // public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException{      // --- previous version ---
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")             // --- endpoint refactoring ---
    public void deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {        // --- endpoint refactoring ---

        /*
        if (dbService.getTaskById(taskId).isPresent())
            dbService.deleteTaskById(taskId);
        else
            throw new TaskNotFoundException();
        */

        dbService.deleteTaskById(taskId);

    }


}
