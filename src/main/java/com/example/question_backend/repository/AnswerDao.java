package com.example.question_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question_backend.entity.Answer;

public interface AnswerDao extends JpaRepository<Answer, Integer>{

}
