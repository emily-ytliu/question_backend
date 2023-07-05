package com.example.question_backend.vo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import com.example.question_backend.entity.Outline;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OutlineRequest {

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

	public OutlineRequest() {
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
	
}
