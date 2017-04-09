/* Finalizers are the methods that objects can use to
 * 		release resources before thay are distroyed.
 * The Garbage Collector of JVM is responsible for calling
 * 		finalizers.
 * To ensure robustness, a developer is not given a chance
 * 		to call finalizers but JVM do it.  Developer can
 * 		only specify what to do while destroying an object.
 * The finalize() method is inherited from Object class.
 * 		Developer can override this method.  Its return
 * 		type is void.
 * The last line of finalize() method should call finalize
 * 		method for superclass to give that layer a chance to
 * 		cleanup. 
 */
public class finalizeMethod
	{	int id;
		finalizeMethod(int i)
			{	id = i;
				System.out.println("Creating object: "+id);
			}
		public static void main(String[] args)
			{	//System.runFinalizersOnExit(true);
				finalizeMethod f = null;
				for(int i=0; i<11000; i++)
					{	f = new finalizeMethod(i);
					}
				//System.out.println("Calling finalize using System.gc()");
				//System.gc();
			}
		public void finalize() throws Throwable
			{	System.out.println("Finalizing :"+ id);
				super.finalize();
			}
	}
