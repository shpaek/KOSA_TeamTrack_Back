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
	private String id;
	private String nickname;
	private Date joinDate;
	private Integer attendence;
	private Integer rankSum;
	private Integer bestRank;
	private Integer status;
	
	// 출석부 테이블
	private Date attendanceDate; // 출석부 테이블의 출석일자
	private String attendenceId; // 출석부 테이블의 팀원 아이디
	
} // end interface
