package com.example.question_backend.constants;

public enum RtnCode {
	
	ADD_OUTLINE_SUCCESS("200", "�s�W�ݨ��j�����\"),
	ADD_QUESTION_SUCCESS("200", "�s�W�ݨ����D���\"),
	GET_ALL_SUCCESS("200", "���o�Ҧ��ݨ����\"),
	INCORRECT_INFO_ERROR("400", "��Ƥ����T"),
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
