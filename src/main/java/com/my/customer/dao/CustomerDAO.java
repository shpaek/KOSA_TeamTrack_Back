package com.my.customer.dao;

import com.my.customer.dto.CustomerDTO;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface CustomerDAO {
	
	/**
	 * 회원을 추가한다
	 * @param customerDTO 고객객체
	 * @throws AddException DB와의 연결 실패 또는 제약조건에 위반시 예외 발생
	 */
	void create(CustomerDTO customerDTO) throws AddException;
	
	/**
	 * id에 해당하는 고객을 검색한다
	 * @param id
	 * @return db에 저장된 고객객체를 반환
	 * @throws FindException 아이디에 해당하는 고객 객체가 없거나 DB연결 실패시 예외 발생
	 */
	CustomerDTO selectById(String id) throws FindException;

}
