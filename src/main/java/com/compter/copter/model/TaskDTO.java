package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class TaskDTO {

    private Long taskId;

    @NotNull
    @Size(max = 255)
    private String taskname;

    @NotNull
    private Long userTask;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(final Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(final String taskname) {
        this.taskname = taskname;
    }

    public Long getUserTask() {
        return userTask;
    }

    public void setUserTask(final Long userTask) {
        this.userTask = userTask;
    }

}
