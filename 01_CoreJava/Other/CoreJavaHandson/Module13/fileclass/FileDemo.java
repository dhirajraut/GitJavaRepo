package Module13.fileclass;

import java.io.*;
import java.sql.Date;

class FileDemo {
	public static void main(String[] args) {
		File f=new File("abc.txt");
		try { 
			f.createNewFile() ;
		}
		catch (IOException e) {
			e.printStackTrace();  
		}
		System.out.println("Does the file exists ? "+f.exists());
		System.out.println("Is this a file ? "+f.isFile());
		System.out.println("Is this a directory ? "+f.isDirectory());
		System.out.println("The AbsolutePath is : "+f.getAbsolutePath());
		System.out.println("The file separtor : "+f.separator);
		System.out.println("The size of file : "+f.length());
		System.out.println("The file was last modified at : "+new Date(f.lastModified()));
		System.out.println("Is the file Readable ? "+f.canRead());
		System.out.println("Is the file Writable ? "+f.canWrite());
		System.out.println("Is the file Hidden ? "+f.isHidden());
	}
}


