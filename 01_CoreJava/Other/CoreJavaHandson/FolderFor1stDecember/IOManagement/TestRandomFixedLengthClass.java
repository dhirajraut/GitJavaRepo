
import java.io.*;

class record
	{	int rn;			// 4
		String nm;		// 10
		float percent;	// 4
		
		public record()
			{}
		public record(int rn, String nm, float percent)
			{	this.rn=rn;
				this.nm = convertStringToStringFixedLength(nm);
				this.percent=percent;
			}
		public String toString()
			{	return(rn+" "+nm+" "+percent);	}
		public static int getSize()
			{	return(20);	}
		//public void setSize(int s)
			//{	size=s; }
		static String convertStringToStringFixedLength(String nm)
			{	StringBuffer sb = new StringBuffer(nm);
				for(int i=0; i<10-nm.length();i++)
					sb.append(" ");
				if (sb.length()>10)
					sb.setLength(10);
				return(sb.toString());
			}
	}
class operateRecord
	{	RandomAccessFile raf;
		
		public operateRecord(String filepath, String filename) throws IOException
			{	File f = new File(filepath+File.separator+filename);
				raf = new RandomAccessFile(f, "rw");
			}
		public void setFileBegin() throws IOException
			{	raf.seek(0);	}
		public void writeRecord(record rc) throws IOException
			{	raf.writeInt(rc.rn);
				raf.writeUTF(rc.nm);
				raf.writeFloat(rc.percent);
				System.out.println("Size of record in file:"+raf.getFilePointer());
			}
		public void finalize() throws Throwable
			{	raf.close();
				super.finalize();
			}
		public record readRandomRecord(long posi) throws IOException
			{	record rc=null;
				if (posi<(raf.length()-record.getSize()))
					{	raf.seek(posi);
						rc = new record(raf.readInt(),raf.readUTF(), raf.readFloat());
					}
				return(rc);
			}
		public record readRollRecord(int rollno) throws IOException
			{	return(readRandomRecord((long)((rollno-101)*record.getSize())));	}
		
		public void displayRecords() throws IOException
			{	setFileBegin();
				record rc;
				for(int i=0; (rc = readRandomRecord(i))!=null ; i+=record.getSize())
					{	System.out.println(rc);	}
			}
	}
public class TestRandomFixedLengthClass
	{	public static void main(String[] args) throws IOException
			{	//D:\D.Chandra New\ClassroomExercises\src\IOManagement\Data
				String filepath="C:"+File.separator+"D. Chandra"+File.separator+"Java"+File.separator+"D.Chandra New"+File.separator+"ClassroomExercises";
				filepath=filepath+File.separator+"src"+File.separator+"IOManagement"+File.separator+"Data";
				
				String filename="TestRandomFixFile.dat";
				operateRecord or = new operateRecord(filepath, filename);
				
				or.writeRecord(new record(101, "Ameya", 68.5f));
				or.writeRecord(new record(102, "Amit", 48.5f));
				or.writeRecord(new record(103, "xxxxxxxxxx", 48.5f));
				or.writeRecord(new record(104, "xxxxxxxxxxYYY", 48.5f));
				or.writeRecord(new record(105, "Asit", 68.5f));
				
				or.displayRecords();
				// Pickup records Randomly
				System.out.println(or.readRollRecord(104));
			}
	}
