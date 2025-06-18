package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

//    private final User user = userServiceImpl.getCurrentUser();

    private User user;

    private User getUser(){
        return userServiceImpl.getCurrentUser();
    }

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task){
        task.setUserId(getUser().getId());
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getTasks(){
        Vector<Task> filteredTasks = new Vector<Task>();
        for (Task task : taskRepository.findAll()){
            if (task.getUserId() == getUser().getId()) filteredTasks.addElement(task);
//            System.out.println(task.getDate());
//            System.out.println(task.getDescription());
//            System.out.println();
        }
        return filteredTasks;
    }
/*
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id){
        Task task = null;
        if (taskRepository.existsById(id)) task = taskRepository.findById(id).orElse(null);
        assert task != null;
        System.out.println(task.getDate());
        System.out.println(task.getDescription());
        System.out.println();
        return task;
    }*/

    @PutMapping("/tasks/{id}")
    public void update(@PathVariable Long id, @RequestBody Task task){
        if (task.getUserId() != getUser().getId()) return;
        task.setId(id);
        taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        if (!taskRepository.existsById(id)) return;
        if (taskRepository.findById(id).orElse(null).getUserId() != getUser().getId()) return;
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    public void markAsDone(@PathVariable Long id, @RequestBody Task task){
        if (task.getUserId() != getUser().getId()) return;
        if (task.isDone()) taskRepository.markAsDone(id);
    }
    @PatchMapping("/tasks/{id}:mark-as-done")
    public void markAsDone(@PathVariable Long id){
        if (!taskRepository.existsById(id)) return;
        if (taskRepository.findById(id).orElse(null).getUserId() != getUser().getId()) return;
        taskRepository.markAsDone(id);
    }

}
