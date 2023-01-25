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
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Message {

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
    private Long messageId;

    @Column(nullable = false, unique = true)
    private Integer senderId;

    @Column(nullable = false, unique = true)
    private Integer receiverId;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_message_id", nullable = false)
    private User userMessage;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public User getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(final User userMessage) {
        this.userMessage = userMessage;
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
