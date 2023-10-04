package com.my.notice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Notice {
	Integer noticeNo;
	String noticeTitle;
	String noticeContent;
	Date regdate;
	Integer mainStatus;
}
