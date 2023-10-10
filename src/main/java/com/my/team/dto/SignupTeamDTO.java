package com.my.team.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter @Setter @ToString
public class SignupTeamDTO {

	// 가입한 팀 테이블
	private Integer signupTeamNo; 		// 팀 번호
	private String signupId;			// 회원 아이디
	private Integer signupStatus;		// 가입 상태
	private String introduction;		// 팀 가입 자기소개

} // end class
