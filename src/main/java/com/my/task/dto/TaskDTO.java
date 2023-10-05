package com.my.task.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TaskDTO {

	private Integer taskNo;
	private String id;
	private Date dueDate1;
	private Date dueDate2;
	private String title;
	//private String type;
	private String regDate;
	private String endDate;
	private double avgReviewscore;
	//private List<Integer> answerList;
	
	// rank 산정에 필요한 변수
	private Double totalReviewscore;	//리뷰 점수 총점
	private Integer monthlyTaskNum;		//월별 과제 개수
	private Integer month;				//월
	
	
//	public Task(Integer taskNo, String id, String title) {
//		this.taskNo=taskNo;
//		this.id=id;
//		this.title=title;
//	}
	
}
