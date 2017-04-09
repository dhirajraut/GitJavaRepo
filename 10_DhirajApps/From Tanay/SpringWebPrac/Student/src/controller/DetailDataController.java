package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentList;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.DetailDataService;

public class DetailDataController extends MultiActionController{
	DetailDataService detailDataService;
	StudentList studentList;


	public ModelAndView showDetail(HttpServletRequest req,HttpServletResponse res) {
		System.out.println("id is....."+Integer.parseInt(req.getParameter("stud_id")));
		int id = Integer.parseInt(req.getParameter("stud_id"));
		try {
			studentList=detailDataService.showDetail(id);
			System.out.println(studentList.getName());
			System.out.println(studentList.getAddress());
			System.out.println(studentList.getClass1());
			System.out.println(studentList.getGrade());
			System.out.println(studentList.getId());
			System.out.println(studentList.getComment());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("near return statement....");
		return new ModelAndView("student_details","studentList",studentList);
		
	}

	public void setDetailDataService(DetailDataService detailDataService) {
		this.detailDataService = detailDataService;
	}
	
	public void setStudentList(StudentList studentList) {
		this.studentList = studentList;
	}
}
