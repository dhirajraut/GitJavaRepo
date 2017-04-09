package Module09.inbuiltclasses.stringclass;
class StringBufferAppend {
 	public static void main (String [] args){
 			int i = 16;
   			StringBuffer str = new StringBuffer ();
    			str.append ("Square root of ").append (i).append (" is ");
    			str.append(Math.sqrt (i));
    			System.out.println (str);
  	}
}
