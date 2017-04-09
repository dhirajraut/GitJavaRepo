import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Swing02_01TestFonts
	{	public static void main(String[] args)
			{	String fontNames[] = {"Serif", "SansSerif", "Dialog", "DialogInput", "Monospaced"};
				int styleNames[] = { Font.BOLD, Font.PLAIN, Font.ITALIC};
				
				Font f = null;
				JLabel l = null;
				JPanel jp = new JPanel(new GridLayout(15, 1));
				for(int i=0; i<fontNames.length; i++)
					{	for(int j=0; j<styleNames.length; j++)
							{	f = new Font(fontNames[i], styleNames[j], 20);
								l = new JLabel("Font Name: "+fontNames[i]+" Style:"+styleNames[j]);
								l.setFont(f);
								jp.add(l);
							}
					}
				//int hPolicy =  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				//int vPolicy =  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
				//JScrollPane jsp = new JScrollPane(jp,vPolicy, hPolicy);
				
				//jsp.setVisible(true);
				
				JFrame fr = new JFrame();
				Container c = fr.getContentPane();
				//c.setLayout(new FlowLayout());
				fr.setVisible(true);
				fr.setSize(300, 200);
				c.add(jp);
				//c.add(jsp);
				fr.validate();
			}
	}
