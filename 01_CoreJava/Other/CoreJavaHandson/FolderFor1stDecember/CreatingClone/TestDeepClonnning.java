class Piston1 implements Cloneable
	{ private int numRings=5; 
	  
		public void setParam(int n)
			{	numRings = n;	}
		
	  public Object clone() throws CloneNotSupportedException
		{ return super.clone();	}
	  
	  public String toString()
	  	{	return "Number Rings: "+numRings;	}
	}

class Engine1 implements Cloneable
	{ 	private String engName="Ford";
		private Piston1 piston;
	  
		public Engine1()
			{	piston = new Piston1();	}
		
		public void setParam(String engName, int numRings)
			{	this.engName = engName;
				piston.setParam(numRings);
			}
		public Object clone() throws CloneNotSupportedException
			{ Engine1 e = null;
			  Object o = super.clone();
			  e = (Engine1) o;
				  	
			  e.piston = (Piston1) piston.clone();
			  return e;
			}
		  
		  public String toString()
		  	{	return "Engine Make: "+engName+" "+piston;	}
	}

class Model implements Cloneable
	{	private String modelName="Ford Icon";
		private Engine1 engine;
		
		public Model()
			{	engine = new Engine1();	}
		
		public void setParam(String engName, int numRings, String modelName)
			{	engine.setParam(engName, numRings);
				this.modelName = modelName;
			}
		public Object clone() throws CloneNotSupportedException
			{	Model m = (Model) super.clone();
				m.engine = (Engine1) engine.clone();
				return m;
			}
		public String toString()
			{	return "Model Name: "+modelName+" "+engine;	}
	}
public class TestDeepClonnning
	{	public static void main(String [] argv)
			{	Model m1 = new Model();
				
				Model m2=null;
				try {	m2 = (Model) m1.clone();	} 
				catch (CloneNotSupportedException e)
					{	e.printStackTrace();	}
			
				m2.setParam("Mitshubishi", 6, "RoadKing");
				
				System.out.println("M1: "+m1);
				System.out.println("M2: "+m2);
			}
	}
