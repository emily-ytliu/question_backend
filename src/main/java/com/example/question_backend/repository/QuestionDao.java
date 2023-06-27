package com.example.question_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.question_backend.entity.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{

}
