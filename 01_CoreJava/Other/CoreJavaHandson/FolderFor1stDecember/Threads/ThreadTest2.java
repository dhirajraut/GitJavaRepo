// This is for demonstrating simple threads which implements Runnable interface

import java.lang.Math;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.lang.System;

 public class ThreadTest2
	{	public static void main(String[] args)
		{	Thread thread1 = new Thread(new mythread2("Thread1:"));
			Thread thread2 = new Thread(new mythread2("Thread2:"));
			thread1.start();
			thread2.start();
		}
	}
   class mythread2 implements  Runnable
	   { 	String senten[]={"Pragati", "Softwares", "Pvt.", "Ltd.", "302", "Lokcenter","Marol"};
	      	String nm;
	      	
			mythread2(String st)
				{ nm=st; }
			
			public void run()
				{ for (int i=0; i<senten.length; i++)
					{ 	randomWait();
						System.out.println(nm+senten[i]);
					}
				}
			
			void randomWait()
				{ try {
						Thread.currentThread().sleep((long)(3000*Math.random()));
					   }
				  	catch(InterruptedException x)
						 
				  	{ System.out.println("Interrupted"); }
				}
	   }