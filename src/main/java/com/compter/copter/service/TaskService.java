package com.compter.copter.service;

import com.compter.copter.domain.Task;
import com.compter.copter.domain.User;
import com.compter.copter.model.TaskDTO;
import com.compter.copter.repos.TaskRepository;
import com.compter.copter.repos.UserRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(final TaskRepository taskRepository, final UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> findAll() {
        final List<Task> tasks = taskRepository.findAll(Sort.by("taskId"));
        return tasks.stream()
                .map((task) -> mapToDTO(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    public TaskDTO get(final Long taskId) {
        return taskRepository.findById(taskId)
                .map(task -> mapToDTO(task, new TaskDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final TaskDTO taskDTO) {
        final Task task = new Task();
        mapToEntity(taskDTO, task);
        return taskRepository.save(task).getTaskId();
    }

    public void update(final Long taskId, final TaskDTO taskDTO) {
        final Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(taskDTO, task);
        taskRepository.save(task);
    }

    public void delete(final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private TaskDTO mapToDTO(final Task task, final TaskDTO taskDTO) {
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTaskname(task.getTaskname());
        taskDTO.setUserTask(task.getUserTask() == null ? null : task.getUserTask().getUserId());
        return taskDTO;
    }

    private Task mapToEntity(final TaskDTO taskDTO, final Task task) {
        task.setTaskname(taskDTO.getTaskname());
        final User userTask = taskDTO.getUserTask() == null ? null : userRepository.findById(taskDTO.getUserTask())
                .orElseThrow(() -> new NotFoundException("userTask not found"));
        task.setUserTask(userTask);
        return task;
    }

}
