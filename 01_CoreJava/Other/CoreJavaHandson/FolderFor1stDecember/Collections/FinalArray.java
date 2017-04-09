import java.util.*;

public class FinalArray {
  public static void main(String args[]) {
	final String suitsNames[] = {
		"hearts",
		"clubs",
		"diamonds",
		"spades"
	};

	final List suits = Collections.unmodifiableList(Arrays.asList(suitsNames));


	for(int i=0;i<suitsNames.length;i++) {
		System.out.println(""+suitsNames[i]);
	}

	suitsNames[3]="armani";

	for(int i=0;i<suitsNames.length;i++) {
		System.out.println(""+suitsNames[i]);
	}



  }
}