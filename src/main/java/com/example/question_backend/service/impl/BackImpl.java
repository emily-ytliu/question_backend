package com.example.question_backend.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.example.question_backend.vo.OneDataResponse;
import com.example.question_backend.vo.OutlineQuestionRequest;
import com.example.question_backend.vo.OutlineQuestionResponse;
import com.example.question_backend.vo.OutlineRequest;
import com.example.question_backend.vo.QuestionRequest;

@Service
public class BackImpl implements BackService{
	
	@Autowired
	OutlineDao outlineDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	AnswerDao answerDao;
	
	@Transactional
	@Override
	// �s�W�ݨ�
	public BackResponse add(OutlineQuestionRequest outlineQuestionRequest) {
		// ���X��J���ݨ��j����T
		String title = outlineQuestionRequest.getTitle();
		String startDate = outlineQuestionRequest.getStartDate();
		String endDate = outlineQuestionRequest.getEndDate();
		String description = outlineQuestionRequest.getDescription();
		
		// �P�_��ƬO�_����
		if (!StringUtils.hasText(title)
	    		|| !StringUtils.hasText(startDate)
	    		|| !StringUtils.hasText(endDate)
	    		|| !StringUtils.hasText(description)) {
	    	return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		
//		if (!StringUtils.hasText(title)) {
//    	return new BackResponse("�ݨ����D���~");
//    }
//		if (!StringUtils.hasText(startDate)) {
//    	return new BackResponse("�ݨ��}�l������~");
//    }
//		if (!StringUtils.hasText(endDate)) {
//    	return new BackResponse("�ݨ�����������~");
//    }
//		if (!StringUtils.hasText(description)) {
//    	return new BackResponse("�ݨ��y�z���~");
//    }
		
		// �r����LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);	
	    
	    // ========== question ========== 
		// ���X��J���ݨ����D
		String questionTitle = outlineQuestionRequest.getQuestionTitle();
		String notNull = outlineQuestionRequest.getNotNull();
		String type = outlineQuestionRequest.getType();
		String questionSelector = outlineQuestionRequest.getQuestionSelector();
		
		// �P�_��ƬO�_����
		if (!StringUtils.hasText(questionTitle)
	    		|| !StringUtils.hasText(notNull)
	    		|| !StringUtils.hasText(type)
	    		|| !StringUtils.hasText(questionSelector)) {
	    	return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		
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
		
		// List�ন�r��
		// 1. questionTitle
		String qTitleStr = String.join(", ", qTitleList);  //��", "�h�s��(�B���|�����A��)
		// 2. notNull
		String notNullStr = String.join(", ", notNullList);
		// 3. type
		String typeStr = String.join(", ", typeList);
		
	    // �s�W�ݨ��j����T
	    Outline outline = new Outline();
	    outline.setTitle(title);
	    outline.setDescription(description);
	    outline.setStartDate(localDateStart);
	    outline.setEndDate(localDateEnd);
	    
	    outlineDao.save(outline);
	    
		// �s�W�ݨ����D
	    Question question = new Question();
	    question.setOutlineId(outline.getOutlineId());
	    question.setQuestionTitle(qTitleStr); 
	    question.setNotNull(notNullStr);
	    question.setType(typeStr);
	    question.setQuestionSelector(resultStr);
		
		questionDao.save(question);
		
	    
		return new BackResponse(outline, question, RtnCode.ADD_SUCCESS.getMessage());
	}

//	@Override
//	// �s�W�ݨ��j��
//	public OutlineQuestionResponse addOutline(OutlineRequest outlineRequest) {
//		// ���X��J���ݨ��j����T
//		String title = outlineRequest.getTitle();
////		String status = outlineRequest.getStatus();
//		String startDate = outlineRequest.getStartDate();
//		String endDate = outlineRequest.getEndDate();
//		String description = outlineRequest.getDescription();
//		String questionId = outlineRequest.getQuestionId();
//		
//		// �P�_��ƬO�_����
//		if (!StringUtils.hasText(title)
////	    		|| !StringUtils.hasText(status)
//	    		|| !StringUtils.hasText(startDate)
//	    		|| !StringUtils.hasText(endDate)
//	    		|| !StringUtils.hasText(description)
//	    		|| !StringUtils.hasText(questionId)) {
//	    	return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//	    }
//		
////		// �P�_�w�g�s�b��outlineId���୫���x�s
////		boolean aiId = outlineDao.existsById(null);
////		if (!aiId) {
////			return new BackResponse(RtnCode.DATA_IS_EXISTED.getMessage());
////		}
//		
//		// �r����LocalDate
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
//	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);	
// 		
//	    // �s�W�ݨ��j����T
//	    Outline outline = new Outline();
//	    outline.setTitle(title);
////	    outline.setStatus(status);
//	    outline.setStartDate(localDateStart);
//	    outline.setEndDate(localDateEnd);
//	    outline.setDescription(description);
////	    outline.setQuestionId(questionId);
//	    outline.setAnswerId(null);
//	    
//	    outlineDao.save(outline);
//		 
////		if (!StringUtils.hasText(title)) {
////	    	return new BackResponse("�ݨ����D���~");
////	    }
//// 		if (!StringUtils.hasText(status)) {
////	    	return new BackResponse("�ݨ����A���~");
////	    }
//// 		if (!StringUtils.hasText(startDate)) {
////	    	return new BackResponse("�ݨ��}�l������~");
////	    }
//// 		if (!StringUtils.hasText(endDate)) {
////	    	return new BackResponse("�ݨ�����������~");
////	    }
//// 		if (!StringUtils.hasText(description)) {
////	    	return new BackResponse("�ݨ��y�z���~");
////	    }
//		
//		return new OutlineQuestionResponse(outline, RtnCode.ADD_OUTLINE_SUCCESS.getMessage());
//	}

//	@Override
//	// �s�W�ݨ����D
//	public OutlineQuestionResponse addQuestion(QuestionRequest questionRequest) {
//		// ���X��J���ݨ����D
//		String outlineId = questionRequest.getOutlineId();
//		String questionTitle = questionRequest.getQuestionTitle();
//		String notNull = questionRequest.getNotNull();
//		String type = questionRequest.getType();
//		String questionSelector = questionRequest.getQuestionSelector();
//		
//		// �P�_��ƬO�_����
//		if (!StringUtils.hasText(outlineId)
//	    		|| !StringUtils.hasText(questionTitle)
//	    		|| !StringUtils.hasText(notNull)
//	    		|| !StringUtils.hasText(type)
//	    		|| !StringUtils.hasText(questionSelector)) {
//	    	return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//	    }
//		
////		boolean aiId = questionDao.existsById(null);
////		if (!aiId) {
////			return new BackResponse(RtnCode.DATA_IS_EXISTED.getMessage());
////		}
//		
//		// �r���নint
//		int intOutlineId = Integer.parseInt(outlineId);
//		
//		// �r���নList
//		// 1. questionTitle
//		String qTitleAry[] = questionTitle.replace(" ", "").split(",");  // �h�����Ҧ��Ů�A�A�γr�I��
//		List<String> qTitleList = Arrays.asList(qTitleAry);  // Array�নList
//		// 2. notNull
//		String notNullAry[] = notNull.replace(" ", "").split(",");
//		List<String> notNullList = Arrays.asList(notNullAry);
//		// 3. type
//		String typeAry[] = type.replace(" ", "").split(",");
//		List<String> typeList = Arrays.asList(typeAry);
//		// 4. questionSelector
////		String qSelectorAry[] = questionTitle.replace(" ", "").split(";");  // �Τ�����
////		List<String> qSelectorList = Arrays.asList(qSelectorAry);
//		
//		// 4. questionSelector
//		// �r���নList
//		String qSelectorAry[] = questionSelector.replace("'", "").split(", ");  // �h����޸��A�A�γr�I�Ů��
//		
//		List<List<String>> resultList = new ArrayList<>();
//		for (String aryItem : qSelectorAry) {
//			String ary[] = aryItem.split(";");  // �Τ�����
//			List<String> itemList = Arrays.asList(ary);  // Array�নList
//			
//			// List���C�Ӷ��ؤ���O��
//			for (String item : itemList) {
//				if (!StringUtils.hasText(item)) {
//					return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//				}
//			}
//			resultList.add(itemList);
//		}
//		// List�ন�r��
//		String resSelectorStr = resultList.toString();  //List�ন�r��
//		String resultStr = resSelectorStr.substring(1, resSelectorStr.length()-1);  //�h�����A��
//		
//		
////		// �إ�Gson����
////		Gson gson = new Gson();
////		// �ϥ�TypeToken���w�ഫ�ؼЪ���ƫ��A
////		TypeToken<List<List<String>>> token = new TypeToken<List<List<String>>>() {};
////		// �ϥ�fromJson��k�ഫ��List
////		List<List<String>> allSelectorList = gson.fromJson(questionSelector, token.getType());
//		
//		
//		
//		// List���C�Ӷ��ؤ���O��
//		// 1. questionTitle
//		for (String item : qTitleList) {
//			if (!StringUtils.hasText(item)) {
//				return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//			}
//		}
//		// 2. notNull
//		for (String item : notNullList) {
//			if (!StringUtils.hasText(item)) {
//				return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//			}
//		}
//		// 3. type
//		for (String item : typeList) {
//			if (!StringUtils.hasText(item)) {
//				return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//			}
//		}
//		// 4. questionSelector
////		for (String item : qSelectorList) {
////			if (!StringUtils.hasText(item)) {
////				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
////			}
////		}
//		
//		// List�ন�r��
//		// 1. questionTitle
//		String qTitleStr = String.join(", ", qTitleList);  //��", "�h�s��(�B���|�����A��)
//		// 2. notNull
//		String notNullStr = String.join(", ", notNullList);
//		// 3. type
//		String typeStr = String.join(", ", typeList);
//		// 4. questionSelector
////		String qSelectorStr = String.join(", ", qSelectorList);
//		
//		
//		
//		// �s�W�ݨ����D
//		Question question = new Question();
//		question.setOutlineId(intOutlineId);
//		question.setQuestionTitle(qTitleStr); 
//		question.setNotNull(notNullStr);
//		question.setType(typeStr);
//		question.setQuestionSelector(resultStr);
//		
//		questionDao.save(question);
//		
//
//// 		if (!StringUtils.hasText(questionId)) {
////	    	return new BackResponse("�D�����~");
////	    }
//// 		if (!StringUtils.hasText(questionTitle)) {
////	    	return new BackResponse("���D���~");
////	    }
//// 		if (!StringUtils.hasText(notNull)) {
////	    	return new BackResponse("�O�_������~");
////	    }
//// 		if (!StringUtils.hasText(type)) {
////	    	return new BackResponse("�������~");
////	    }
//// 		if (!StringUtils.hasText(questionSelector)) {
////	    	return new BackResponse("�ﶵ���~");
////	    }
//
//		return new OutlineQuestionResponse(RtnCode.ADD_QUESTION_SUCCESS.getMessage());
//	}

	@Override
	// �ҽk�j�M�ݨ�(���D�B�}�l�ɶ��B�����ɶ�)
	public BackResponse search(OutlineRequest outlineRequest) {
		// ���X��J����T
		String titleKeyword = outlineRequest.getTitle();
		String startDate = outlineRequest.getStartDate();
		String endDate = outlineRequest.getEndDate();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Outline> outlineList = new ArrayList<>();
		
		// �p�G�Ҧ���쳣�O��
	    if (!StringUtils.hasText(titleKeyword) 
	    		&& !StringUtils.hasText(startDate) 
	    		&& !StringUtils.hasText(endDate)) {
			return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
		
		// 1 �u����J�ݨ����Dkeyword
		if (StringUtils.hasText(titleKeyword)
				&& !StringUtils.hasText(startDate)
				&& !StringUtils.hasText(endDate)) {
			outlineList = outlineDao.findByTitle(titleKeyword);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 1 �u����J�}�l�ɶ�
		if (!StringUtils.hasText(titleKeyword)
				&& StringUtils.hasText(startDate)
				&& !StringUtils.hasText(endDate)) {
			// �r����LocalDate
		    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
			outlineList = outlineDao.findByStartDate(localDateStart);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 1 �u����J�����ɶ�
		if (!StringUtils.hasText(titleKeyword)
				&& !StringUtils.hasText(startDate)
				&& StringUtils.hasText(endDate)) {
			// �r����LocalDate
		    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
			outlineList = outlineDao.findByEndDate(localDateEnd);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 2 ���Dkeyword�B�}�l�ɶ�
		if (StringUtils.hasText(titleKeyword)
				&& StringUtils.hasText(startDate)
				&& !StringUtils.hasText(endDate)) {
			// �r����LocalDate
		    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
			outlineList = outlineDao.findByTitleKeywordAndStartDate(titleKeyword, localDateStart);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 2 ���Dkeyword�B�����ɶ�
		if (StringUtils.hasText(titleKeyword)
				&& !StringUtils.hasText(startDate)
				&& StringUtils.hasText(endDate)) {
			// �r����LocalDate
		    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
			outlineList = outlineDao.findByTitleKeywordAndEndDate(titleKeyword, localDateEnd);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 2 �}�l�ɶ��B�����ɶ�
		if (!StringUtils.hasText(titleKeyword)
				&& StringUtils.hasText(startDate)
				&& StringUtils.hasText(endDate)) {
			// �r����LocalDate
		    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
		    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
			outlineList = outlineDao.findByStartDateAndEndDate(localDateStart, localDateEnd);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
//			// �P�_�}�l�ɶ��O�_�j�󵲧��ɶ�
//			if (localDateStart.isAfter(localDateEnd)) {
//				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}

		// 3 ���Dkeyword�B�}�l�ɶ��B�����ɶ�������J
		// �r����LocalDate
	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);  
	    outlineList = outlineDao.findByTitleKeywordAndStartDateAndEndDate(titleKeyword, localDateStart, localDateEnd);
		if (outlineList.isEmpty()) {
			return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
//		// �P�_�}�l�ɶ��O�_�j�󵲧��ɶ�
//		if (localDateStart.isAfter(localDateEnd)) {
//			return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//		}
	    
		return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
	}
	
	@Override
	// �d�ߩҦ��ݨ��j��
	public BackResponse getAll() {
		List<Outline> outlineList = outlineDao.findAll();
		if (outlineList == null) {
			return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
		
		return new BackResponse(outlineList, RtnCode.GET_ALL_SUCCESS.getMessage());
	}

	@Override
	// �d�߯S�w�ݨ�
	public OneDataResponse getOne(OutlineQuestionRequest outlineQuestionRequest) {
		int outlineId = outlineQuestionRequest.getOutlineId();
		
		Optional<Outline> opOutline = outlineDao.findById(outlineId);
		Optional<Question> opQuestion = questionDao.findById(outlineId);
		if (opOutline.isEmpty() 
				|| opQuestion.isEmpty()) {
			return new OneDataResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
		
		return new OneDataResponse(opOutline.get(), opQuestion.get(), RtnCode.GET_ONE_SUCCESS.getMessage());
	}


	
	
//	private BackResponse convertStrToLocalDate(String startDate, String endDate) {
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
//	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
//	    
//	    return new BackResponse(localDateStart, localDateEnd);
//	}

}
