package com.example.question_backend.constants;

public enum RtnCode {
	
	ADD_OUTLINE_SUCCESS("200", "�s�W�ݨ��j�����\"),
	ADD_QUESTION_SUCCESS("200", "�s�W�ݨ����D���\"),
	ADD_SUCCESS("200", "�s�W�ݨ����\"),
	SEARCH_SUCCESS("200", "�j�M�ݨ����\"),
	GET_ALL_SUCCESS("200", "���o�Ҧ��ݨ����\"),
	GET_ONE_SUCCESS("200", "���o�S�w�ݨ����\"),
	NO_SEARCH_INFO("400", "�d�L���"),
	INCORRECT_INFO_ERROR("400", "��Ƥ����T"),
	DATA_IS_EXISTED("400", "��Ƥw�g�s�b"),
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
