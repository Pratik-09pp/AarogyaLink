package com.example.ArogyaLink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArogyaLink.Entity.Assistant;

public interface AssistantRepository extends JpaRepository<Assistant,Long> {
    public Assistant findByEmail(String email);

}
