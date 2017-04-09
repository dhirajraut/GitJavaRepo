//import java.applet.Applet;

import javax.swing.JButton;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class JInputForm extends JFrame implements ActionListener, ItemListener, MouseListener
	{	JButton butOk = new JButton("OK");
	    JButton butClear = new JButton("Clear");
		JLabel lblNm=new JLabel("Name");
		JLabel lblPwd=new JLabel("PassWord");
		JLabel lblAdd=new JLabel("Address");
		JLabel lblRes=new JLabel("Result");
		JLabel lblGen=new JLabel("Gender");
		JTextField txtNm=new JTextField(30);
		JPasswordField txtPwd = new JPasswordField(12);
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
		public JInputForm()
			{	setSize(600,300);
				setBackground(Color.LIGHT_GRAY);
				 butClear.addActionListener( this);			 // Component Registration
				 butOk.addActionListener(this);					// Component Registration
				 chkSal.addItemListener(this);						  // Component Registration
				 
				 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

public class TestJInputForm
	{	public static void main(String [] argv)
			{	JInputForm inF = new JInputForm();
				inF.setVisible(true);
			}
	}
