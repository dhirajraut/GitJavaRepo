import java.io.*;

public class TestDataStream
	{	public static void main(String[] args)
			{	String filepath="C:"+File.separator+"D. Chandra"+File.separator+"Java"+File.separator+"D.Chandra New"+File.separator+"ClassroomExercises";
			filepath=filepath+File.separator+"src"+File.separator+"IOManagement"+File.separator+"Data";
				
				String filename1="TestDataStreamFile.dat";
				
				try{	FileOutputStream fos = new FileOutputStream(filepath+File.separator+filename1);
						DataOutputStream dos = new DataOutputStream(fos);
						String datastring = Integer.toString(500);
						dos.writeInt(256);
						dos.writeLong(1000L);
						dos.writeUTF("Chandrashekhar");
						dos.writeUTF(datastring);
						dos.flush();
						
						fos.close();
						dos.close();
						FileInputStream fis=new FileInputStream(filepath+File.separator+filename1);
						DataInputStream dis = new DataInputStream(fis);
						System.out.println(dis.readInt());
						System.out.println(dis.readLong());
						System.out.println(dis.readUTF());
						System.out.println(dis.readUTF());
						 dis.close();
						 fis.close();
					}
				catch(IOException ioe)
					{	System.err.println("Error"+ioe); }
			}
	}
