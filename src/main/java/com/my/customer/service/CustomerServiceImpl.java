package com.my.customer.service;

import com.my.customer.dao.CustomerDAO;
import com.my.customer.dao.CustomerDAOImpl;
import com.my.customer.dto.CustomerDTO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDAO customerDAO;
	
	private static CustomerServiceImpl  service = new CustomerServiceImpl();
	
	private CustomerServiceImpl() {
		customerDAO = new CustomerDAOImpl();
	}
	
	public static CustomerService getInstance() {
		return service;
	}

	@Override
	public void singup(CustomerDTO customerDTO) throws AddException {
		
		customerDAO.create(customerDTO);
		
	} // signup

	@Override
	public void login(String id, String pwd) throws FindException {
		
		try {
			
			CustomerDTO dto = customerDAO.selectById(id);
			
			// 입력한 비밀번호와 db에 저장되어있는 비밀번호가 다르면 예외 발생
			if(!dto.getPwd().equals(pwd)) {
				throw new FindException();
			} // if
			
			System.out.println("로그인 성공");
			
		} catch(FindException e) {
			System.out.println("로그인 실패");
		} // try-catch
		
	} // login

	@Override
	public void idDubCheck(String id) throws FindException {
		
		customerDAO.selectById(id);
		
	} // idDbuCheck
	
	// ===========================  db와 연결 테스트 ========================
	
//	public static void main(String[] args) {
//		
//		CustomerService servcie = new CustomerServiceImpl();
//		
//		try {
//			servcie.login("test01", "test01");
//			System.out.println(" 로그인 성공 ^-^b ");
//			
//		} catch (FindException e) {
//			System.out.println(" 로그인 실패 ㅠㅠ ");
//
//		} // try-catch
//		
//	} // main(test)
	
	// ---- 원희 ----
	
	@Override
	public CustomerDTO findById(String id) throws FindException{
		return customerDAO.selectById(id);
	}
	
	@Override
	public void nicknameDupChk(String nickname) throws FindException{
		customerDAO.selectByNickname(nickname);
	}
	
	@Override
	public void modifyNickname(String id, String nickname) throws ModifyException{
		customerDAO.updateNickname(id, nickname);
	}
	
	@Override
	public void modifyMyInfo(String id, CustomerDTO customer) throws ModifyException{
		customerDAO.updateCustomerAll(id, customer);
	}
	
	@Override
	public void deleteAccount(String id) throws ModifyException{
		customerDAO.updateCustomerStatus(id);
	}

} // end class
