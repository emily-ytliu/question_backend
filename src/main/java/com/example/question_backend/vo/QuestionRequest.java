package com.example.question_backend.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionRequest {
	
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

	public QuestionRequest() {
	}

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
