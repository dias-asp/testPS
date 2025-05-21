package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getTasks(){
        for (Task task : taskRepository.findAll()){
            System.out.println(task.getDate());
            System.out.println(task.getDescription());
            System.out.println();
        }
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id){
        Task task = null;
        if (taskRepository.existsById(id)) task = taskRepository.findById(id).orElse(null);
        assert task != null;
        System.out.println(task.getDate());
        System.out.println(task.getDescription());
        System.out.println();
        return task;
    }

    @PutMapping("/tasks/{id}")
    public void update(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    public void markAsDone(@PathVariable Long id, @RequestBody Task task){
        if (task.isDone()) taskRepository.markAsDone(id);
    }
    @PatchMapping("/tasks/{id}:mark-as-done")
    public void markAsDone(@PathVariable Long id){
        taskRepository.markAsDone(id);
    }

}
