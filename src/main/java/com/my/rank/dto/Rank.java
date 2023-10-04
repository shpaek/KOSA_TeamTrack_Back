package com.my.rank.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Data
@AllArgsConstructor @NoArgsConstructor
public class Rank {
	
	//Rank 기본 변수 
	Integer rankNo;
	Date rankDate;
	Integer rank;
	Double totalScore;
	String id;
	
	//Rank 계산을 위한 변수
//	private AttendanceDTO AttendanceDto;
//	private TaskDTO TaskDto;
//	private MemberScoreDTO MemberScoreDto;
//	private QnACommentDTO QnACommentDto;

}
