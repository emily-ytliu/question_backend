package com.example.question_backend.constants;

public enum RtnCode {
	
	ADD_OUTLINE_SUCCESS("200", "新增問卷大綱成功"),
	ADD_QUESTION_SUCCESS("200", "新增問卷問題成功"),
	GET_ALL_SUCCESS("200", "取得所有問卷成功"),
	INCORRECT_INFO_ERROR("400", "資料不正確"),
	;

	private String code;

    private String message;

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
