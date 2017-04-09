import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class JInputInternalForm extends JInternalFrame implements ActionListener, ItemListener, MouseListener
{	JButton butOk = new JButton("OK");
    JButton butClear = new JButton("Clear");
	JLabel lblNm=new JLabel("Name");
	JLabel lblPwd=new JLabel("PassWord");
	JLabel lblAdd=new JLabel("Address");
	JLabel lblRes=new JLabel("Result");
	JLabel lblGen=new JLabel("Gender");
	JTextField txtNm=new JTextField(30);
	JTextField txtPwd=new JTextField(10);
	JTextArea txtAdd=new JTextArea(" ",5,30);
	JTextField txtRes = new JTextField(30);
	JCheckBox  chkSal = new JCheckBox("Salaried", true);
	
	ButtonGroup bg = new ButtonGroup();
	JRadioButton chkMale = new JRadioButton("Male", false);
	JRadioButton chkFemale = new JRadioButton("Female", true);
	
	JLabel l1=new JLabel("Enter Name");
	boolean str;
	String st=new String();
	int x, y;
	public JInputInternalForm(String msg)
		{	super(msg, true, true, true, true);
			setSize(600,200);
			setBackground(Color.LIGHT_GRAY);
			 butClear.addActionListener( this);	// Component Registration
			 butOk.addActionListener(this);		// Component Registration
			 chkSal.addItemListener(this);		// Component Registration
			 
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(lblNm); p.add(txtNm);
			p.add(lblPwd); //txtPwd.setEchoChar('*');
			p.add(txtPwd); 
			p.add(lblAdd); p.add(txtAdd);
			p.add(lblGen);
			bg.add(chkMale);
			bg.add(chkFemale);
			
			p.add(chkMale); p.add(chkFemale);
			p.add(chkSal); 
			p.add(butOk);p.add(butClear);
			p.add(lblRes); p.add(txtRes);
			txtAdd.addMouseListener(this);
			this.getContentPane().add(p);
		}
	public void itemStateChanged(ItemEvent ie)
		{ if (ie.getSource()==chkSal)
					{		str=chkSal.isSelected();
							if (str)
									   { st=st+new String(" is Salaried.");	   }
							   else
										{ st=st+new String(" is not Salaried."); }
					}
		  	if ((ie.getSource()==chkMale))
		  		st=st+"Male";
		  	
		}
	public void actionPerformed(ActionEvent ae)
		{ 	if  ( ae.getSource()==butClear)
				{ txtNm.setText("");
					txtPwd.setText("");
					txtAdd.setText("");
					txtRes.setText("");
				}
			else								 // For Ok button
				{  if (chkMale.isSelected())
						txtRes.setText(txtRes.getText()+"Male");
					else
						txtRes.setText(txtRes.getText()+"Female");
					txtRes.setText(txtNm.getText()+st);							 
				}
		}
	public void mouseClicked(MouseEvent e) 
		{  x=e.getX();  y=e.getY();
			txtRes.setText("Mouse clicked at "+"X:"+x+"  "+"Y:"+y);
		}
	public  void mouseEntered(MouseEvent e) 
		{  x=e.getX();  y=e.getY();
			txtRes.setText("Mouse entered at "+"X:"+x+"  "+"Y:"+y);
		}
	public  void mouseExited(MouseEvent e) 
		{  x=e.getX();  y=e.getY();
			txtRes.setText("Mouse exited at "+"X:"+x+"  "+"Y:"+y);
		}
	public void mousePressed(MouseEvent e) 
		{  x=e.getX();  y=e.getY();
			txtRes.setText("Mouse pressed at "+"X:"+x+"  "+"Y:"+y);
		}
	public  void mouseReleased(MouseEvent e) 
	   {  x=e.getX();  y=e.getY();
			txtRes.setText("Mouse released at "+"X:"+x+"  "+"Y:"+y);
		}
}
public class PlugableLookAndFeel08
	{	public static void main(String[] args)
			{	JFrame jf = new JFrame("Pluggable Look & Feel");
				JDesktopPane jdp = new JDesktopPane();
				try {	jf.setSize(700, 700);
						
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
						JInputInternalForm inFCross = new JInputInternalForm("Cross Platform");
						inFCross.setLocation(0, 0);
						inFCross.setVisible(true);
						
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						JInputInternalForm inFSystem = new JInputInternalForm("System Platform");
						inFSystem.setLocation(0, 210);
						inFSystem.setVisible(true);
						
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
						JInputInternalForm inFMotif = new JInputInternalForm("Motif Platform");
						inFMotif.setLocation(0, 420);
						inFMotif.setVisible(true);
						
						jdp.add(inFCross);
						jdp.add(inFSystem);
						jdp.add(inFMotif);
						
						jf.getContentPane().add(jdp);
						jf.setVisible(true);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			}
	}
