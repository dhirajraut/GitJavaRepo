import java.util.concurrent.Semaphore;

public class Sema02Dentist
	{	public static final int MAX_OPERATION_CHAIR = 2;
	
		public static void main(String[] argv)
			{	Dentist d = new Dentist();
				Semaphore reception = new Semaphore(MAX_OPERATION_CHAIR, true);
			
				ThreadPatient02 p1 = new ThreadPatient02("Patient 1:", d, reception);
				ThreadPatient02 p2 = new ThreadPatient02("Patient 2:", d, reception);
				ThreadPatient02 p3 = new ThreadPatient02("Patient 3:", d, reception);
				
				p1.start();
				p2.start();
				p3.start();
			}
	}

class Dentist
	{	public void checkingPatient(String thrdNm)
			{	System.out.println("Doctor is operating patient "+thrdNm);	}
	}

class ThreadPatient02	extends Thread
	{	Dentist d;
		Semaphore reception;
		
		public ThreadPatient02(String nm, Dentist d, Semaphore reception)
			{	super(nm);
				this.d = d;
				this.reception = reception;
			}
		public void run()
			{	try {	String patientsName = getName();
						System.out.println(patientsName+" is waiting in reception room...");
						reception.acquire();
						System.out.println(patientsName+" has got entry permition in Doctor's chember...");
						for(int i=0; i<5; i++)
							{	d.checkingPatient(patientsName);
								sleep((long)Math.random()*3000);
							}
						System.out.println(patientsName+" is leaving doctor's Chember...");
						reception.release();
					}
				catch(InterruptedException ie)
					{	ie.printStackTrace();	}
			}
	}