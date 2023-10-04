package com.my.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	
	String id;				// 유저 아이디
	String pwd;				// 유저 비밀번호
	String nickname;		// 유저 닉네임
	String name;			// 유저 이름
	String birthday;		// 유저 생년월일
	String phone;			// 유저 번호
	String email;			// 유저 이메일
	Integer status;			// 유저 상태 -> 탈퇴: 0, 가입중: 1
	
} // end class
