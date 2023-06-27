package com.example.question_backend.service.ifs;

import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

public interface BackService {
	// 新增問卷大綱
	public BackResponse addOutline(OutlineRequest outlineRequest);
	
	// 新增問卷問題
	public BackResponse addQuestion(QuestionRequest questionRequest);
	// 刪除問卷
	// 修改問卷
	// 查詢所有問卷
	public BackResponse getAll();
	
}
