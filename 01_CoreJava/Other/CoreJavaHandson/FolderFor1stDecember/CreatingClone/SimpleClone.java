class MyLink implements Cloneable
	{ int i;
	  MyLink(int i)
		{ this.i = i;	}
	  int getI()
		{ return(i);	}
	  void setI(int i)
		{ this.i = i; }
	  public String toString()
		{ return ("I:"+i); }
	  public Object clone() throws CloneNotSupportedException
		{ return super.clone();	}
	}
class MyString implements Cloneable
	{  String x;
	   MyLink i;
	   public MyString(String x, MyLink i)
		{ this.x = x;
		  //this.i = i;
		  this.i = new MyLink(i.getI());
		}

	   public Object clone() throws CloneNotSupportedException
		{ MyString m = (MyString) super.clone();
		  if (i!=null)
			m.i = (MyLink) i.clone(); 
		  return m;
		}

	   public void setXI(String x, int i )
		{ this.x = x;
		  this.i.setI(i);
		}

	   public String toString()
		{ return (x+"  "+i);}
	}

public class SimpleClone
	{ public static void main(String [] argv)
		{ MyLink ml = new MyLink(5);
		  String abc = "ABC";
		  // New Object Created
		  MyString m1 = new MyString(abc, ml);
		  
		  System.out.println("m1:"+m1); // ABC, 5
		  ml.setI(6);
		  System.out.println("m1:"+m1); // ABC, 5

		  // New Clone created
		  MyString m2 =  null;
		  try { m2 = (MyString) m1.clone();}
		  catch ( CloneNotSupportedException cs)
			{ System.out.println("MyString not cloned.");}

		  // New Reference created
		  MyString m3 = m1;

		  System.out.println("m1:"+m1);	// ABC, 5
		  System.out.println("m2:"+m2); // ABC, 5
		  System.out.println("m3:"+m3); // ABC, 5

		  // Change original m1 object
		  System.out.println("Change value of m1");
		  m1.setXI("PQR", 7);

		  System.out.println("m1 after change:"+m1);	// PQR, 7
		  System.out.println("m2 after change:"+m2);	// PQR, 7.... ABC, 5
  		  System.out.println("m3 after change:"+m3);	// PQR, 7
		}
	}