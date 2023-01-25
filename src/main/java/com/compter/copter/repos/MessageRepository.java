package com.compter.copter.repos;

import com.compter.copter.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Long> {

    boolean existsBySenderId(Integer senderId);

    boolean existsByReceiverId(Integer receiverId);

}
