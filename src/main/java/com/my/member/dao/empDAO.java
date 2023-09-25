package com.my.member.dao;

import com.my.exception.FindException;
import com.my.member.dto.Emp;

public interface empDAO {
	
	Emp select(Integer id) throws FindException;

}
