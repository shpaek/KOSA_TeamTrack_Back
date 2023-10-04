package com.my.team.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class TeamDTO {

	// 팀 테이블
	private int teamNo;
	private String teamName;
	private String leaderId;
	private String studyType;
	private String onOffLine;
	private Integer joinMember;
	private int maxMember;
	private String startDate;
	private String endDate;
	private String briefInfo;
	private String teamInfo;
	
	// 팀원 테이블
	private String id;					// 회원 아이디
	private String nickname;			// 닉네임
	private Date joinDate;				// 팀가입일
	private Integer attendence;			// 출석수
	private Integer rankSum;			// 개인랭킹 누적합
	private Integer bestRank;			// 개인랭킹 최고순위
	private Integer status;				// 회원 상태
	
	// 출석부 테이블
	private Date attendanceDate; 		// 출석일자
	private String attendenceId; 		// 회원 아이디
	
	// 가입한 팀 테이블
	private Integer signupTeamNo; 		// 팀 번호
	private String signupId;			// 회원 아이디
	private Integer signupStatus;		// 가입 상태
	private String introduction;		// 팀 가입 자기소개
	
} // end class
