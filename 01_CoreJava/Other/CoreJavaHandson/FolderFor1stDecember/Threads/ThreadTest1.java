// This is for demonstrating simple threads extending Thread class

import java.lang.Math;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.lang.System;

class mythread1 extends Thread
	{ 	String senten[]={"Pragati", "Softwares", "Pvt.", "Ltd.", "302", "Lokcenter","Marol"};
		mythread1(String st)
 		{ super(st); } 
		
		public void run()
			{ 	for (int i=0; i<senten.length; i++)
					{ 	System.out.println(getName()+senten[i]);
						randomWait();
					}
			}
		
		void randomWait()
			{ try {
					sleep((long)((3000)*Math.random()));
				  }
			  	catch(InterruptedException x)
			  		{ System.out.println("Interrupted"); }
			}
	}
public class ThreadTest1
	{	public static void main(String[] args)
		{	mythread1 thread1 = new mythread1("Thread1:");
			mythread1 thread2 = new mythread1("Thread2:");
			
			thread1.start();
			thread2.start(); 
			
			System.out.println("abvc");
		}
	}
