package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class FileDTO {

    private Long fileId;

    @NotNull
    @Size(max = 255)
    private String type;

    @NotNull
    @Size(max = 255)
    private String data;

    @NotNull
    private Long taskFile;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(final Long fileId) {
        this.fileId = fileId;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public Long getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(final Long taskFile) {
        this.taskFile = taskFile;
    }

}
