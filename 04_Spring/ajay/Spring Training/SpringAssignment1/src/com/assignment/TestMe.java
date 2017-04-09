package com.assignment;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestMe {
public static void main(String[] args) {
	
	ApplicationContext context=(ApplicationContext) new ClassPathXmlApplicationContext("services.xml");
	Department dept=(Department) context.getBean("deptObj");
	System.out.println(dept.getDeptId());
	Employee emp=(Employee) dept.getEmps().get(1);
	System.out.println(emp.getEmpName());
	
}
}
