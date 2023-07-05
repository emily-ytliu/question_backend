package com.example.question_backend.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.question_backend.entity.Answer;
import com.example.question_backend.entity.Outline;
import com.example.question_backend.entity.Question;

public class OutlineQuestionResponse {
	
	private Outline outline;
	
//	private Question question;
	
	private String message;
	
	public OutlineQuestionResponse() {
	}

//	public OutlineQuestionResponse(Question question, String message) {
//		this.question = question;
//		this.message = message;
//	}
	
	public OutlineQuestionResponse(Outline outline, String message) {
		this.outline = outline;
		this.message = message;
	}

	public OutlineQuestionResponse(String message) {
		this.message = message;
	}

	public Outline getOutline() {
		return outline;
	}

	public void setOutline(Outline outline) {
		this.outline = outline;
	}

//	public Question getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(Question question) {
//		this.question = question;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
