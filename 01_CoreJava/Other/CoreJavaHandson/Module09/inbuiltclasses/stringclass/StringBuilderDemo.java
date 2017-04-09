package Module09.inbuiltclasses.stringclass;
class StringBuilderDemo {
	public static void main (String [] args) {
		int i = 16;
		StringBuilder str = new StringBuilder ();
		str.append ("Square root of ").append (i).append (" is ");
		str.append(Math.sqrt (i));
		System.out.println (str);
	}
}

