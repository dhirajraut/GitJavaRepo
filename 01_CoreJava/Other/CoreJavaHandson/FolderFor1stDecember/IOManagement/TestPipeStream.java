import java.io.*;

public class TestPipeStream
	{	static PipedWriter pw;
		static PipedReader pr;
		
		public static void main(String[] args) throws IOException
			{	pw=new PipedWriter();
				pr=new PipedReader(pw);
				WritingThread wt = new WritingThread("WritingThread");
				ReadingThread wr = new ReadingThread("ReadingThread");
				wt.start();
				try	{ Thread.sleep(2000); }
				catch(InterruptedException e)
					{}
				wr.start();
			}
	}
class WritingThread extends Thread
	{	WritingThread(String msg)
			{ 	super(msg);			}
		
		public void run()
			{	try {	
						System.out.println("I am big brother.");
						System.out.println("Chhotu will pickup the data I am sending.");
						for(int i=0; i<15; i++)
							{	String str=getName()+" A:"+i+'\n';
								System.out.print(str);
								TestPipeStream.pw.write(str);
							}
					//TestPipeStream.str=getName()+" A"+i+'\n';
					}
				catch(IOException ioe)
					{	System.err.println("Error1"+ioe);	}
			}
	}
class ReadingThread extends Thread
	{	ReadingThread(String msg)
			{	super(msg);		}
		public void run()
			{	int  ch;
				try {	System.out.println("I am Reading Thread.  Now let me begin");
						System.out.println("reading of data written by big brother.");
						while((ch=TestPipeStream.pr.read())!= -1)
						//while((ch=TestPipeStream.str).equalsIgnoreCase(""))
								{	System.out.print((char) ch);	}
					}
				catch(IOException ioe)
					{	}
			}
	}

