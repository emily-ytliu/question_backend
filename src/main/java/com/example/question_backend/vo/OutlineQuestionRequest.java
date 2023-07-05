package com.example.question_backend.vo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import com.example.question_backend.entity.Outline;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OutlineQuestionRequest {

	// Outline
	@JsonProperty("o_id_list")
	private List<String> oIdList;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("start_date")
	private String startDate;
	
	@JsonProperty("end_date")
	private String endDate;
	
	// Question
	@JsonProperty("q_id_list")
	private List<String> qIdList;

	@JsonProperty("question_id")
	private String questionId;
	
	@JsonProperty("outline_id")
	private String outlineId;
	
	@JsonProperty("question_title")
	private String questionTitle;
	
	@JsonProperty("not_null")
	private String notNull;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("question_selector")
	private String questionSelector;

	public OutlineQuestionRequest() {
	}

	// Outline
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getoIdList() {
		return oIdList;
	}

	public void setoIdList(List<String> oIdList) {
		this.oIdList = oIdList;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	// Question
	public List<String> getqIdList() {
		return qIdList;
	}

	public void setqIdList(List<String> qIdList) {
		this.qIdList = qIdList;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getOutlineId() {
		return outlineId;
	}

	public void setOutlineId(String outlineId) {
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
