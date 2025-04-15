package com.example.repository;

import com.example.entity.Account;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findMessageByMessageId(Integer messageId);

    Optional<Integer> deleteByMessageId(Integer messageId);
}
