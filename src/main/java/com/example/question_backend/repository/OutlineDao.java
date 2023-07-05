package com.example.question_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.question_backend.entity.Outline;

@Repository
public interface OutlineDao extends JpaRepository<Outline, Integer>{
	
	// 搜尋1 只有輸入問卷標題keyword
	public List<Outline> findByTitle(String titleKeyword);
	
	// 搜尋1 只有輸入開始時間
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.start_date >= :startDate ", nativeQuery = true)
	public List<Outline> findByStartDate(@Param("startDate") LocalDate startDate);
	
	// 搜尋1 只有輸入結束時間
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.end_date <= :endDate ", nativeQuery = true)
	public List<Outline> findByEndDate(@Param("endDate") LocalDate endDate);
	
	// 搜尋2 標題keyword、開始時間
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.title LIKE %:title% "
            + "AND o.start_date >= :startDate ", nativeQuery = true)
	public List<Outline> findByTitleKeywordAndStartDate(@Param("title") String titleKeyword,
	                                          			@Param("startDate") LocalDate startDate);
	
	// 搜尋2 標題keyword、結束時間
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.title LIKE %:title% "
            + "AND o.end_date <= :endDate", nativeQuery = true)
	public List<Outline> findByTitleKeywordAndEndDate(@Param("title") String titleKeyword,
	                                          		  @Param("endDate") LocalDate endDate);
	
	// 搜尋2 開始時間、結束時間
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.start_date >= :startDate "
            + "AND o.end_date <= :endDate ", nativeQuery = true)
	public List<Outline> findByStartDateAndEndDate(@Param("startDate") LocalDate startDate,
	                                          	   @Param("endDate") LocalDate endDate);

	// 搜尋3 標題keyword、開始時間、結束時間都有輸入
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.title LIKE %:title% "
            + "AND o.start_date >= :startDate "
            + "AND o.end_date <= :endDate ", nativeQuery = true)
	public List<Outline> findByTitleKeywordAndStartDateAndEndDate(@Param("title") String titleKeyword,
	                                          				 	  @Param("startDate") LocalDate startDate,
	                                          				 	  @Param("endDate") LocalDate endDate);
	

	
	

	
}
