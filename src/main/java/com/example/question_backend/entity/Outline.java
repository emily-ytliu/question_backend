package com.example.question_backend.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outline")
public class Outline {
	
	@Id
	@Column(name = "outline_id")
	private int outlineId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "question_id")
	private String questionId;
	
	@Column(name = "answer_id")
	private String answerId;

	public Outline() {
	}

	public int getOutlineId() {
		return outlineId;
	}

	public void setOutlineId(int outlineId) {
		this.outlineId = outlineId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
}
