package Externalization;

import java.io.*;
public class EmpE implements Externalizable
	{
		int eno;
		String ename;
		float sal;
		transient int trans;
		static int stat;
	
		public EmpE() {}
		public EmpE(int eno, String ename, float sal, int trans, int stat)
			{	this.eno=eno;
				this.ename=ename;
				this.sal=sal;
				this.trans = trans;
				this.stat = stat;
			}
		
		public void show()
			{	System.out.println("Emp number   : "+eno);
				System.out.println("Emp name     : "+ename);
				System.out.println("Emp sal      : "+sal);
				System.out.println("Emp trans    : "+trans);
				System.out.println("Emp stat     : "+stat);
			}
		
		public void writeExternal(ObjectOutput out) throws IOException
			{	out.writeInt(eno);
				out.writeUTF(ename);
				out.writeFloat(sal);
				out.writeInt(trans);
				out.writeInt(stat);
			}
		
		public void readExternal(ObjectInput in) throws IOException
			{	this.eno = in.readInt();
				this.ename = in.readUTF();
				this.sal = in.readFloat();
				this.trans = in.readInt();
				this.stat = in.readInt();
			}
}

