package com.my.customer.dao;

import com.my.customer.dto.Emp;
import com.my.exception.FindException;

public interface empDAO {
	
	Emp select(Integer id) throws FindException;

}
