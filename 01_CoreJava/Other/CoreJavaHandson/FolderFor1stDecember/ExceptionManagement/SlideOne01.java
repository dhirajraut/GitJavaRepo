import java.io.IOException;

import ioservices.MyIOService;

public class SlideOne01
	{	public static void main(String[] args) throws IOException
		  { boolean flag=true;
		    int n;
		    while(flag)
				{System.out.println("Number?:");
				  n=MyIOService.getInt();
				  int p = 5/n;
				  System.out.append("Value of p:"+p);
			   	}
		  }
	}
