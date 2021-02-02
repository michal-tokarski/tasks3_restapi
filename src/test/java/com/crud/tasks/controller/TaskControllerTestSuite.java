package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    /*
    @Test
    public void test1_getTasks() {

        // Given
        List<TaskDto> tasksDto = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

    }
    */

    /*
    @Test
    public void test2_getTask(Long taskId) {

    }
    */

    /*
    @Test
    public void test3_deleteTask(Long taskId) {

    }
    */

    /*
    @Test
    public void test4_updateTask(TaskDto taskDto) {

    }
    */

    /*
    @Test
    public void test5_createTask(TaskDto taskDto) {

    }
    */

}
