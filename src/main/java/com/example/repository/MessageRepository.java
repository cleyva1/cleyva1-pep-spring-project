package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findMessageByMessageId(Integer messageId);

    Optional<Integer> deleteByMessageId(Integer messageId);

    List<Message> findMessagesByPostedBy(Integer postedBy);
}
