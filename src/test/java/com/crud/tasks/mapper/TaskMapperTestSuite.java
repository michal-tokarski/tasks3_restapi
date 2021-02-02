package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoard;
import jdk.nashorn.internal.objects.Global;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Autowired
    private Task task1, task2;

    @Autowired
    private TaskDto taskDto1, taskDto2;


    /* commented out for the moment
    @Before
    public void init() {

        Task task1 = new Task(1L, "Fix the car", "Clutch in Nissan BRZ is not working");
        Task task2 = new Task(2L, "Paint the walls", "Paint the walls on 2nd floor");
        TaskDto taskDto1 = new TaskDto(1L, "Fix the car", "Clutch in Nissan BRZ is not working");
        TaskDto taskDto2 = new TaskDto(2L, "Paint the walls", "Paint the walls on 2nd floor");

    }
    */

    @Test
    public void test1_mapToTask() {

        // Given
        Task task1 = new Task(1L, "Fix the car", "Clutch in Nissan BRZ is not working");
        TaskDto taskDto1 = new TaskDto(1L, "Fix the car", "Clutch in Nissan BRZ is not working");

        // When
        Task resultTask = taskMapper.mapToTask(taskDto1);

        // Then
        assertEquals(task1.getId(), resultTask.getId());
        assertEquals(task1.getTitle(), resultTask.getTitle());
        assertEquals(task1.getContent(), resultTask.getContent());

    }

    @Test
    public void test2_mapToTaskDto() {

        // Given
        Task task2 = new Task(2L, "Paint the walls", "Paint the walls on 2nd floor");
        TaskDto taskDto2 = new TaskDto(2L, "Paint the walls", "Paint the walls on 2nd floor");

        // When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(task2);

        // Then
        assertEquals(taskDto2.getId(), resultTaskDto.getId());
        assertEquals(taskDto2.getTitle(), resultTaskDto.getTitle());
        assertEquals(taskDto2.getContent(), resultTaskDto.getContent());

    }

    @Test
    public void test3_mapTaskDtoList() {

        // Given
        Task task1 = new Task(1L, "Fix the car", "Clutch in Nissan BRZ is not working");
        Task task2 = new Task(2L, "Paint the walls", "Paint the walls on 2nd floor");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        TaskDto taskDto1 = new TaskDto(1L, "Fix the car", "Clutch in Nissan BRZ is not working");
        TaskDto taskDto2 = new TaskDto(2L, "Paint the walls", "Paint the walls on 2nd floor");
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(taskDto1);
        taskListDto.add(taskDto2);

        // When
        List<TaskDto> resultList = taskMapper.mapTaskDtoList(taskList);

        // Then : Assert #1 :
        for (int count=0 ; count < taskList.size() ; count++) {
            assertEquals (taskList.get(count).getId(), resultList.get(count).getId());
            assertEquals (taskList.get(count).getTitle(), resultList.get(count).getTitle());
            assertEquals (taskList.get(count).getContent(), resultList.get(count).getContent());
        }

        // Then : Assert #2 :
        assertEquals(taskList.size(), resultList.size());

    }

}
