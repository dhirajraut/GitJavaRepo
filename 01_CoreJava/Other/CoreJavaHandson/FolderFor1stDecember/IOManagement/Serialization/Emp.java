package Serialization;
/* The command level tool c:\jdk\lib>serialver -classpath c:\....  packname.classname
   gives us 
  	Serialization.Emp:  static final long serialVersionUID = 569889833423424233L;
   This is a serialized version number - A secure hash number calculated as fingre print from
   Class Name, Superinterfaces and members of a class.
   The ObjectInputStream at the time of de-serializing an object goes through following steps...
   1. Looks for a class to calculate version id from its recent fingure prints.
   2. De-serialize object and collects version ID from de-serialized information.
   3. If they matched, no exception is thrown and de-serialized object is considered as valid.
   4. If they are mismatched, an exception InvalidClassException is thrown. 
  */ 
import java.io.*;
public class Emp extends EmpBase //implements Serializable
{	
	int eno;
	String ename;
	int sal;
	transient int trans=10;
	static int stat=100;
	
	//public final static long serialVersionUID = -7964162467535886284L;
	public Emp()
		{	this(100, "Chandra", 5000, 90, 80);	}
	
	public Emp(int eno, String ename, int sal, int trans, int stat)
		{ 	super();
			this.eno=eno;
			this.ename=ename;
			this.sal=sal;
			this.trans = trans;
			this.stat = stat;
		}
	public void show()
		{	System.out.println("Base Field.  : "+baseField);
			System.out.println("Emp number   : "+eno);
			System.out.println("Emp name     : "+ename);
			System.out.println("Emp sal      : "+sal);
			System.out.println("Emp trans    : "+trans);
			System.out.println("Emp stat     : "+stat);
		}
	public void xyz() {}
	public static void main(String [] argv)
		{	Emp e = new Emp();
		}
}
