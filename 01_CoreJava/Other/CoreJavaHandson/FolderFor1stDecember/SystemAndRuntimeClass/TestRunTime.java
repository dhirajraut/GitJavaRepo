import java.io.*;
public class TestRunTime
	{	public static void main(String[] args) throws IOException
			{	Runtime rt = Runtime.getRuntime();
				System.out.println("Free Memory of VM                     :"+rt.freeMemory());
				System.out.println("Maximum memory VM can attempt        :"+rt.maxMemory());
				System.out.println("Total memory of VM                    :"+rt.totalMemory());
				rt.gc();
				System.out.println("Free Memory of VM after calling gc()  :"+rt.freeMemory());
				try {
						rt.exec("d:\\d.chandra\\turboc++\\bin\\tc.exe");
					}
				catch (IOException ie)
					{System.err.println(ie);}
			}
	}
