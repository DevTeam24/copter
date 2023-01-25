package com.compter.copter.service;

import com.compter.copter.domain.File;
import com.compter.copter.domain.Task;
import com.compter.copter.model.FileDTO;
import com.compter.copter.repos.FileRepository;
import com.compter.copter.repos.TaskRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FileService {

    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;

    public FileService(final FileRepository fileRepository, final TaskRepository taskRepository) {
        this.fileRepository = fileRepository;
        this.taskRepository = taskRepository;
    }

    public List<FileDTO> findAll() {
        final List<File> files = fileRepository.findAll(Sort.by("fileId"));
        return files.stream()
                .map((file) -> mapToDTO(file, new FileDTO()))
                .collect(Collectors.toList());
    }

    public FileDTO get(final Long fileId) {
        return fileRepository.findById(fileId)
                .map(file -> mapToDTO(file, new FileDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final FileDTO fileDTO) {
        final File file = new File();
        mapToEntity(fileDTO, file);
        return fileRepository.save(file).getFileId();
    }

    public void update(final Long fileId, final FileDTO fileDTO) {
        final File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(fileDTO, file);
        fileRepository.save(file);
    }

    public void delete(final Long fileId) {
        fileRepository.deleteById(fileId);
    }

    private FileDTO mapToDTO(final File file, final FileDTO fileDTO) {
        fileDTO.setFileId(file.getFileId());
        fileDTO.setType(file.getType());
        fileDTO.setData(file.getData());
        fileDTO.setTaskFile(file.getTaskFile() == null ? null : file.getTaskFile().getTaskId());
        return fileDTO;
    }

    private File mapToEntity(final FileDTO fileDTO, final File file) {
        file.setType(fileDTO.getType());
        file.setData(fileDTO.getData());
        final Task taskFile = fileDTO.getTaskFile() == null ? null : taskRepository.findById(fileDTO.getTaskFile())
                .orElseThrow(() -> new NotFoundException("taskFile not found"));
        file.setTaskFile(taskFile);
        return file;
    }

}
