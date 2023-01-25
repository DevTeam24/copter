package com.compter.copter.repos;

import com.compter.copter.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
