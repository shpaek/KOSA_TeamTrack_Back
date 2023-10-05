package com.my.notice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class NoticeDTO {
	private Integer noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date regDate;
	private Integer mainStatus;
}
