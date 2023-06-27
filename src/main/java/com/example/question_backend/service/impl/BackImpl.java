package com.example.question_backend.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.question_backend.constants.RtnCode;
import com.example.question_backend.entity.Outline;
import com.example.question_backend.entity.Question;
import com.example.question_backend.repository.AnswerDao;
import com.example.question_backend.repository.OutlineDao;
import com.example.question_backend.repository.QuestionDao;
import com.example.question_backend.service.ifs.BackService;
import com.example.question_backend.vo.BackResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class BackImpl implements BackService{
	
	@Autowired
	OutlineDao outlineDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	AnswerDao answerDao;

	@Override
	// �s�W�ݨ��j��
	public BackResponse addOutline(OutlineRequest outlineRequest) {
		// ���X��J���ݨ��j����T
		String title = outlineRequest.getTitle();
		String status = outlineRequest.getStatus();
		String startDate = outlineRequest.getStartDate();
		String endDate = outlineRequest.getEndDate();
		String description = outlineRequest.getDescription();
		
		// �P�_��ƬO�_����
		if (!StringUtils.hasText(title)
	    		|| !StringUtils.hasText(status)
	    		|| !StringUtils.hasText(startDate)
	    		|| !StringUtils.hasText(endDate)
	    		|| !StringUtils.hasText(description)) {
	    	return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		 
//		if (!StringUtils.hasText(title)) {
//	    	return new BackResponse("�ݨ����D���~");
//	    }
// 		if (!StringUtils.hasText(status)) {
//	    	return new BackResponse("�ݨ����A���~");
//	    }
// 		if (!StringUtils.hasText(startDate)) {
//	    	return new BackResponse("�ݨ��}�l������~");
//	    }
// 		if (!StringUtils.hasText(endDate)) {
//	    	return new BackResponse("�ݨ�����������~");
//	    }
// 		if (!StringUtils.hasText(description)) {
//	    	return new BackResponse("�ݨ��y�z���~");
//	    }
		
		// �r����LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);	
 		
	    // �s�W�ݨ��j����T
	    Outline outline = new Outline();
	    outline.setTitle(title);
	    outline.setStatus(status);
	    outline.setStartDate(localDateStart);
	    outline.setEndDate(localDateEnd);
	    outline.setDescription(description);
	    outline.setQuestionId(null);
	    outline.setAnswerId(null);
	    
	    outlineDao.save(outline);
		
		return new BackResponse(outline, RtnCode.ADD_OUTLINE_SUCCESS.getMessage());
	}

	@Override
	// �s�W�ݨ����D
	public BackResponse addQuestion(QuestionRequest questionRequest) {
		// ���X��J���ݨ����D
		String outlineId = questionRequest.getOutlineId();
		String questionTitle = questionRequest.getQuestionTitle();
		String notNull = questionRequest.getNotNull();
		String type = questionRequest.getType();
		String questionSelector = questionRequest.getQuestionSelector();
		
		// �P�_��ƬO�_����
		if (!StringUtils.hasText(outlineId)
	    		|| !StringUtils.hasText(questionTitle)
	    		|| !StringUtils.hasText(notNull)
	    		|| !StringUtils.hasText(type)
	    		|| !StringUtils.hasText(questionSelector)) {
	    	return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		
		// �r���নint
		int intOutlineId = Integer.parseInt(outlineId);
		
		// �r���নList
		// 1. questionTitle
		String qTitleAry[] = questionTitle.replace(" ", "").split(",");  // �h�����Ҧ��Ů�A�A�γr�I��
		List<String> qTitleList = Arrays.asList(qTitleAry);  // Array�নList
		// 2. notNull
		String notNullAry[] = notNull.replace(" ", "").split(",");
		List<String> notNullList = Arrays.asList(notNullAry);
		// 3. type
		String typeAry[] = type.replace(" ", "").split(",");
		List<String> typeList = Arrays.asList(typeAry);
		// 4. questionSelector
//		String qSelectorAry[] = questionTitle.replace(" ", "").split(";");  // �Τ�����
//		List<String> qSelectorList = Arrays.asList(qSelectorAry);
		
		// 4. questionSelector
		// �r���নList
		String qSelectorAry[] = questionSelector.replace("'", "").split(", ");  // �h����޸��A�A�γr�I�Ů��
		
		List<List<String>> resultList = new ArrayList<>();
		for (String aryItem : qSelectorAry) {
			String ary[] = aryItem.split(";");  // �Τ�����
			List<String> itemList = Arrays.asList(ary);  // Array�নList
			
			// List���C�Ӷ��ؤ���O��
			for (String item : itemList) {
				if (!StringUtils.hasText(item)) {
					return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
				}
			}
			resultList.add(itemList);
		}
		// List�ন�r��
		String resSelectorStr = resultList.toString();  //List�ন�r��
		String resultStr = resSelectorStr.substring(1, resSelectorStr.length()-1);  //�h�����A��
		
		
//		// �إ�Gson����
//		Gson gson = new Gson();
//		// �ϥ�TypeToken���w�ഫ�ؼЪ���ƫ��A
//		TypeToken<List<List<String>>> token = new TypeToken<List<List<String>>>() {};
//		// �ϥ�fromJson��k�ഫ��List
//		List<List<String>> allSelectorList = gson.fromJson(questionSelector, token.getType());
		
		
		
		// List���C�Ӷ��ؤ���O��
		// 1. questionTitle
		for (String item : qTitleList) {
			if (!StringUtils.hasText(item)) {
				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
			}
		}
		// 2. notNull
		for (String item : notNullList) {
			if (!StringUtils.hasText(item)) {
				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
			}
		}
		// 3. type
		for (String item : typeList) {
			if (!StringUtils.hasText(item)) {
				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
			}
		}
		// 4. questionSelector
//		for (String item : qSelectorList) {
//			if (!StringUtils.hasText(item)) {
//				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//			}
//		}
		
		// List�ন�r��
		// 1. questionTitle
		String qTitleStr = String.join(", ", qTitleList);  //��", "�h�s��(�B���|�����A��)
		// 2. notNull
		String notNullStr = String.join(", ", notNullList);
		// 3. type
		String typeStr = String.join(", ", typeList);
		// 4. questionSelector
//		String qSelectorStr = String.join(", ", qSelectorList);
		
		
		
		// �s�W�ݨ����D
		Question question = new Question();
		question.setOutlineId(intOutlineId);
		question.setQuestionTitle(qTitleStr); 
		question.setNotNull(notNullStr);
		question.setType(typeStr);
		question.setQuestionSelector(resultStr);
		
		questionDao.save(question);
		

// 		if (!StringUtils.hasText(questionId)) {
//	    	return new BackResponse("�D�����~");
//	    }
// 		if (!StringUtils.hasText(questionTitle)) {
//	    	return new BackResponse("���D���~");
//	    }
// 		if (!StringUtils.hasText(notNull)) {
//	    	return new BackResponse("�O�_������~");
//	    }
// 		if (!StringUtils.hasText(type)) {
//	    	return new BackResponse("�������~");
//	    }
// 		if (!StringUtils.hasText(questionSelector)) {
//	    	return new BackResponse("�ﶵ���~");
//	    }

		return new BackResponse(RtnCode.ADD_QUESTION_SUCCESS.getMessage());
	}

	@Override
	// �d�ߩҦ��ݨ��j��
	public BackResponse getAll() {
		List<Outline> outlineList = outlineDao.findAll();
		
		return new BackResponse(outlineList, RtnCode.GET_ALL_SUCCESS.getMessage());
	}

}
