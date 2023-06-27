package com.example.question_backend.service.ifs;

import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

public interface BackService {
	// �s�W�ݨ��j��
	public BackResponse addOutline(OutlineRequest outlineRequest);
	
	// �s�W�ݨ����D
	public BackResponse addQuestion(QuestionRequest questionRequest);
	// �R���ݨ�
	// �ק�ݨ�
	// �d�ߩҦ��ݨ�
	public BackResponse getAll();
	
}
