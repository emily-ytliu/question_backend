package com.example.question_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.question_backend.service.ifs.BackService;
import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

@CrossOrigin
@RestController
public class BackController {

	@Autowired
	private BackService backService;
	
	// �s�W�ݨ��j��
	@PostMapping(value = "add_outline")
	public BackResponse addOutline(@RequestBody OutlineRequest outlineRequest) {
		return backService.addOutline(outlineRequest);
	}
	
	// �s�W�ݨ����D
	@PostMapping(value = "add_question")
	public BackResponse addQuestion(@RequestBody QuestionRequest questionRequest) {
		return backService.addQuestion(questionRequest);
	}
	
	// �d�ߩҦ��ݨ�
	@GetMapping(value = "get_all")
	public BackResponse getAll() {
		return backService.getAll();
	}
	
	
}
