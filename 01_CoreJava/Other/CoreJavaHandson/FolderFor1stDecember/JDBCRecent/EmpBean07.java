
public class EmpBean07
	{	int empNo;
		String eName;
		float sal;
		
		public EmpBean07(int empNo, String eName, float sal)
			{	this.empNo = empNo;
				this.eName = eName;
				this.sal = sal;
			}
		
		public int getEmpNo()
			{	return empNo;	}

		public String getEName()
			{	return eName;	}

		public float getSal()
			{	return sal;	}
		
		public String toString()
			{	return empNo+" "+eName+" "+sal;	}
	}
