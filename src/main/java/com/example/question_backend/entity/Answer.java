package com.example.question_backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@Column(name = "answer_id")
	private int answerId;
	
	@Column(name = "outline_id")
	private int outlineId;
	
	@Column(name = "answer_sheet")
	private String answerSheet;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "write_time")
	private LocalDateTime writeTime;

	public Answer() {
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getOutlineId() {
		return outlineId;
	}

	public void setOutlineId(int outlineId) {
		this.outlineId = outlineId;
	}

	public String getAnswerSheet() {
		return answerSheet;
	}

	public void setAnswerSheet(String answerSheet) {
		this.answerSheet = answerSheet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDateTime getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(LocalDateTime writeTime) {
		this.writeTime = writeTime;
	}
	
}
