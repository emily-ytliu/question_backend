package com.example.question_backend.vo;

import java.util.List;

import com.example.question_backend.entity.Answer;
import com.example.question_backend.entity.Outline;
import com.example.question_backend.entity.Question;

public class BackResponse {
	
	private List<Outline> outlineList;
	
	private Outline outline;
	
	private Question question;
	
	private Answer answer;
	
	private String message;

	public BackResponse() {
	}

	public BackResponse(List<Outline> outlineList, String message) {
		this.outlineList = outlineList;
		this.message = message;
	}

	public BackResponse(Outline outline, Question question, Answer answer, String message) {
		this.outline = outline;
		this.question = question;
		this.answer = answer;
		this.message = message;
	}

	public BackResponse(Outline outline, String message) {
		this.outline = outline;
		this.message = message;
	}

	public BackResponse(Question question, String message) {
		this.question = question;
		this.message = message;
	}

	public BackResponse(Answer answer, String message) {
		this.answer = answer;
		this.message = message;
	}

	public BackResponse(String message) {
		this.message = message;
	}

	public List<Outline> getOutlineList() {
		return outlineList;
	}

	public void setOutlineList(List<Outline> outlineList) {
		this.outlineList = outlineList;
	}

	public Outline getOutline() {
		return outline;
	}

	public void setOutline(Outline outline) {
		this.outline = outline;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
