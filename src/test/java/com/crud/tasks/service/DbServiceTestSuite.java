package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void test1_getAllTasks() {

        // Given
        Task task1 = new Task(1L, "Task 1", "Repair the stairs");
        Task task2 = new Task(2L, "Task 2", "Fix the pipeline");
        Task task3 = new Task(3L, "Task 3", "Seal the oil leakage");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1); taskList.add(task2); taskList.add(task3);
        when(taskRepository.findAll()).thenReturn(taskList);

        // When
        List<Task> resultList = dbService.getAllTasks();

        // Then
        assertEquals(3, resultList.size());

    }

    @Test
    public void test2_getTaskById() {

        // Given
        Task task2 = new Task(2L, "Task 2", "Fix the pipeline");
        when(taskRepository.findById(task2.getId())).thenReturn(Optional.of(task2));

        // When
        Optional<Task> resultTask = dbService.getTaskById(task2.getId());

        // Then
        assertEquals(new Long(2), resultTask.get().getId());
        assertEquals("Task 2", resultTask.get().getTitle());
        assertEquals("Fix the pipeline", resultTask.get().getContent());

    }

    @Test
    public void test3_saveTask() {

        // Given
        Task task1 = new Task(1L, "Task 1", "Repair the stairs");
        when(taskRepository.save(task1)).thenReturn(task1);

        // When
        Task resultTask = dbService.saveTask(task1);

        // Then
        assertEquals(new Long(1), resultTask.getId());
        assertEquals("Task 1", resultTask.getTitle());
        assertEquals("Repair the stairs", resultTask.getContent());

    }

    @Test
    public void test4_deleteTaskById() {

        // Given
        Task task3 = new Task(3L, "Task 3", "Seal the oil leakage");
        dbService.saveTask(task3);

        // When
        dbService.deleteTaskById(task3.getId());

        // Then
        verify(taskRepository, times(1)).deleteById(task3.getId());

    }

}
