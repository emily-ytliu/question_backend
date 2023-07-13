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
	// 新增問卷
	public BackResponse add(OutlineQuestionRequest outlineQuestionRequest) {
		// 取出輸入的問卷大綱資訊
		String title = outlineQuestionRequest.getTitle();
		String startDate = outlineQuestionRequest.getStartDate();
		String endDate = outlineQuestionRequest.getEndDate();
		String description = outlineQuestionRequest.getDescription();
		
		// 判斷資料是否為空
		if (!StringUtils.hasText(title)
	    		|| !StringUtils.hasText(startDate)
	    		|| !StringUtils.hasText(endDate)
	    		|| !StringUtils.hasText(description)) {
	    	return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		
//		if (!StringUtils.hasText(title)) {
//    	return new BackResponse("問卷標題錯誤");
//    }
//		if (!StringUtils.hasText(startDate)) {
//    	return new BackResponse("問卷開始日期錯誤");
//    }
//		if (!StringUtils.hasText(endDate)) {
//    	return new BackResponse("問卷結束日期錯誤");
//    }
//		if (!StringUtils.hasText(description)) {
//    	return new BackResponse("問卷描述錯誤");
//    }
		
		// 字串轉LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);	
	    
	    // ========== question ========== 
		// 取出輸入的問卷問題
		String questionTitle = outlineQuestionRequest.getQuestionTitle();
		String notNull = outlineQuestionRequest.getNotNull();
		String type = outlineQuestionRequest.getType();
		String questionSelector = outlineQuestionRequest.getQuestionSelector();
		
		// 判斷資料是否為空
		if (!StringUtils.hasText(questionTitle)
	    		|| !StringUtils.hasText(notNull)
	    		|| !StringUtils.hasText(type)
	    		|| !StringUtils.hasText(questionSelector)) {
	    	return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
	    }
		
// 		if (!StringUtils.hasText(questionTitle)) {
//	    	return new BackResponse("標題錯誤");
//	    }
// 		if (!StringUtils.hasText(notNull)) {
//	    	return new BackResponse("是否必填錯誤");
//	    }
// 		if (!StringUtils.hasText(type)) {
//	    	return new BackResponse("類型錯誤");
//	    }
// 		if (!StringUtils.hasText(questionSelector)) {
//	    	return new BackResponse("選項錯誤");
//	    }
		
		// 字串轉成List
		// 1. questionTitle
		String qTitleAry[] = questionTitle.replace(" ", "").split(",");  // 去除掉所有空格，再用逗點切
		List<String> qTitleList = Arrays.asList(qTitleAry);  // Array轉成List
		// 2. notNull
		String notNullAry[] = notNull.replace(" ", "").split(",");
		List<String> notNullList = Arrays.asList(notNullAry);
		// 3. type
		String typeAry[] = type.replace(" ", "").split(",");
		List<String> typeList = Arrays.asList(typeAry);
		// 4. questionSelector
		// 字串轉成List
		String qSelectorAry[] = questionSelector.replace("'", "").split(", ");  // 去除單引號，再用逗點空格切
		List<List<String>> resultList = new ArrayList<>();
		for (String aryItem : qSelectorAry) {
			String ary[] = aryItem.split(";");  // 用分號切
			List<String> itemList = Arrays.asList(ary);  // Array轉成List
			// List的每個項目不能是空
			for (String item : itemList) {
				if (!StringUtils.hasText(item)) {
					return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
				}
			}
			resultList.add(itemList);
		}
		// List轉成字串
		String resSelectorStr = resultList.toString();  //List轉成字串
		String resultStr = resSelectorStr.substring(1, resSelectorStr.length()-1);  //去掉中括號
		
		// List的每個項目不能是空
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
		
		// List轉成字串
		// 1. questionTitle
		String qTitleStr = String.join(", ", qTitleList);  //用", "去連接(且不會有中括號)
		// 2. notNull
		String notNullStr = String.join(", ", notNullList);
		// 3. type
		String typeStr = String.join(", ", typeList);
		
	    // 新增問卷大綱資訊
	    Outline outline = new Outline();
	    outline.setTitle(title);
	    outline.setDescription(description);
	    outline.setStartDate(localDateStart);
	    outline.setEndDate(localDateEnd);
	    
	    outlineDao.save(outline);
	    
		// 新增問卷問題
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
//	// 新增問卷大綱
//	public OutlineQuestionResponse addOutline(OutlineRequest outlineRequest) {
//		// 取出輸入的問卷大綱資訊
//		String title = outlineRequest.getTitle();
////		String status = outlineRequest.getStatus();
//		String startDate = outlineRequest.getStartDate();
//		String endDate = outlineRequest.getEndDate();
//		String description = outlineRequest.getDescription();
//		String questionId = outlineRequest.getQuestionId();
//		
//		// 判斷資料是否為空
//		if (!StringUtils.hasText(title)
////	    		|| !StringUtils.hasText(status)
//	    		|| !StringUtils.hasText(startDate)
//	    		|| !StringUtils.hasText(endDate)
//	    		|| !StringUtils.hasText(description)
//	    		|| !StringUtils.hasText(questionId)) {
//	    	return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//	    }
//		
////		// 判斷已經存在的outlineId不能重複儲存
////		boolean aiId = outlineDao.existsById(null);
////		if (!aiId) {
////			return new BackResponse(RtnCode.DATA_IS_EXISTED.getMessage());
////		}
//		
//		// 字串轉LocalDate
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
//	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);	
// 		
//	    // 新增問卷大綱資訊
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
////	    	return new BackResponse("問卷標題錯誤");
////	    }
//// 		if (!StringUtils.hasText(status)) {
////	    	return new BackResponse("問卷狀態錯誤");
////	    }
//// 		if (!StringUtils.hasText(startDate)) {
////	    	return new BackResponse("問卷開始日期錯誤");
////	    }
//// 		if (!StringUtils.hasText(endDate)) {
////	    	return new BackResponse("問卷結束日期錯誤");
////	    }
//// 		if (!StringUtils.hasText(description)) {
////	    	return new BackResponse("問卷描述錯誤");
////	    }
//		
//		return new OutlineQuestionResponse(outline, RtnCode.ADD_OUTLINE_SUCCESS.getMessage());
//	}

//	@Override
//	// 新增問卷問題
//	public OutlineQuestionResponse addQuestion(QuestionRequest questionRequest) {
//		// 取出輸入的問卷問題
//		String outlineId = questionRequest.getOutlineId();
//		String questionTitle = questionRequest.getQuestionTitle();
//		String notNull = questionRequest.getNotNull();
//		String type = questionRequest.getType();
//		String questionSelector = questionRequest.getQuestionSelector();
//		
//		// 判斷資料是否為空
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
//		// 字串轉成int
//		int intOutlineId = Integer.parseInt(outlineId);
//		
//		// 字串轉成List
//		// 1. questionTitle
//		String qTitleAry[] = questionTitle.replace(" ", "").split(",");  // 去除掉所有空格，再用逗點切
//		List<String> qTitleList = Arrays.asList(qTitleAry);  // Array轉成List
//		// 2. notNull
//		String notNullAry[] = notNull.replace(" ", "").split(",");
//		List<String> notNullList = Arrays.asList(notNullAry);
//		// 3. type
//		String typeAry[] = type.replace(" ", "").split(",");
//		List<String> typeList = Arrays.asList(typeAry);
//		// 4. questionSelector
////		String qSelectorAry[] = questionTitle.replace(" ", "").split(";");  // 用分號切
////		List<String> qSelectorList = Arrays.asList(qSelectorAry);
//		
//		// 4. questionSelector
//		// 字串轉成List
//		String qSelectorAry[] = questionSelector.replace("'", "").split(", ");  // 去除單引號，再用逗點空格切
//		
//		List<List<String>> resultList = new ArrayList<>();
//		for (String aryItem : qSelectorAry) {
//			String ary[] = aryItem.split(";");  // 用分號切
//			List<String> itemList = Arrays.asList(ary);  // Array轉成List
//			
//			// List的每個項目不能是空
//			for (String item : itemList) {
//				if (!StringUtils.hasText(item)) {
//					return new OutlineQuestionResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//				}
//			}
//			resultList.add(itemList);
//		}
//		// List轉成字串
//		String resSelectorStr = resultList.toString();  //List轉成字串
//		String resultStr = resSelectorStr.substring(1, resSelectorStr.length()-1);  //去掉中括號
//		
//		
////		// 建立Gson物件
////		Gson gson = new Gson();
////		// 使用TypeToken指定轉換目標的資料型態
////		TypeToken<List<List<String>>> token = new TypeToken<List<List<String>>>() {};
////		// 使用fromJson方法轉換成List
////		List<List<String>> allSelectorList = gson.fromJson(questionSelector, token.getType());
//		
//		
//		
//		// List的每個項目不能是空
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
//		// List轉成字串
//		// 1. questionTitle
//		String qTitleStr = String.join(", ", qTitleList);  //用", "去連接(且不會有中括號)
//		// 2. notNull
//		String notNullStr = String.join(", ", notNullList);
//		// 3. type
//		String typeStr = String.join(", ", typeList);
//		// 4. questionSelector
////		String qSelectorStr = String.join(", ", qSelectorList);
//		
//		
//		
//		// 新增問卷問題
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
////	    	return new BackResponse("題號錯誤");
////	    }
//// 		if (!StringUtils.hasText(questionTitle)) {
////	    	return new BackResponse("標題錯誤");
////	    }
//// 		if (!StringUtils.hasText(notNull)) {
////	    	return new BackResponse("是否必填錯誤");
////	    }
//// 		if (!StringUtils.hasText(type)) {
////	    	return new BackResponse("類型錯誤");
////	    }
//// 		if (!StringUtils.hasText(questionSelector)) {
////	    	return new BackResponse("選項錯誤");
////	    }
//
//		return new OutlineQuestionResponse(RtnCode.ADD_QUESTION_SUCCESS.getMessage());
//	}

	@Override
	// 模糊搜尋問卷(標題、開始時間、結束時間)
	public BackResponse search(OutlineRequest outlineRequest) {
		// 取出輸入的資訊
		String titleKeyword = outlineRequest.getTitle();
		String startDate = outlineRequest.getStartDate();
		String endDate = outlineRequest.getEndDate();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Outline> outlineList = new ArrayList<>();
		
		// 如果所有欄位都是空
	    if (!StringUtils.hasText(titleKeyword) 
	    		&& !StringUtils.hasText(startDate) 
	    		&& !StringUtils.hasText(endDate)) {
			return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
		
		// 1 只有輸入問卷標題keyword
		if (StringUtils.hasText(titleKeyword)
				&& !StringUtils.hasText(startDate)
				&& !StringUtils.hasText(endDate)) {
			outlineList = outlineDao.findByTitle(titleKeyword);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 1 只有輸入開始時間
		if (!StringUtils.hasText(titleKeyword)
				&& StringUtils.hasText(startDate)
				&& !StringUtils.hasText(endDate)) {
			// 字串轉LocalDate
		    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
			outlineList = outlineDao.findByStartDate(localDateStart);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 1 只有輸入結束時間
		if (!StringUtils.hasText(titleKeyword)
				&& !StringUtils.hasText(startDate)
				&& StringUtils.hasText(endDate)) {
			// 字串轉LocalDate
		    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
			outlineList = outlineDao.findByEndDate(localDateEnd);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 2 標題keyword、開始時間
		if (StringUtils.hasText(titleKeyword)
				&& StringUtils.hasText(startDate)
				&& !StringUtils.hasText(endDate)) {
			// 字串轉LocalDate
		    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
			outlineList = outlineDao.findByTitleKeywordAndStartDate(titleKeyword, localDateStart);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 2 標題keyword、結束時間
		if (StringUtils.hasText(titleKeyword)
				&& !StringUtils.hasText(startDate)
				&& StringUtils.hasText(endDate)) {
			// 字串轉LocalDate
		    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
			outlineList = outlineDao.findByTitleKeywordAndEndDate(titleKeyword, localDateEnd);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}
		
		// 2 開始時間、結束時間
		if (!StringUtils.hasText(titleKeyword)
				&& StringUtils.hasText(startDate)
				&& StringUtils.hasText(endDate)) {
			// 字串轉LocalDate
		    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
		    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
			outlineList = outlineDao.findByStartDateAndEndDate(localDateStart, localDateEnd);
			if (outlineList.isEmpty()) {
				return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
			}
//			// 判斷開始時間是否大於結束時間
//			if (localDateStart.isAfter(localDateEnd)) {
//				return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//			}
			
			return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
		}

		// 3 標題keyword、開始時間、結束時間都有輸入
		// 字串轉LocalDate
	    LocalDate localDateStart = LocalDate.parse(startDate, formatter);
	    LocalDate localDateEnd = LocalDate.parse(endDate, formatter);  
	    outlineList = outlineDao.findByTitleKeywordAndStartDateAndEndDate(titleKeyword, localDateStart, localDateEnd);
		if (outlineList.isEmpty()) {
			return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
//		// 判斷開始時間是否大於結束時間
//		if (localDateStart.isAfter(localDateEnd)) {
//			return new BackResponse(RtnCode.INCORRECT_INFO_ERROR.getMessage());
//		}
	    
		return new BackResponse(outlineList, RtnCode.SEARCH_SUCCESS.getMessage());
	}
	
	@Override
	// 查詢所有問卷大綱
	public BackResponse getAll() {
		List<Outline> outlineList = outlineDao.findAll();
		if (outlineList == null) {
			return new BackResponse(RtnCode.NO_SEARCH_INFO.getMessage());
		}
		
		return new BackResponse(outlineList, RtnCode.GET_ALL_SUCCESS.getMessage());
	}

	@Override
	// 查詢特定問卷
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
