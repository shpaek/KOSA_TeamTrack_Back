package com.my.team.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter 
public class Team {
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
}
