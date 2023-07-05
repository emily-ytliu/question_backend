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
	
	// �j�M1 �u����J�ݨ����Dkeyword
	public List<Outline> findByTitle(String titleKeyword);
	
	// �j�M1 �u����J�}�l�ɶ�
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.start_date >= :startDate ", nativeQuery = true)
	public List<Outline> findByStartDate(@Param("startDate") LocalDate startDate);
	
	// �j�M1 �u����J�����ɶ�
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.end_date <= :endDate ", nativeQuery = true)
	public List<Outline> findByEndDate(@Param("endDate") LocalDate endDate);
	
	// �j�M2 ���Dkeyword�B�}�l�ɶ�
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.title LIKE %:title% "
            + "AND o.start_date >= :startDate ", nativeQuery = true)
	public List<Outline> findByTitleKeywordAndStartDate(@Param("title") String titleKeyword,
	                                          			@Param("startDate") LocalDate startDate);
	
	// �j�M2 ���Dkeyword�B�����ɶ�
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.title LIKE %:title% "
            + "AND o.end_date <= :endDate", nativeQuery = true)
	public List<Outline> findByTitleKeywordAndEndDate(@Param("title") String titleKeyword,
	                                          		  @Param("endDate") LocalDate endDate);
	
	// �j�M2 �}�l�ɶ��B�����ɶ�
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.start_date >= :startDate "
            + "AND o.end_date <= :endDate ", nativeQuery = true)
	public List<Outline> findByStartDateAndEndDate(@Param("startDate") LocalDate startDate,
	                                          	   @Param("endDate") LocalDate endDate);

	// �j�M3 ���Dkeyword�B�}�l�ɶ��B�����ɶ�������J
	@Query(value = 
			"SELECT * FROM Outline o "
            + "WHERE o.title LIKE %:title% "
            + "AND o.start_date >= :startDate "
            + "AND o.end_date <= :endDate ", nativeQuery = true)
	public List<Outline> findByTitleKeywordAndStartDateAndEndDate(@Param("title") String titleKeyword,
	                                          				 	  @Param("startDate") LocalDate startDate,
	                                          				 	  @Param("endDate") LocalDate endDate);
	

	
	

	
}
