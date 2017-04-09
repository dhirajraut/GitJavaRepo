package packthreadtest8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Collections;

public class ThreadCollectFromFile extends Thread
	{	Collection hs;
		String filePath;
		String fileNm;
		//Transact ts;
		public ThreadCollectFromFile(String msg, Collection hs, String filePath, String fileNm)
			{	super(msg);
				this.hs = hs;
				this.filePath = filePath;
				this.fileNm = fileNm;
			}
		
		public void run()
			{	File f = new File(filePath+File.separator+fileNm);
				System.out.println(f.getAbsolutePath());
				if (!f.isFile())
					System.out.println(fileNm+"File does not exists,");	// Throw an exception FileNotExistException
				else
					{	try	{	BufferedReader br = new BufferedReader(new FileReader(filePath+File.separator+fileNm));
								String line;
								while ((line=br.readLine())!=null)
							 		{ 	Transact ts = tokenizeLine(line);
							 			hs.add(ts);
							 		}
							}
						catch (FileNotFoundException fnf)
							{	}
						catch (IOException io)
							{	System.out.println("File ended.");	}
					}	
			}
		
		public Transact tokenizeLine(String line)
			{	StringTokenizer st = new StringTokenizer(line);
				try	{	int gateNo = Integer.parseInt(st.nextToken());
						int transNo = Integer.parseInt(st.nextToken());
						int empId = Integer.parseInt(st.nextToken());
						long dt = Long.parseLong(st.nextToken());
						String oper = st.nextToken();
						return new Transact(gateNo, transNo, empId, dt, oper);
					}
				catch(NumberFormatException nfe)
					{	System.out.println("Entry at "+st+" is wrong");}
				return null;
				}
	}
