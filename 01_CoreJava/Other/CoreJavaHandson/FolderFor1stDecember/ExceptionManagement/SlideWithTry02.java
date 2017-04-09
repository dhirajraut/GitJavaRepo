import ioservices.MyIOService;

import java.io.IOException;

public class SlideWithTry02
	{	public static void main(String[] args)
			{	boolean flag=true;
				int n;
				while(flag)
					{	System.out.println("Enter any number:");
						try {	n=MyIOService.getInt();
								int p = 5/n;
								System.out.println("Value of p:"+p);
							}
						catch (NumberFormatException e)
							{	//e.printStackTrace();
								System.out.println("Number format wrong.");
								continue;
							}
						catch (IOException e)
							{	System.out.println("IO wrong.");
								return;
							}
						catch (ArithmeticException ae)
							{	//e.printStackTrace();
								System.out.println("Do not enter zero.");
								continue;
							}
						break;
					}
			}
	}

