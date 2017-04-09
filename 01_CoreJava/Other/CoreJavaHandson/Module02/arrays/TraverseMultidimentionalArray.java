package Module02.arrays;

import java.io.IOException;

import Module13.ioservices.MyIOService;


public class TraverseMultidimentionalArray {

	public static void main(String[] args) throws IOException {
		int [][] marks = new int [4][4]; 
		for (int i = 0; i < marks.length ; i ++) {
				System.out.println ("Enter marks of student : " + i);
				for (int j = 0; j < marks[i].length ; j++) {
			System.out.println ("Subject " + j);
			marks [i][j] = MyIOService.getInt ();
				}
		}
		for (int[] i : marks) {
		for (int j : i){
			System.out.print (j + " ");
		}
				System.out.println();
		}


	}

}
