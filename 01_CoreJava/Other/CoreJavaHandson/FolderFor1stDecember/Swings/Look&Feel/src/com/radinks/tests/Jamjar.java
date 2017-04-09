package com.radinks.tests;

import java.util.*;
import java.util.jar.*;
import java.io.*;

public class Jamjar {

    public Jamjar() {
    }

	public static void main(String[] args) {
		useJarInput();
	}

	private static void useJarFile() {
		try {
			int bytes=0;

			JarFile jf = new JarFile("/tmp/junk/print.war");
			JarOutputStream jout = new JarOutputStream(new FileOutputStream("/tmp/junk/out.war"));

			Enumeration enum = jf.entries();

			while (enum.hasMoreElements()) {
				Object item = enum.nextElement();
				String entryName = item.toString();

				JarEntry je = jf.getJarEntry(entryName);
				JarEntry jeOut = new JarEntry(je);


				if(je.getCompressedSize() == 3409)
				{
					System.out.println("djfalkjfkld");
				}

				if(getDiff(je) < 1 || entryName.endsWith("zip") ||
				   entryName.endsWith("jar") || entryName.endsWith("bzip2") ||
				   entryName.endsWith("gz"))
				{
					System.out.println("aa");
					jeOut.setCompressedSize(je.getSize());
					jeOut.setMethod(je.STORED);
				}

				jout.putNextEntry(jeOut);
				InputStream in = jf.getInputStream(je);
				if(entryName.equals("WEB-INF/classes/com/lekana/lekana.properties"))
				{
					BufferedReader bin = new BufferedReader(new InputStreamReader(in));
					while(true)
					{
						String s = bin.readLine();
						if(s== null)
						{
							break;
						}
						System.out.print("\t");
						System.out.println(s);
						jout.write(s.getBytes());
					}
				}
				else
				{
					int iLim = 1024;
					byte[] b = new byte[iLim];
					while(true)
					{
						int c = in.read(b,0,iLim);

						if(c == -1)
						{
							break;
						}
						bytes +=c;
						jout.write(b,0,c);
					}
				}

				///System.out.print(je.getName());
				//System.out.print(" bytes =" + bytes);
				//System.out.println(" expected " + je.getSize());
				bytes=0;
				in.close();
				jout.closeEntry();

			}
		}
		catch (IOException ex) {

			ex.printStackTrace();
		}
	}

	private static void useJarInput() {
		try {
			int bytes=0;

			JarInputStream jin = new JarInputStream(new FileInputStream("/tmp/junk/print.war"));
			JarOutputStream jout = new JarOutputStream(new FileOutputStream("/tmp/junk/out.war"));

			while (true) {
				JarEntry je = jin.getNextJarEntry();
				if(je == null)
				{
					break;
				}
				JarEntry jeOut = new JarEntry(je);
				jeOut.setCompressedSize(-1);
				jout.putNextEntry(jeOut);

				int iLim = 1024;
				byte[] b = new byte[iLim];
				while(true)
				{
					int c = jin.read(b,0,iLim);
					if(c == -1)
					{
						break;
					}
					bytes +=c;
					jout.write(b,0,c);
				}

				System.out.print(je.getName());
				System.out.print(" bytes =" + bytes);
				System.out.println(" expected " + je.getSize());
				bytes=0;
				jout.closeEntry();
			}
			jin.close();
			jout.close();
		}
		catch (IOException ex) {

			ex.printStackTrace();
		}
	}

	private static long getDiff(JarEntry je)
	{
		if(je.getSize() == 0)
		{
			return 0;
		}
		else
		{
			return  ((je.getSize() - je.getCompressedSize())*100)/ je.getSize();
		}
	}
}