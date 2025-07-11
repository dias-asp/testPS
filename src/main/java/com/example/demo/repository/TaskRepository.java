package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    @Modifying
    @Query("UPDATE Task t SET t.done = true where t.id = :id")
    public void markAsDone(@Param("id") Long id);

}
