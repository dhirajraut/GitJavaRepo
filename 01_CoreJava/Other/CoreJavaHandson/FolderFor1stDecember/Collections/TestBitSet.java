// How BitSet works
/*Constructors BitSet();  BitSet(int size);  Where 'size' is  number of bits to be reserved as a size of  BitSet object
	* Both these constructors initialize an object to all zero bits.
	* To set and get a bit status: void set(int index); boolean get(int index); where index is bit position subscript.
	* To set any bit to zero : void clear(int index);	The bit at position 'index' is set to zero.
	* For bitwise logical operations:  void and(bitSet);
																		void andNot(bitSet);
																		void or(bitSet);
																		void xor(bitSet);
																	These operations operate between invoking and given bitset and result goes to invoking bitset
		For Relational Operations		:	boolean equals(bitSet); Returns 'true' if bit pattern of 'bitSet' exact matches with that of
																														  invoking bit set.
		Length Calculating :	int length();		Returns number of bits required to hold contents of invoking BitSet.
													int size();				Returns number of bits in invoking Bitset.
		Other:	Object clone();			Duplicates invoking bitset.
						String toString();		Returns string equivalent to invoking bitset.
	*/

import java.util.BitSet;
																				  
public class TestBitSet
	{	public static void main(String[] args)
			{	BitSet b1 = new BitSet(16);
				BitSet b2 = new BitSet(16);
				BitSet b3 = new BitSet(16);
				
				// set some bits
				for(int i=0; i<16; i++)
					{	if((i%2)==0)	b1.set(i);	b3=(BitSet) b1.clone();
						if((i%5)!=0)	b2.set(i);
					}
					
				// get bits setting
				for(int i=0; i<16; i++)
					System.out.println("The "+i+"th bit is :"+b1.get(i));
				
				System.out.print("Initial pattern in b1 using toString():");
				System.out.println(b1);
				
				System.out.print("Initial pattern in b2:");
				System.out.println(b2);
				
				// ANDing 
				b1.and(b2);
				System.out.print("ANDing b2 into b1:");
				System.out.println(b1);
				
				// ORing
				b1=(BitSet) b3.clone();
				b1.or(b2);
				System.out.print("ORing b2 into b1:");
				System.out.println(b1);
				
				// XORing
				b1=(BitSet) b3.clone();
				b1.xor(b2);
				System.out.print("XORing b2 into b1:");
				System.out.println(b1);
			}
	}
