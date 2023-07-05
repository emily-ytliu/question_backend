package com.example.question_backend.service.ifs;

import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OutlineQuestionRequest;
import com.example.question_backend.vo.OutlineQuestionResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

public interface BackService {
	// 新增問卷
	public BackResponse add(OutlineQuestionRequest outlineQuestionRequest);
	
	// 新增問卷大綱
//	public OutlineQuestionResponse addOutline(OutlineRequest outlineRequest);
	// 新增問卷問題
//	public OutlineQuestionResponse addQuestion(QuestionRequest questionRequest);
	// 刪除問卷
	// 修改問卷
	
	// 模糊搜尋問卷(標題、開始時間、結束時間)
	public BackResponse search(OutlineRequest outlineRequest);
	
	// 查詢所有問卷
	public BackResponse getAll();
	
}
