package Module13.randomaccess;

import java.io.*;

class RandomAccessFileDemo {
	RandomAccessFile raf;
	String emp;
	String [] empNo = new String[10];
	
	RandomAccessFileDemo(){
		try {
			raf = new RandomAccessFile("Details.txt","r");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void display(String empno){
		try {
			while(raf.getFilePointer()<raf.length()){
				empNo = raf.readLine().split(":");
				if(empno.equals(empNo[0])){
					System.out.println("employee found...\n"+empno);
					break;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	public static void main(String[] args) throws FileNotFoundException {
		RandomAccessFileDemo rafd = new RandomAccessFileDemo();
		rafd.display("7844");
	}
		 
		
}

