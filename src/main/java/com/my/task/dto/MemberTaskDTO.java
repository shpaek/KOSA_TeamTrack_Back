package com.my.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MemberTaskDTO {

	private int hwScore;
	private String id;
	private int reviewScore;
	private String submitDate;
	//private List<Integer> memberAnswerList;
	private String title;
	
}
