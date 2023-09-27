package com.my.team.board.task.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TaskGrade {

	Integer teamMemberNo;
	Integer taskNo;
	int hwScore;
	int reviewScore;
	Date submitDate;
	
}
