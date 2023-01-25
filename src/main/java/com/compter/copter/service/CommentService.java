package com.compter.copter.service;

import com.compter.copter.domain.Comment;
import com.compter.copter.domain.Task;
import com.compter.copter.domain.User;
import com.compter.copter.model.CommentDTO;
import com.compter.copter.repos.CommentRepository;
import com.compter.copter.repos.TaskRepository;
import com.compter.copter.repos.UserRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentService(final CommentRepository commentRepository,
            final TaskRepository taskRepository, final UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDTO> findAll() {
        final List<Comment> comments = commentRepository.findAll(Sort.by("commentId"));
        return comments.stream()
                .map((comment) -> mapToDTO(comment, new CommentDTO()))
                .collect(Collectors.toList());
    }

    public CommentDTO get(final Long commentId) {
        return commentRepository.findById(commentId)
                .map(comment -> mapToDTO(comment, new CommentDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CommentDTO commentDTO) {
        final Comment comment = new Comment();
        mapToEntity(commentDTO, comment);
        return commentRepository.save(comment).getCommentId();
    }

    public void update(final Long commentId, final CommentDTO commentDTO) {
        final Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(commentDTO, comment);
        commentRepository.save(comment);
    }

    public void delete(final Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private CommentDTO mapToDTO(final Comment comment, final CommentDTO commentDTO) {
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setComment(comment.getComment());
        commentDTO.setCommentTask(comment.getCommentTask() == null ? null : comment.getCommentTask().getTaskId());
        commentDTO.setUserComment(comment.getUserComment() == null ? null : comment.getUserComment().getUserId());
        return commentDTO;
    }

    private Comment mapToEntity(final CommentDTO commentDTO, final Comment comment) {
        comment.setComment(commentDTO.getComment());
        final Task commentTask = commentDTO.getCommentTask() == null ? null : taskRepository.findById(commentDTO.getCommentTask())
                .orElseThrow(() -> new NotFoundException("commentTask not found"));
        comment.setCommentTask(commentTask);
        final User userComment = commentDTO.getUserComment() == null ? null : userRepository.findById(commentDTO.getUserComment())
                .orElseThrow(() -> new NotFoundException("userComment not found"));
        comment.setUserComment(userComment);
        return comment;
    }

}
