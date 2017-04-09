import java.io.*;

public class TestFileStream
	{	public static void main(String[] args)
			{	//C:\D.Chandra\ClassroomExercises\src\IOManagement
				String filepath="C:"+File.separator+"D. Chandra"+File.separator+"Java"+File.separator+"D.Chandra New"+File.separator+"ClassroomExercises";
				filepath=filepath+File.separator+"src"+File.separator+"IOManagement"+File.separator+"Data";
				//String filepath="D:"+File.separator+"D.Chandra New"+File.separator+"ClassroomExercises"+File.separator;
								//filepath=filepath+"src"+File.separator+"IOManagement"+File.separator+"Data";
				String filename1="TestFileStreamInput.txt";
				String filename2="TestFileStreamOutput.bak";
				
				try{	FileInputStream fis = new FileInputStream(filepath+File.separator+filename1);
						FileOutputStream fos = new FileOutputStream(filepath+File.separator+filename2);
					
						//FileReader fis = new FileReader(filepath+File.separator+filename1);
						//FileWriter fos = new FileWriter(filepath+File.separator+filename2);
						
						int ch;
						while((ch=fis.read())!=-1)
							{	System.out.print((char) ch);
								fos.write(ch);
							}
						fis.close();
						fos.close();
					}
				catch(FileNotFoundException fnf)
					{}
				catch (IOException ioe)
					{	System.err.println("IO Error"+ioe);	}
			}
	}