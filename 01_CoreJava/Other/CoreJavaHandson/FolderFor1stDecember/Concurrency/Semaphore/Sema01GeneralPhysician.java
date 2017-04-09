
import java.util.concurrent.Semaphore;

public class Sema01GeneralPhysician
	{	public static final int NO_OF_TABLES = 1;
	
		public static void main(String[] argv)
			{	Doctor d = new Doctor();
				Semaphore reception = new Semaphore(NO_OF_TABLES, true);
				
				ThreadPatient01 p1 = new ThreadPatient01("Patient1", reception, d);
				ThreadPatient01 p2 = new ThreadPatient01("Patient2", reception, d);
				ThreadPatient01 p3 = new ThreadPatient01("Patient3", reception, d);
			
				p1.start();
				p2.start();
				p3.start();
			}
	}

class Doctor
	{	public String doctorsActivity(String patient)
			{	return "Doctor discussing with "+patient;	}
	}

class ThreadPatient01 extends Thread
	{	Semaphore sem;
		Doctor d;
		public ThreadPatient01(String nm, Semaphore sem, Doctor d)
			{	super(nm);
				this.sem = sem;
				this.d = d;
			}
		public void run()
			{	try	{	String patientsName = getName();
						System.out.println(patientsName+" is waiting in reception room...");
						sem.acquire();
						System.out.println(patientsName+" has got entry permition in Doctor's chember...");
						
						for(int i=0; i<5; i++)
							{	System.out.println(d.doctorsActivity(patientsName));
								sleep((long)Math.random()*1000);
							}
						System.out.println(patientsName+" is leaving doctor's Chember...");
						sem.release();
					}
				catch (InterruptedException e)
					{	e.printStackTrace();	}
			}
	}