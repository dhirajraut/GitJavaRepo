// This program itself demonstrating exceptions
import SalaryException.*;


public class Employee
	{	public static void main(String[] args)
			{	try{	EmployeeProcess e =new EmployeeProcess(100, "afasdfaf", +1200);
			  			EmployeeProcess e1=new EmployeeProcess(100,"bbbbbbbbbb", 29000);
			  			System.out.println("Employee e:");
			  			e.showdata();
			  			System.out.println("Employee e1:");
			  			e1.showdata();
					}
				catch(NegativeSalaryException ns)
					{ System.out.println(ns.getMessage()); }
				catch(SalaryExceedsException se)
					{ System.out.println(se.getMessage()); }
				catch (SalaryException s)
					{ System.out.println("Error in salary.");}
				finally
					{ System.out.println("I am at final.");}
			}
	}