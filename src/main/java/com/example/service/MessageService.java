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
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByID(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findMessageByMessageId(id);
        if (optionalMessage.isPresent()) {
            return optionalMessage.get();
        }
        else {
            return null;
        }
    }

    public Message insertMessage(Message message) {
        if (message.getMessageText() != "" && message.getMessageText().length() <= 255) {
            return messageRepository.save(message);
        }
        else {
            return null; 
        } 
        
    }

    public Integer deleteMessage(Integer id) {
       return messageRepository.deleteByMessageId(id).get();
    }

    public Integer updateMessage(Integer id, Message message){
        Optional<Message> optionalMessage = messageRepository.findMessageByMessageId(id);
        if (message.getMessageText().length() > 255 || message.getMessageText() == "") {
            return null;
        }
        if(optionalMessage.isPresent()){
            Message newMessage = optionalMessage.get();
            newMessage.setMessageText(message.getMessageText());
            messageRepository.save(newMessage);
            return 1;
        }
        else {
            return null;
        }
    }

    public List<Message> getMessagesByUser(Integer accountId) {
        return messageRepository.findMessagesByPostedBy(accountId);
    }
}
