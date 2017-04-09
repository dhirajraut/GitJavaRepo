import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Adds exterior padding around components.
//Adds interiar padding which increase size of a commoponent by ipad*2
public class TestGridBagConstraints
	{	public static void main(String[] args)
			{	JFrame fr1 = new JFrame();
				fr1.setBounds(10, 10, 400, 400);
				Container cn=fr1.getContentPane();
				cn.setLayout(new GridLayout(2, 2));
				
				windowPanel w00 = new windowPanel(2, 2, 2, 2, 2, 2);
				cn.add(w00);
				
				windowPanel w10 = new windowPanel(2, 2, 2, 2, 20, 20);
				cn.add(w10);
				
				windowPanel w01 = new windowPanel(2, 2, 2, 2, 20, 2);
				cn.add(w01);
				
				windowPanel w11 = new windowPanel(2, 2, 2, 2, 2, 20);
				cn.add(w11);
				
				fr1.setVisible(true);
			}
	}
class windowPanel extends JPanel
	{	JButton but1=new JButton("Wonderful.");
		JButton but2=new JButton("World");
		JLabel  lbl =new JLabel();
		GridBagConstraints cbc=new GridBagConstraints();
			windowPanel(int is1, int is2, int is3, int is4, int ipx, int ipy)
				{	setLayout(new GridBagLayout());
					setBorder(BorderFactory.createBevelBorder(30));
					
					cbc.insets= new Insets(is1, is2, is3, is4);
					cbc.gridx=0;	cbc.gridy=0;	cbc.ipadx=ipx; 	cbc.ipady=ipy;
					add(but1, cbc);
					
					cbc.gridx=1;	cbc.gridy=0;	cbc.ipadx=0; 	cbc.ipady=0;
					add(but2, cbc);
					
					cbc.gridx=0;	cbc.gridy=1;	cbc.ipadx=0; 	cbc.ipady=0;
					lbl.setText("Inset:"+is1+" "+is2+" "+is3+" "+is4+"  Ipad :"+ipx+" "+ipy);
					add(lbl, cbc);
				}
	}