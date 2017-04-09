import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class ThreadTest6
	{	public static void main(String[] args)
			{	try {	PipedWriter pw = new PipedWriter();
						PipedReader pr = new PipedReader(pw);
						
						Pantry6 sheff = new Pantry6("PantryThread", pw);
						ServeTable6 waiter = new ServeTable6("ServiceTableThread", pr);
						
						sheff.start();
						Thread.sleep(2000);
						waiter.start();
					}
				catch (InterruptedException e)
					{}
				catch (IOException ie)
					{	System.out.println("PipeWriter/Reader problem.");	}
			}
	}

class Pantry6 extends Thread
	{	PipedWriter pw;
		Pantry6(String msg, PipedWriter pw)
			{ 	super(msg);
				this.pw=pw;				
			}

		public void run()
			{	String [] menu = { "Kachauri", "Batata Vada", "Idlee", "MungVada", "Bhajiya", "Allu Wadi", "Poha", "Upma", "Shrikhand", "Shira"};
				for(int i=0; i<menu.length; i++)
					{	System.out.println("\n"+getName()+" "+menu[i]+"\n");
						try {	pw.write(menu[i]+"\n");
								Thread.currentThread().sleep((long)(3000*Math.random()));
							}
						catch (IOException e)
							{	System.out.println("Problem in cooking.");}
						catch (InterruptedException ei)
							{}
					}
			}
	}

class ServeTable6 extends Thread
	{	PipedReader pr;
		ServeTable6(String msg, PipedReader pr)
			{	super(msg);
				this.pr = pr;
			}
		public void run()
			{	int  ch;
		
				System.out.print("\n"+getName()+" ");
				try {	while((ch=pr.read())!= -1)
							{	char chr = (char) ch;
								if (chr=='\n')
									System.out.print("\n"+getName()+" ");
								else
									System.out.print(chr);
							}
					}
				catch(IOException ioe)
					{	System.out.println("Problem in transport.");	}
			}
	}