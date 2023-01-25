package com.compter.copter.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class PaymentDTO {

    private Long paymentId;

    @NotNull
    private Integer amount;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private PaymentType paymentType;

    @NotNull
    private Long userPayment;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final Long paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(final PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(final Long userPayment) {
        this.userPayment = userPayment;
    }

}
