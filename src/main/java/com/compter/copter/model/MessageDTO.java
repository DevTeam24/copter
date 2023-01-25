package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class MessageDTO {

    private Long messageId;

    @NotNull
    private Integer senderId;

    @NotNull
    private Integer receiverId;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long userMessage;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(final Long messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(final Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(final Integer receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public Long getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(final Long userMessage) {
        this.userMessage = userMessage;
    }

}
