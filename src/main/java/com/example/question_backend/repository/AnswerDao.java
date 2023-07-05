package com.example.question_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_backend.entity.Answer;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer>{

}
