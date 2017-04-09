// This is for demonstrating Thread priorities and their states.

/* This program demonstrates threads for priorities.
 * MultiThreading is feature of Operating System.
 * The sequence of execution of threads differ operating system wise
 * A windows operating system prefer PreEmptive/RoundRobin system.
 * The threads of same priority are executed in Time Sharing(Round-Robin).
 * For threads of differene priorityes, PreEmptive system is used...
 * The higher priority threads are given first chance for execution.
 * 		When these threads go for sleep or wait for resources, lower priority
 * 		threads are given chance for execution.
 * Starvation : When higher priority threads enguage resources and lower priority 
 * 		threads are kept waiting for resources for long time, the low priority threads
 * 		are said to be in Starvation state.
 * DeadLock : When one thread keeps one resource enguaged till it gets second resource
 * 		and another thread holds second resource waiting for first resource then the
 * 		situation may stretch for long time known as DeadLock.  Every operating system
 * 		has its own solution on deadlock.  The deadlock definitely is responsible for 
 * 		starvation of threads of low priority.
 */
import java.lang.Math;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.lang.System;
class mythread3 extends Thread
	{ String senten[]={"Pragati", "Softwares", "Pvt.", "Ltd.", "302", "Lokcenter","Marol"};
		mythread3(String st)
			{ super(st); }
		public void run()
			{ 	for (int i=0; i<senten.length; i++)
					{ 	
						System.out.println(getName()+senten[i]);
						randomWait();
					}
			}
		void randomWait()
		{ try {
				//sleep(0);
				sleep((long)(300*Math.random()));
			   }
		  	catch(InterruptedException x)
		  	{ System.out.println("Interrupted"); }
		}
	}

public class ThreadTest3
	{	public static void main(String[] args)
			{	mythread3 thread1 = new mythread3("Thread1:");
				mythread3 thread2 = new mythread3("Thread2:");
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				thread1.setPriority(6);
				thread2.setPriority(8);
				thread1.start();
				thread2.start();
				boolean threadT1IsAlive = true;
				boolean threadT2IsAlive = true;
				do { if (threadT1IsAlive && !thread1.isAlive())
						{	threadT1IsAlive=false;
							System.out.println("Thread 1 is dead");
						}
					 if (threadT2IsAlive && !thread2.isAlive())
					 	{	threadT2IsAlive=false;
						 	System.out.println("Thread 2 is dead");
					 	}
					} while (threadT1IsAlive || threadT2IsAlive );
				
				System.out.println("Thread Main says you goodbye");
			}
	}