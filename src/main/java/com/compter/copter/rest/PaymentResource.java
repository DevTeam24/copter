package com.compter.copter.rest;

import com.compter.copter.model.PaymentDTO;
import com.compter.copter.service.PaymentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable final Long paymentId) {
        return ResponseEntity.ok(paymentService.get(paymentId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPayment(@RequestBody @Valid final PaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentService.create(paymentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Void> updatePayment(@PathVariable final Long paymentId,
            @RequestBody @Valid final PaymentDTO paymentDTO) {
        paymentService.update(paymentId, paymentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{paymentId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePayment(@PathVariable final Long paymentId) {
        paymentService.delete(paymentId);
        return ResponseEntity.noContent().build();
    }

}
