
public class ConvertNumWord
	{	private static String oneWord(int n)
			{	String [] oneWord={"One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
				if (n<=0)
					return "";
				else 
					return oneWord[n-1];
			}
	
		private static String twoWord(int n)
			{	String [] twoWord= {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
				String [] tenWord= {"Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"};
				
				if (n<10)
					return oneWord(n);
				else if(n<20)
					return twoWord[n-10];
				else 
					{	int dl, dr;
						dl=n/10; dr = n%10;
						
						return tenWord[dl-2]+" "+oneWord(dr);
					}
			}
		
		public static String convert(int n)
			{	//12 34 56 7 89
				String str="";
				if (n<0)
					{	n*=(-1);
						str="-";
					}
				int dash = n/1%100;	// 89
				int shat = n/100%10;	// 7
				int saha = n/1000%100;	// 56
				int laks = n/100000%100;//34
				int cror = n/10000000%100;//12
				
				
				if (cror!=0)
					str+=twoWord(cror)+" Cror ";
				if (laks!=0)
					str+=twoWord(laks)+" Laks ";
				if (saha!=0)
					str+=twoWord(saha)+" Thousand ";
				if (shat!=0)
					str+=twoWord(shat)+" Hundred ";
				if (dash!=0)
					str+=twoWord(dash)+" Only.";
				return str;
			}
		
		public static void main(String [] argv)
			{	int n;
				
			/*System.out.println((n=123456789)+convert(n));
				System.out.println((n=3456789)+convert(n));
				System.out.println((n=56789)+convert(n));
				System.out.println((n=789)+convert(n));
				System.out.println((n=89)+convert(n));
				System.out.println((n=120000000)+convert(n));
				System.out.println((n=123400000)+convert(n));
				System.out.println((n=123456000)+convert(n));
				System.out.println((n=123456700)+convert(n));
				System.out.println((n=123456780)+convert(n));
				System.out.println((n=120000089)+convert(n));
				System.out.println((n=123400789)+convert(n));
				*/
			n = Integer.parseInt(argv[0]);
			String str = convert(n);
			System.out.println(str);
			}
}