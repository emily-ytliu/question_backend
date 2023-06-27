package com.example.question_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question_backend.entity.Outline;

public interface OutlineDao extends JpaRepository<Outline, Integer>{

}
