 import java.io.*;
 import java.util.Date;

 // exists(), length(), lastModified(), list(), renameTo(), delete(), isDirectory(), isFile(), 
public class TestFileClass
	{	public static void main(String[] args)
			{	//C:\D. Chandra\Java\D.Chandra New\ClassroomExercises\src\IOManagement
				String filepath="C:"+File.separator+"D. Chandra"+File.separator+"Java"+File.separator+"D.Chandra New"+File.separator+"ClassroomExercises";
				filepath=filepath+File.separator+"src"+File.separator+"IOManagement";
				File fExists = new File(filepath+File.separator+"TestFileClass.java");

				System.out.println("Is "+fExists.getName()+" file exists?"+fExists.exists());
				
				System.out.println("Length of "+fExists.getName()+" is:"+fExists.length());
				
				System.out.println("Absolute path of "+fExists.getName()+" is:"+fExists.getAbsolutePath());
				
				System.out.println("Parent path of "+fExists.getName()+" is:"+fExists.getParent());
				
				System.out.println("Date of Last Modification "+fExists.getName()+" is:"+new Date(fExists.lastModified()));

				// Test for Directory Listing
				File directorypath = new File(filepath);
				String [] directoryList;
				directoryList=directorypath.list();
				for(int i=0; i<directoryList.length; i++)
					System.out.println(directoryList[i]);
				System.out.println("\nTotal Files"+directoryList.length);
				
				// Test renameTo() and delete() on existing file.
				//D:\D.Chandra New\ClassroomExercises\src\IOManagement\Data\abc.txt
				//filepath="D:\\D.Chandra New\\ClassroomExercises\\src\\IOManagement\\Data\\";
				File fileToOperate1 = new File(filepath+"pqrFileRenamed.txt");
				File fileToOperate2 = new File(filepath+"abcFileRenamed.txt");
				if (fileToOperate1.exists()&& fileToOperate1.isFile())
					fileToOperate1.renameTo(fileToOperate2);
				else
					if (fileToOperate2.exists()&& fileToOperate2.isFile())
						fileToOperate2.renameTo(fileToOperate1);
					else
						System.out.println("For Renaming, file does not exists.");
				dir(filepath, "Renameing.");
				/* Testing Delete */
				// Confirm whether abcFileDelete.txt is present.  If not copy abcFileDelete.bak
				//	with the new name abcFileDelete.txt
				File fileToOperate = new File(filepath+"abcFileDelete.txt");
				if (fileToOperate.exists()&& fileToOperate.isFile())
					{	fileToOperate.delete();
						System.out.println("File is successfully deleted");	
					}
				else
					System.out.println("File is not deleted or does not exist.");
				dir(filepath, "Deleting");
				// FILTER : To display directory and file with filter
				dirWithFilter(filepath, "FilterChecking");
			}
		public static void dir(String filepath, String operation)
			{	File directorypath = new File(filepath);
				if (!directorypath.isDirectory())
					{	System.out.println("Wrong Directory Name....");
						return;
					}
				try {
						String [] directoryList=directorypath.list();
						
						for(int i=0; i<directoryList.length; i++)
							{	directorypath = new File(filepath+"\\"+directoryList[i]);
								if (directorypath.isDirectory())
									System.out.println("Dir:"+directoryList[i]);
								else
									System.out.println("Fil:"+directoryList[i]);
							}
						System.out.println("\nTotal Files"+directoryList.length);
					}
				catch (NullPointerException npe)
					{	System.out.println("Null Pointer Problem at "+operation);	}
			}
		public static void dirWithFilter(String filepath, String operation)
			{	File directPath = new File(filepath);
				if (!directPath.isDirectory())
					{	System.out.println("Wrong Directory Name.");
						return;
					}
				
				// Directory list with Filters
				// filepath is a string variable storing path to Data directory
				try {	FilenameFilter only = new OnlyExt("bak");
						String [] directoryList=directPath.list(only);
						for(int i=0; i<directoryList.length; i++)
							{	System.out.println(directoryList[i]);	}
					}
				catch (NullPointerException npe)
					{	System.out.println("Null Pointer Problem at "+operation);	}
			}
	}
class OnlyExt implements FilenameFilter
	{	String ext;
		OnlyExt(String ext)
			{	this.ext = "."+ext;	}
		public boolean accept(File dir, String name)
			{	return name.endsWith(ext);	}
	}
