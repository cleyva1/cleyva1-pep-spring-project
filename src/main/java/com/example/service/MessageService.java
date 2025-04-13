package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByID(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(Long.valueOf(id));
        if (optionalMessage.isPresent()) {
            return optionalMessage.get();
        }
        else {
            return null;
        }
    }

    public Message insertMessage(Message message) {
        return messageRepository.save(message);
    }

    public int deleteMessage(Integer id) {
       return messageRepository.deleteByMessageId(id);
    }

    public void updateMessage(long id, Message replacement){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            message.setMessageText(replacement.getMessageText());
            messageRepository.save(message);
        }
    }
}
