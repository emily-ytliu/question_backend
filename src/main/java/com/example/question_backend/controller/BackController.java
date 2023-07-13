package com.example.question_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.question_backend.service.ifs.BackService;
import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OneDataResponse;
import com.example.question_backend.vo.OutlineQuestionRequest;
import com.example.question_backend.vo.OutlineQuestionResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

@CrossOrigin
@RestController
public class BackController {

	@Autowired
	private BackService backService;
	
	// 新增問卷大綱
	@PostMapping(value = "add")
	public BackResponse add(@RequestBody OutlineQuestionRequest outlineQuestionRequest) {
		return backService.add(outlineQuestionRequest);
	}

	// 新增問卷大綱
//	@PostMapping(value = "add_outline")
//	public OutlineQuestionResponse addOutline(@RequestBody OutlineRequest outlineRequest) {
//		return backService.addOutline(outlineRequest);
//	}
	
	// 新增問卷問題
//	@PostMapping(value = "add_question")
//	public OutlineQuestionResponse addQuestion(@RequestBody QuestionRequest questionRequest) {
//		return backService.addQuestion(questionRequest);
//	}
	
	// 模糊搜尋問卷(標題、開始時間、結束時間)
	@PostMapping(value = "search")
	public BackResponse search(@RequestBody OutlineRequest outlineRequest) {
		return backService.search(outlineRequest);
	}
	
	// 查詢所有問卷
	@GetMapping(value = "get_all")
	public BackResponse getAll() {
		return backService.getAll();
	}
	
	// 查詢特定問卷
	@PostMapping(value = "get_one")
	public OneDataResponse getOne(@RequestBody OutlineQuestionRequest outlineQuestionRequest) {
		return backService.getOne(outlineQuestionRequest);
	}
	
	
}
