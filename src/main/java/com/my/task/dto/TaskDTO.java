package com.my.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TaskDTO {

	private Integer taskNo;
	private String id;
	private String dueDate1;
	private String dueDate2;
	private String title;
	//private String type;
	private String regDate;
	private String endDate;
	private double avgReviewscore;
	//private List<Integer> answerList;
	
}
