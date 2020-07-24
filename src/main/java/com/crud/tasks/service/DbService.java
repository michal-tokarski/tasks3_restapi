package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById_v1(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(()->new IllegalArgumentException());
    }

    public Optional<Task> getTaskById_v2(final Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task saveTask(final Task task){
        return taskRepository.save(task);
    }

    public void deleteTaskById(final Long taskId) throws TaskNotFoundException{
        if (taskRepository.findById(taskId).isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            throw new TaskNotFoundException();
        }
    }

}
