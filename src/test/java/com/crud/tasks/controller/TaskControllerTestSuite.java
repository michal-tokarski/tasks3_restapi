package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static java.util.Optional.ofNullable;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    // tested method : public List<TaskDto> getTasks()
    public void test1a_getTasks_Empty() throws Exception {

        // Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> tasksDto = taskMapper.mapTaskDtoList(tasks);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapTaskDtoList(anyList())).thenReturn(tasksDto);

        // When
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))

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
        Task task1 = new Task (1L,"task1","content1");
        Task task2 = new Task (2L,"task2","content2");
        Task task3 = new Task (3L,"task3","content3");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        List<TaskDto> tasksDto = taskMapper.mapTaskDtoList(tasks);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapTaskDtoList(anyList())).thenReturn(tasksDto);

        // When
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))

                // Then # 1
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tasksDto.size())))

                // Then # 2
                .andExpect(jsonPath("$[0].id",      is((tasksDto.get(0).getId()).intValue())))
                .andExpect(jsonPath("$[0].title",   is(tasksDto.get(0).getTitle())))
                .andExpect(jsonPath("$[0].content", is(tasksDto.get(0).getContent())))
                .andExpect(jsonPath("$[1].id",      is((tasksDto.get(1).getId()).intValue())))
                .andExpect(jsonPath("$[1].title",   is(tasksDto.get(1).getTitle())))
                .andExpect(jsonPath("$[1].content", is(tasksDto.get(1).getContent())))
                .andExpect(jsonPath("$[2].id",      is((tasksDto.get(2).getId()).intValue())))
                .andExpect(jsonPath("$[2].title",   is(tasksDto.get(2).getTitle())))
                .andExpect(jsonPath("$[2].content", is(tasksDto.get(2).getContent())))

        ;

    }

    @Test
    // tested method : public TaskDto getTask (@RequestParam Long taskId)
    public void test2_getTask() throws Exception {

        // Given
        Task task = new Task (1L,"task1","content1");
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        when(dbService.getTaskById(anyLong())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        mockMvc.perform(get("/v1/task/getTask")
                //.param("taskId", String.valueOf(taskDto.getId()))
                .param("taskId", "1") // temporary
                .contentType(MediaType.APPLICATION_JSON))

                // Then # 1
                .andExpect(status().isOk())

                // Then # 2
                .andExpect(jsonPath("$[0].id",      is((taskDto.getId()).intValue())))
                .andExpect(jsonPath("$[0].title",   is(taskDto.getTitle())))
                .andExpect(jsonPath("$[0].content", is(taskDto.getContent())))

        ;

    }


    /*
    @Test
    // tested method : public void deleteTask (@RequestParam Long taskId)
    public void test3_deleteTask() {

    }

    @Test
    // tested method : public TaskDto updateTask (@RequestBody TaskDto taskDto)
    public void test4_updateTask() {



    }

    @Test
    // tested method : public void createTask (@RequestBody TaskDto taskDto)
    public void test5_createTask() {


    }

     */

}
