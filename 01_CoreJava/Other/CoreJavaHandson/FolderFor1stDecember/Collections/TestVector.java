import java.util.Vector;
import java.util.Enumeration;
//Vectors : In earlier version, Collection Framework was not available.  
/*	Instead, Legacy classes(Vector, Enumeration, Stack, Dictionary, Properties
 * 	etc.) were available.
 * None of the collection classes are Synchornized but legacy classes are Synchronized.
 * 
 * 
 */

/* List of methods available for vector
 * boolean add(Object), void addElement(Object) : Appends an Object at last
 * void add(int index, Object), void insertElementAt(index, Object): Inserts an object at given index position.
 * int capacity():	Returns current capacity of a vector
 * void clear(): Removes all elements from the vector
 * boolean contains(Object): Returns true if given object is found in the vector 
 * Object elementAt(int index), Object get(int index): Returns element present at given index
 * void ensureCapacity(int mincapa);  Ensures minimum capacity for vector.
 * int indexOf(Object): Searches for Object in the vector. Returns subscript of its first occurance.
 * boolean isEmpty():	Test whether vector has any more componenents
 * Iterator iterator(): Returns iterator for this vector.
 * Object lastElement(): Returns very last element
 * Object firstElement():Returns very first element
 * Object remove(int index); Removes and returns an object present at given index
 * boolean remove(Object): Searches for first appearance and if found removes and returns true.  Otherwise false.
 * Object set(int index, Object new): Returns old replaces it with new an Object at given index.
 * void setElementAt(int index, Object new): Replaces element at index by new Object.
 * void setSize(int size): Sets new size for a vector.
 * int size(): Returns current size of a vector.
 * Object [] toArray(): Converts given vector into array of Objects
 * String toString(): Converts every vector element into a string. 
 * void trimToSize(): Trims the capacity of a vector to current size.
 */
public class TestVector
	{	public static void main(String[] args)
			{ String st=new String("Pragati Softwares");
			   Integer enrollno=new Integer(50);
			   Float feepd=new Float(3.14);
			   Character ch = new Character('A');
			   Element vc = new Element("Mumbai", 101);
		
			   Vector v=new Vector(4);
			   v.addElement( st);	// add()
			   v.addElement(enrollno);
			   v.addElement(feepd);
			   v.addElement(vc);
			   v.add( 1, ch);	// Inserts at 1st subscript position
			   System.out.println("Print Vector list"+v);
			   
			   System.out.println("First Element : "+v.firstElement());
			   System.out.println("Last Element : "+v.lastElement());
			   
			  
			   Enumeration e = v.elements();
			   Object o;
			   while(e.hasMoreElements())
					{	o = e.nextElement();
				   		if (o instanceof  String)
								 System.out.println("String        :"+(String) o);
						else
							if (o  instanceof Integer)
									 System.out.println("Integer      :"+(Integer) o);
							else
								if (o  instanceof  Element)
									System.out.println("VectorClass:"+(Element) o);
								else if (o instanceof Float)
										System.out.println("VectorClass:"+(Float) o);	
					}
			}
	}

class Element
   { private String name;
      private int rollno;
      
      Element(String nm, int rn)
			{ name=nm; rollno=rn; }
      
      public String toString()
			{ return( "Name = " + name + "   Roll No.:"+rollno); }
   }