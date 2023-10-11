package com.my.customer.service;

import com.my.customer.dto.CustomerDTO;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface CustomerService {
	
	/**
	 * 회원가입을 한다
	 * @param customerDTO 회원이 작성한 고객데이터
	 * @throws AddException db와 연결 실패시 예외 발생
	 */
	public void singup(CustomerDTO customerDTO) throws AddException;
	
	/**
	 * 로그인을 한다
	 * @param db에 저장된 id값
	 * @param pwd db에 저장된 pwd값
	 * @throws FindException db에 id와 pwd에 해당하는 값이 없을시 예외 발생
	 */
	// 로그인
	public void login(String id, String pwd) throws FindException;
	
	/**
	 * 아이디 중복확인을 한다
	 * @param id db에 저장된 id값
	 * @throws FindException 아이디에 해당하는 고객이 존재하지 않으면 FindException 발생 
	 */
	// 아이디 중복체크
	public void idDubCheck(String id) throws FindException;

} // end class
