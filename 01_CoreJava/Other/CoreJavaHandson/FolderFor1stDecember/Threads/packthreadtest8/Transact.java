package packthreadtest8;

import java.util.Calendar;
import java.util.Date;

public class Transact
	{	private int gateNo;
		private int transNo;
		private int empId;
		private long dt;
		private String oper;
		
		public Transact(int gateNo, int transNo, int empId, long dt, String oper)
			{	this.gateNo = gateNo;
				this.transNo = transNo;
				this.empId = empId;
				this.dt = dt;
				this.oper = oper;
			}
		
		public int getEmpId()
			{	return empId;	}
		
		public long getDt()
			{	return dt;	}

		public int getGateNo()
			{	return gateNo;	}

		public String getOper()
			{	return oper;	}

		public int getTransNo()
			{	return transNo;	}

		public String toString()
			{	Date dtt = new Date(dt);
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtt);
				
				String dtStr = cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR);
				String tmStr = cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
				return ("Emplyee Id:"+empId+" Date :"+dtStr+" Time:"+tmStr+" Operation:"+oper);
			}
	}
