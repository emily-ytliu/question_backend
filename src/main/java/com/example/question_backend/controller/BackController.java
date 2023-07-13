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
	
	// �s�W�ݨ��j��
	@PostMapping(value = "add")
	public BackResponse add(@RequestBody OutlineQuestionRequest outlineQuestionRequest) {
		return backService.add(outlineQuestionRequest);
	}

	// �s�W�ݨ��j��
//	@PostMapping(value = "add_outline")
//	public OutlineQuestionResponse addOutline(@RequestBody OutlineRequest outlineRequest) {
//		return backService.addOutline(outlineRequest);
//	}
	
	// �s�W�ݨ����D
//	@PostMapping(value = "add_question")
//	public OutlineQuestionResponse addQuestion(@RequestBody QuestionRequest questionRequest) {
//		return backService.addQuestion(questionRequest);
//	}
	
	// �ҽk�j�M�ݨ�(���D�B�}�l�ɶ��B�����ɶ�)
	@PostMapping(value = "search")
	public BackResponse search(@RequestBody OutlineRequest outlineRequest) {
		return backService.search(outlineRequest);
	}
	
	// �d�ߩҦ��ݨ�
	@GetMapping(value = "get_all")
	public BackResponse getAll() {
		return backService.getAll();
	}
	
	// �d�߯S�w�ݨ�
	@PostMapping(value = "get_one")
	public OneDataResponse getOne(@RequestBody OutlineQuestionRequest outlineQuestionRequest) {
		return backService.getOne(outlineQuestionRequest);
	}
	
	
}
