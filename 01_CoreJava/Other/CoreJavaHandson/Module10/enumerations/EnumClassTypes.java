package Module10.enumerations;

enum OperatingSystem {
		 Linux(9), Solaris(10), Windows(5), Macintosh(7);
  		  private int version;                     //version of each operating system

	   OperatingSystem( int v) {
			 version = v;
		 }

  		int getVersion() {
		 	return version;
	 }
}

public class EnumClassTypes {
	public static void main (String args [] ) {
    			System.out.println ("Linux version : "+OperatingSystem.Linux.getVersion());
    			System.out.println ("All operating systems versions :\n");
    			for(OperatingSystem os : OperatingSystem.values()) {
      			System.out.println (os+" version : "+os.getVersion());
    			}
  	}
}

