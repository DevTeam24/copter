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
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

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
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_task_id", nullable = false)
    private Task commentTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_comment_id", nullable = false)
    private User userComment;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(final Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public Task getCommentTask() {
        return commentTask;
    }

    public void setCommentTask(final Task commentTask) {
        this.commentTask = commentTask;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(final User userComment) {
        this.userComment = userComment;
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
