package com.my.team.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TeamDAOImpl implements TeamDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	public TeamDAOImpl() {
		
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		
		try {

			inputStream = Resources.getResourceAsStream(resource);

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch

	} // constructor
	
//	---------------------------------------------------------------------------------
	
	
} // end class

