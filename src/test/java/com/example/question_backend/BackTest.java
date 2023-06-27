package com.example.question_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.question_backend.repository.OutlineDao;
import com.example.question_backend.service.ifs.BackService;


@SpringBootTest(classes = QuestionBackendApplication.class)
public class BackTest {
	
	@Autowired
	private BackService backService;

	@Autowired
	private OutlineDao backDao;
	
//	@Test
//	public void Test() {
//		
//	}
}
