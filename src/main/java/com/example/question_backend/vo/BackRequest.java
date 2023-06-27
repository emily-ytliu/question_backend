package com.example.question_backend.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackRequest {

	@JsonProperty("outline_id")
	private int outlineId;
	
	@JsonProperty("answer_id")
	private int answerId;

	public BackRequest() {
	}

	public int getOutlineId() {
		return outlineId;
	}

	public void setOutlineId(int outlineId) {
		this.outlineId = outlineId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	
}
