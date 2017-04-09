class EmpFamilyBean07
	{	int empNo;
		int memberId;
		String memberName;
		String memberRelation;
		
		public EmpFamilyBean07(int no, int id, String name, String relation)
			{	empNo = no;
				memberId = id;
				memberName = name;
				memberRelation = relation;
			}

		public int getEmpNo()
			{	return empNo;	}

		public int getMemberId()
			{	return memberId;	}

		public String getMemberName()
			{	return memberName;	}

		public String getMemberRelation()
			{	return memberRelation;	}
		
		public String toString()
			{	return empNo+" "+memberId+" "+memberName+" "+memberRelation;	}
	}
