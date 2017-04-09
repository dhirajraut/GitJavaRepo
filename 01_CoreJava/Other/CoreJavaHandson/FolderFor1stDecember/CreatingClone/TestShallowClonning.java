
class ShallowClonning implements Cloneable
	{	int x;

		public ShallowClonning(int x)
			{	this.x = x;	}

		public String toString()
			{	return "X: "+x;	}
		
		protected void setX(int x)
			{	this.x = x;	}
		
		public Object clone() throws CloneNotSupportedException
			{	return super.clone();}
	}

public class TestShallowClonning
{	public static void main(String [] arg)
		{	ShallowClonning sc1 = new ShallowClonning(10);
			System.out.println("From 1 before clonning "+sc1);
			try {	ShallowClonning sc2 = (ShallowClonning) sc1.clone();
					sc2.setX(20);
					System.out.println("From 1 after clonning "+sc1);
					System.out.println("From 2 after clonning "+sc2);
				}
			catch (CloneNotSupportedException e)
				{	e.printStackTrace();	}
		}	
}