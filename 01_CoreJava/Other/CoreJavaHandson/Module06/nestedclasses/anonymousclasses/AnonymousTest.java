package Module06.nestedclasses.anonymousclasses;

class Employee {
  	int empno;
  	float basic;
    Employee ( int empno, float basic) {
    	this.empno = empno;
    	this.basic = basic;
    }
    void showEmployee () {
    	System.out.println ("Number   : " + empno);
    	System.out.println ("Basic    : " + basic);
    }
}
public class AnonymousTest {
	public static void main (String [] args) {
    	Employee e1 = new Employee (10,5000.0f);
    	e1.showEmployee ();
    	Employee e2 = new Employee (11,6000.0f) {
    		float bonus = 500;
    		void showEmployee () {
    			super.showEmployee ();
    			System.out.println ("Bonus : " + bonus);
    		}
   		};       //end of anonymous inner class 
   		e2.showEmployee () ;
  	}
}


