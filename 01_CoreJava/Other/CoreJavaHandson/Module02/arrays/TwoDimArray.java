package Module02.arrays;


import java.util.Scanner;

import Module13.ioservices.MyIOService;

public class TwoDimArray {

	public static void main(String[] args) {
		int [][] marks = new int [4][4]; 
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i < marks.length ; i ++) {
			System.out.println ("Enter marks of student "+i);
				for (int j = 1; j < marks[i].length ; j++) {
					System.out.println ("Subject "+j);
					marks [i][j] = sc.nextInt();
				}
		}
		for (int i = 1; i < marks.length ; i++) {
			for (int j = 1; j < marks[i].length ; j++)
				System.out.print (marks[i][j]+" ");
			
		}
	}
}