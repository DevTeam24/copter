package com.compter.copter.service;

import com.compter.copter.domain.Message;
import com.compter.copter.domain.User;
import com.compter.copter.model.MessageDTO;
import com.compter.copter.repos.MessageRepository;
import com.compter.copter.repos.UserRepository;
import com.compter.copter.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(final MessageRepository messageRepository,
            final UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<MessageDTO> findAll() {
        final List<Message> messages = messageRepository.findAll(Sort.by("messageId"));
        return messages.stream()
                .map((message) -> mapToDTO(message, new MessageDTO()))
                .collect(Collectors.toList());
    }

    public MessageDTO get(final Long messageId) {
        return messageRepository.findById(messageId)
                .map(message -> mapToDTO(message, new MessageDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final MessageDTO messageDTO) {
        final Message message = new Message();
        mapToEntity(messageDTO, message);
        return messageRepository.save(message).getMessageId();
    }

    public void update(final Long messageId, final MessageDTO messageDTO) {
        final Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(messageDTO, message);
        messageRepository.save(message);
    }

    public void delete(final Long messageId) {
        messageRepository.deleteById(messageId);
    }

    private MessageDTO mapToDTO(final Message message, final MessageDTO messageDTO) {
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setSenderId(message.getSenderId());
        messageDTO.setReceiverId(message.getReceiverId());
        messageDTO.setDate(message.getDate());
        messageDTO.setUserMessage(message.getUserMessage() == null ? null : message.getUserMessage().getUserId());
        return messageDTO;
    }

    private Message mapToEntity(final MessageDTO messageDTO, final Message message) {
        message.setSenderId(messageDTO.getSenderId());
        message.setReceiverId(messageDTO.getReceiverId());
        message.setDate(messageDTO.getDate());
        final User userMessage = messageDTO.getUserMessage() == null ? null : userRepository.findById(messageDTO.getUserMessage())
                .orElseThrow(() -> new NotFoundException("userMessage not found"));
        message.setUserMessage(userMessage);
        return message;
    }

    public boolean senderIdExists(final Integer senderId) {
        return messageRepository.existsBySenderId(senderId);
    }

    public boolean receiverIdExists(final Integer receiverId) {
        return messageRepository.existsByReceiverId(receiverId);
    }

}
