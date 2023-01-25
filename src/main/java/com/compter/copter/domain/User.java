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
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener.class)
public class User {

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
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id", nullable = false)
    private Role userRole;

    @OneToMany(mappedBy = "userMessage")
    private Set<Message> userMessageMessages;

    @OneToMany(mappedBy = "userTask")
    private Set<Task> userTaskTasks;

    @OneToMany(mappedBy = "userComment")
    private Set<Comment> userCommentComments;

    @OneToMany(mappedBy = "userPayment")
    private Set<Payment> userPaymentPayments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_user_id", nullable = false)
    private Company companyUser;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(final Role userRole) {
        this.userRole = userRole;
    }

    public Set<Message> getUserMessageMessages() {
        return userMessageMessages;
    }

    public void setUserMessageMessages(final Set<Message> userMessageMessages) {
        this.userMessageMessages = userMessageMessages;
    }

    public Set<Task> getUserTaskTasks() {
        return userTaskTasks;
    }

    public void setUserTaskTasks(final Set<Task> userTaskTasks) {
        this.userTaskTasks = userTaskTasks;
    }

    public Set<Comment> getUserCommentComments() {
        return userCommentComments;
    }

    public void setUserCommentComments(final Set<Comment> userCommentComments) {
        this.userCommentComments = userCommentComments;
    }

    public Set<Payment> getUserPaymentPayments() {
        return userPaymentPayments;
    }

    public void setUserPaymentPayments(final Set<Payment> userPaymentPayments) {
        this.userPaymentPayments = userPaymentPayments;
    }

    public Company getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(final Company companyUser) {
        this.companyUser = companyUser;
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
