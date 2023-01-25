package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class CommentDTO {

    private Long commentId;

    @NotNull
    @Size(max = 255)
    private String comment;

    @NotNull
    private Long commentTask;

    @NotNull
    private Long userComment;

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

    public Long getCommentTask() {
        return commentTask;
    }

    public void setCommentTask(final Long commentTask) {
        this.commentTask = commentTask;
    }

    public Long getUserComment() {
        return userComment;
    }

    public void setUserComment(final Long userComment) {
        this.userComment = userComment;
    }

}
