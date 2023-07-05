package com.example.question_backend.service.ifs;

import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OutlineQuestionRequest;
import com.example.question_backend.vo.OutlineQuestionResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

public interface BackService {
	// �s�W�ݨ�
	public BackResponse add(OutlineQuestionRequest outlineQuestionRequest);
	
	// �s�W�ݨ��j��
//	public OutlineQuestionResponse addOutline(OutlineRequest outlineRequest);
	// �s�W�ݨ����D
//	public OutlineQuestionResponse addQuestion(QuestionRequest questionRequest);
	// �R���ݨ�
	// �ק�ݨ�
	
	// �ҽk�j�M�ݨ�(���D�B�}�l�ɶ��B�����ɶ�)
	public BackResponse search(OutlineRequest outlineRequest);
	
	// �d�ߩҦ��ݨ�
	public BackResponse getAll();
	
}
