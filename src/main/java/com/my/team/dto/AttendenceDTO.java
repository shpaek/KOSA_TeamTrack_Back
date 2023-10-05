package com.my.team.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") // 일단 보류
@Getter @Setter @ToString
public class AttendenceDTO {

	// 출석부 테이블
	private Date attendenceDate; 		// 출석일자
	private String attendenceId; 		// 회원 아이디
	
} // end class
