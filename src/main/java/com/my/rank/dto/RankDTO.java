package com.my.rank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Data
@AllArgsConstructor @NoArgsConstructor
public class RankDTO {
	
	//Rank 기본 변수 
	private Integer rankNo;
	private String rankDate;
	private Integer rank;
	private Double totalScore;
	private String id;
	
	//랭킹 리스트에 필요한 회원 정보
	private String nickname;
	private String profile;
//	private AttendanceDTO AttendanceDto;
//	private TaskDTO TaskDto;
//	private MemberScoreDTO MemberScoreDto;
//	private QnACommentDTO QnACommentDto;

}
