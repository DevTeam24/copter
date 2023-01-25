package com.compter.copter.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long taskId;

    @Column(nullable = false)
    private String taskname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_task_id", nullable = false)
    private User userTask;

    @OneToMany(mappedBy = "commentTask")
    private Set<Comment> commentTaskComments;

    @OneToMany(mappedBy = "taskFile")
    private Set<File> taskFileFiles;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public User getUserTask() {
        return userTask;
    }

    public void setUserTask(final User userTask) {
        this.userTask = userTask;
    }

    public Set<Comment> getCommentTaskComments() {
        return commentTaskComments;
    }

    public void setCommentTaskComments(final Set<Comment> commentTaskComments) {
        this.commentTaskComments = commentTaskComments;
    }

    public Set<File> getTaskFileFiles() {
        return taskFileFiles;
    }

    public void setTaskFileFiles(final Set<File> taskFileFiles) {
        this.taskFileFiles = taskFileFiles;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
