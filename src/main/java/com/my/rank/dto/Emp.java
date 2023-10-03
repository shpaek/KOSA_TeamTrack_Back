package com.my.rank.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Emp {
	
	Integer emp_id;
	String emp_name;
	String gender;
	Integer age;
	Date hire_date;
	String etc;

}
