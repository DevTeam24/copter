package com.compter.copter.service;

import com.compter.copter.domain.Payment;
import com.compter.copter.domain.User;
import com.compter.copter.model.PaymentDTO;
import com.compter.copter.repos.PaymentRepository;
import com.compter.copter.repos.UserRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentService(final PaymentRepository paymentRepository,
            final UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public List<PaymentDTO> findAll() {
        final List<Payment> payments = paymentRepository.findAll(Sort.by("paymentId"));
        return payments.stream()
                .map((payment) -> mapToDTO(payment, new PaymentDTO()))
                .collect(Collectors.toList());
    }

    public PaymentDTO get(final Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> mapToDTO(payment, new PaymentDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final PaymentDTO paymentDTO) {
        final Payment payment = new Payment();
        mapToEntity(paymentDTO, payment);
        return paymentRepository.save(payment).getPaymentId();
    }

    public void update(final Long paymentId, final PaymentDTO paymentDTO) {
        final Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(paymentDTO, payment);
        paymentRepository.save(payment);
    }

    public void delete(final Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    private PaymentDTO mapToDTO(final Payment payment, final PaymentDTO paymentDTO) {
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setDate(payment.getDate());
        paymentDTO.setPaymentType(payment.getPaymentType());
        paymentDTO.setUserPayment(payment.getUserPayment() == null ? null : payment.getUserPayment().getUserId());
        return paymentDTO;
    }

    private Payment mapToEntity(final PaymentDTO paymentDTO, final Payment payment) {
        payment.setAmount(paymentDTO.getAmount());
        payment.setDate(paymentDTO.getDate());
        payment.setPaymentType(paymentDTO.getPaymentType());
        final User userPayment = paymentDTO.getUserPayment() == null ? null : userRepository.findById(paymentDTO.getUserPayment())
                .orElseThrow(() -> new NotFoundException("userPayment not found"));
        payment.setUserPayment(userPayment);
        return payment;
    }

}
