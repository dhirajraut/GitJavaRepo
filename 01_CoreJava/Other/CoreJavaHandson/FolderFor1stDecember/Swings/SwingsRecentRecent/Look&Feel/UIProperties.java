/**Fonts, colors and icons associated with each L&F are stored in an instance of
 * 		UIDefaults. You can change a given UI by making modifications to the values
 * 		stored in these objects. The UIDefaults associated with a particular Look
 * 		and Feel can be retrieved with a call to the static getLookAndFeelDefaults()
 * 		method of the UIManager class.  
 */
import javax.swing.*;
  import java.util.*;
  public class UIProperties
  	{	public static void main(String[] args)
  			{	
  				UIDefaults def = UIManager.getLookAndFeelDefaults();
  				Enumeration enum = def.keys();
  				while (enum.hasMoreElements())
					{	Object item = enum.nextElement();
						System.out.println(item +" " + def.get(item));
					}
  			}
  } 
