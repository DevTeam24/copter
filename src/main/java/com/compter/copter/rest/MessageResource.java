package com.compter.copter.rest;

import com.compter.copter.model.MessageDTO;
import com.compter.copter.service.MessageService;
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
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageResource {

    private final MessageService messageService;

    public MessageResource(final MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable final Long messageId) {
        return ResponseEntity.ok(messageService.get(messageId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createMessage(@RequestBody @Valid final MessageDTO messageDTO) {
        return new ResponseEntity<>(messageService.create(messageDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<Void> updateMessage(@PathVariable final Long messageId,
            @RequestBody @Valid final MessageDTO messageDTO) {
        messageService.update(messageId, messageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{messageId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMessage(@PathVariable final Long messageId) {
        messageService.delete(messageId);
        return ResponseEntity.noContent().build();
    }

}
