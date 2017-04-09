package com;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;



public class StudentMain {
	
	
	public static void displayData(StudentData sd)
	{
		System.out.println("Name:-" + sd.getFirstName()+" "+sd.getLastName() );
		System.out.println("Address:- " + sd.getAddress());
	}
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("C:\\Tanay\\SprigPractice\\DependencyInjection\\com\\studentapp.xml"));
		Student stu = (Student) factory.getBean("student");
		displayData(stu.getStudentData());
	}
	
}
