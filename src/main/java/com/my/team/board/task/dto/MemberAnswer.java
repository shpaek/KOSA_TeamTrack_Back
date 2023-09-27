package com.my.team.board.task.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MemberAnswer {

	Integer teamMemberNo;
	Integer taskNo;
	List<Integer> answerList;
	
}
