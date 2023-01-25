package com.compter.copter.rest;

import com.compter.copter.model.FileDTO;
import com.compter.copter.service.FileService;
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
@RequestMapping(value = "/api/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileResource {

    private final FileService fileService;

    public FileResource(final FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<FileDTO>> getAllFiles() {
        return ResponseEntity.ok(fileService.findAll());
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<FileDTO> getFile(@PathVariable final Long fileId) {
        return ResponseEntity.ok(fileService.get(fileId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createFile(@RequestBody @Valid final FileDTO fileDTO) {
        return new ResponseEntity<>(fileService.create(fileDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<Void> updateFile(@PathVariable final Long fileId,
            @RequestBody @Valid final FileDTO fileDTO) {
        fileService.update(fileId, fileDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{fileId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFile(@PathVariable final Long fileId) {
        fileService.delete(fileId);
        return ResponseEntity.noContent().build();
    }

}
