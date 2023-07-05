package com.example.question_backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int questionId;
	
	@Column(name = "outline_id")
	private int outlineId;
	
	@Column(name = "question_title")
	private String questionTitle;
	
	@Column(name = "not_null")
	private String notNull;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "question_selector")
	private String questionSelector;

	public Question() {
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getOutlineId() {
		return outlineId;
	}

	public void setOutlineId(int outlineId) {
		this.outlineId = outlineId;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getNotNull() {
		return notNull;
	}

	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestionSelector() {
		return questionSelector;
	}

	public void setQuestionSelector(String questionSelector) {
		this.questionSelector = questionSelector;
	}

}
