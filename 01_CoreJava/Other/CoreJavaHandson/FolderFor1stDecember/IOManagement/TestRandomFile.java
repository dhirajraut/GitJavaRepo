 import java.io.*;
 
public class TestRandomFile
	{	static File fRandom;
		static RandomAccessFile raf;
		public static void main(String[] args)
			{	//C:\D. Chandra\Java\D.Chandra New\ClassroomExercises\src\IOManagement
				String filepath="C:"+File.separator+"D. Chandra"+File.separator+"Java"+File.separator+"D.Chandra New"+File.separator+"ClassroomExercises";
				filepath=filepath+File.separator+"src"+File.separator+"IOManagement"+File.separator+"Data";
				fRandom = new File(filepath+"TestRandomFileOutput.dat");
				
				boolean lunchover=false;
				try{	raf=new RandomAccessFile(fRandom, "rw");
						
						/*writeRec(50, 10000l, "abc");
						System.out.println("File pointer at Record:"+raf.getFilePointer());
						writeRec(60, 5000l, "xyz");
						System.out.println("File pointer at Record:"+raf.getFilePointer());*/
						raf.writeUTF("Chandrashekhar");	// Characters 14
						System.out.println("File pointer is after Chand:"+raf.getFilePointer());	//16
						raf.writeUTF("Mumbai");		// Characters 09
						System.out.println("File pointer is after Desh:"+raf.getFilePointer());	// 27
						raf.writeBoolean(lunchover);
						System.out.println("File pointer is after false"+raf.getFilePointer());	// 28
						raf.writeLong(1000l);
						System.out.println("File pointer is after long"+raf.getFilePointer());	// 34
						
						raf.seek(0L);
						raf.skipBytes(0);
						System.out.println(raf.readUTF());
						System.out.println("File pointer at Name:"+raf.getFilePointer());
						System.out.println(raf.readUTF());
						System.out.println("File pointer at Surname:"+raf.getFilePointer());
						System.out.println(raf.readBoolean());
						System.out.println("File pointer at lunchover:"+raf.getFilePointer());
						System.out.println(raf.readLong());
						System.out.println("File pointer at long:"+raf.getFilePointer());
						raf.seek(0L);
						System.out.println("File pointer at0:"+raf.getFilePointer());
						raf.seek(1L);
						System.out.println("File pointer at1:"+raf.getFilePointer());
						raf.seek(16L);
						System.out.println("File pointer at16:"+raf.getFilePointer());
						System.out.println(raf.readUTF());
						raf.seek(28L);
						System.out.println(raf.readLong());
						raf.close();
					}
				catch(IOException ioe)
					{ System.err.println("Error"+ioe); }
			}
		public static void writeRec(int abcno, long abclong, String abcname )
			{	try {	raf.writeInt(abcno);
						raf.writeLong(abclong);
						raf.writeUTF(abcname);
					}
				catch(IOException ioe)
					{ System.err.println("Error"+ioe); }
			}
	}

