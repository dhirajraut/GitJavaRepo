//StringCharOccur.java
//
public class CountCharOccuranceInString
   { public static void main(String [] argv)
         { String str=new String("Today is second saturday");
            int i,n=str.length(),countalpha[]=new int[26], countspace=0, totchar=0;
			for(i=0; i<26; i++)
			    countalpha[i]=0;
			str=str.toLowerCase();
			//System.out.println(str.charAt(0));
            for(i=0; i<n; i++)
            	{	int x=(int)str.charAt(i);
            		System.out.print((char)x);
            		if (x==32)
            			countspace++;
            		if((x>=97) && (x<=122))
            			countalpha[x-97]++;
			   }
            System.out.println(str);
        	for(i=0; i<26; i++)
			     {	System.out.println((char)(97+i)+":"+countalpha[i]);
			       	totchar+=countalpha[i];
			     }
        	System.out.println("Total Characters are :"+totchar+"+"+countspace+"="+(totchar+countspace));
          }
    }	
