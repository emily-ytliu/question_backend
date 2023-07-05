package com.example.question_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_backend.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

}
