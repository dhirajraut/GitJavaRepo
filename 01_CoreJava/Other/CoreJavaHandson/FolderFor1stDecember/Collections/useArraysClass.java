import java.util.Arrays;

public class useArraysClass
	{	public static void main(String [] argv)
			{	// Array sorting using Arrays class
				int [] list = { 5, 3, 1, 8, 7};
				Arrays.sort(list, 0, list.length-1);
				
				for(int i =0; i<list.length; i++)
					System.out.println(list[i]);
			}
	}
