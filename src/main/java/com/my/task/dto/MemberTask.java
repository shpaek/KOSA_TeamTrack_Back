package com.my.task.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MemberTask {

	private int hwScore;
	private String id;
	private int reviewScore;
	private Date submitDate;
	//private List<Integer> memberAnswerList;
	private String title;
	
}
