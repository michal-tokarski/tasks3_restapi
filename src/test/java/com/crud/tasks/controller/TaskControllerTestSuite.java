package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    public Task task1 = new Task (1L,"task1","content1");
    public Task task2 = new Task (2L,"task2","content2");
    public Task task3 = new Task (3L,"task3","content3");

    public TaskDto taskDto1 = new TaskDto (1L,"task1","content1");
    public TaskDto taskDto2 = new TaskDto (2L,"task2","content2");
    public TaskDto taskDto3 = new TaskDto (3L,"task3","content3");

    public Gson gson = new Gson();


    @Test
    // tested method : public List<TaskDto> getTasks()
    public void test1a_getTasks_Empty() throws Exception {

        // Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> tasksDto = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapTaskDtoList(tasks)).thenReturn(tasksDto);

        // When
        mockMvc
            //.perform(get("/v1/task/getTasks")      // --- previous version ---
            .perform(get("/v1/tasks/")     // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
            )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tasksDto.size())))
        ;

    }

    @Test
    // tested method : public List<TaskDto> getTasks()
    public void test1b_getTasks_NotEmpty() throws Exception {

        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1); tasks.add(task2); tasks.add(task3);
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(taskDto1); tasksDto.add(taskDto2); tasksDto.add(taskDto3);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapTaskDtoList(tasks)).thenReturn(tasksDto);

        // When
        mockMvc
            //.perform(get("/v1/task/getTasks")      // --- previous version ---
            .perform(get("/v1/tasks/")     // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
            )
                // Then #1
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tasksDto.size())))
                // Then #2
                .andExpect(jsonPath("$[0].id",      is((tasks.get(0).getId()).intValue())))
                .andExpect(jsonPath("$[0].title",   is(tasks.get(0).getTitle())))
                .andExpect(jsonPath("$[0].content", is(tasks.get(0).getContent())))
                .andExpect(jsonPath("$[1].id",      is((tasks.get(1).getId()).intValue())))
                .andExpect(jsonPath("$[1].title",   is(tasks.get(1).getTitle())))
                .andExpect(jsonPath("$[1].content", is(tasks.get(1).getContent())))
                .andExpect(jsonPath("$[2].id",      is((tasks.get(2).getId()).intValue())))
                .andExpect(jsonPath("$[2].title",   is(tasks.get(2).getTitle())))
                .andExpect(jsonPath("$[2].content", is(tasks.get(2).getContent())))
        ;

    }


    @Test
    // tested method : public TaskDto getTask (@RequestParam Long taskId)
    public void test2_getTask() throws Exception {

        // Given
        when(dbService.getTaskById(task1.getId())).thenReturn(Optional.of(task1));
        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto1);

        String jsonContent = gson.toJson(task1);

        // When
        mockMvc
            //.perform(get("/v1/task/getTask")        // --- previous version ---
            .perform(get("/v1/tasks/1")     // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", String.valueOf(task1.getId()))
                .content(jsonContent)
            )
                // Then #1
                .andExpect(status().isOk())
                // Then #2
                .andExpect(jsonPath("$.id", is(task1.getId().intValue())))
                .andExpect(jsonPath("$.title", is(task1.getTitle())))
                .andExpect(jsonPath("$.content", is(task1.getContent())))
        ;

    }

    @Test
    // tested method : public void deleteTask (@RequestParam Long taskId)
    public void test3_deleteTask() throws Exception {

        // Given

        // When
        mockMvc
            //.perform(delete("/v1/task/deleteTask")      // --- previous version ---
            .perform(delete("/v1/tasks/1")        // --- endpoint refactoring ---
                .param("taskId", String.valueOf(task1.getId()))
            )
                // Then
                .andExpect(status().isOk())
        ;

        verify(dbService, times(1)).deleteTaskById(task1.getId());

    }

    @Test
    // tested method : public TaskDto updateTask (@RequestBody TaskDto taskDto)
    public void test4_updateTask() throws Exception {

        // Given
        when(dbService.saveTask(any())).thenReturn(task1);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto1);
        when(taskMapper.mapToTask(any())).thenReturn(task1);

        String jsonContent = gson.toJson(task1);

        // When
        mockMvc
            .perform(
                // put("/v1/task/updateTask")      // --- previous version ---
                put("/v1/tasks")         // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
            )
                // Then #1
                .andExpect(status().isOk())
                // Then #2
                .andExpect(jsonPath("$.id", is(taskDto1.getId().intValue())))
                .andExpect(jsonPath("$.title", is(taskDto1.getTitle())))
                .andExpect(jsonPath("$.content", is(taskDto1.getContent())))
        ;

    }

    @Test
    // tested method : public void createTask (@RequestBody TaskDto taskDto)
    public void test5_createTask() throws Exception {

        // Given
        when(dbService.saveTask(any())).thenReturn(task1);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto1);
        when(taskMapper.mapToTask(any())).thenReturn(task1);

        String jsonContent = gson.toJson(task1);

        // When
        mockMvc
            .perform(
                // post("/v1/task/createTask")        // --- previous version ---
                post("/v1/tasks")           // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
            )
                // Then
                .andExpect(status().isOk())
        ;

    }

}
