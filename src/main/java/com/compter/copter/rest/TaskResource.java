package com.compter.copter.rest;

import com.compter.copter.model.TaskDTO;
import com.compter.copter.service.TaskService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskResource {

    private final TaskService taskService;

    public TaskResource(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable final Long taskId) {
        return ResponseEntity.ok(taskService.get(taskId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTask(@RequestBody @Valid final TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.create(taskDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable final Long taskId,
            @RequestBody @Valid final TaskDTO taskDTO) {
        taskService.update(taskId, taskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTask(@PathVariable final Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

}
