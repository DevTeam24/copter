package com.compter.copter.repos;

import com.compter.copter.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
