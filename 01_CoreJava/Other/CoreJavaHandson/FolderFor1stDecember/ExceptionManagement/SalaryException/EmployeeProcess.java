package SalaryException;

public class EmployeeProcess
	{	int empno;
   		String empname;
   		float basic;

   		public EmployeeProcess(int eno, String nm, float bas) throws SalaryException
			{ 	empno=eno;
				empname=new String(nm); 
				 if (bas<0)
						{NegativeSalaryException s = new NegativeSalaryException("Basic can not be negative.");
						  throw s;
						}
				 else
					 if (bas>20000)
						{	 SalaryExceedsException se = new SalaryExceedsException("Salary goes above.");
							throw se;
						}
					 else		
						 basic=bas; 
			}
   		
		public void showdata()
			{	System.out.println("Employee No   :"+empno);
			 	System.out.println("Employee Name :"+empname);
			 	System.out.println("Employee Basic:"+basic);
			}
	}
