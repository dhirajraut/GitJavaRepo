import java.util.Random;
import java.util.Date;
public class TestRandomGenerator
	{	// Available constructors.
		Random r = new Random();
		Random rs = new Random((new Date()).getTime());
		public TestRandomGenerator()
			{	System.out.println("Available Constructors.");
				System.out.println("Random()");
				System.out.println("Random(long Seed)");// longseed is 48 bits number over which gererator generates new set of random numbers.
				System.out.println("Available Methods...");
				System.out.println("nextBoolean()"+r.nextBoolean());
				System.out.println("nextByte()"+r.nextBoolean());
				System.out.println("nextDouble()"+r.nextDouble());
				System.out.println("nextInt()"+r.nextInt());
				System.out.println("nextLong()"+r.nextLong());
				System.out.println("setSeed(long newSeed)");
				r.setSeed((new Date()).getTime());
				}
	}
