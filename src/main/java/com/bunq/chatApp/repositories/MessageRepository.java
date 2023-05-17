package com.bunq.chatApp.repositories;

import com.bunq.chatApp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByGroupId(Long groupId);
}
