//import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class InputForm extends Frame implements ActionListener, ItemListener, MouseListener
	{	Button butOk = new Button("OK");
	    Button butClear = new Button("Clear");
		Label lblNm=new Label("Name");
		Label lblPwd=new Label("PassWord");
		Label lblAdd=new Label("Address");
		Label lblRes=new Label("Result");
		Label lblGen=new Label("Gender");
		TextField txtNm=new TextField(30);
		TextField txtPwd=new TextField(10);
		TextArea txtAdd=new TextArea(" ",5,30);
		TextField txtRes = new TextField(100);
		Checkbox  chkSal = new Checkbox("Salaried", true);
		CheckboxGroup cbg=new CheckboxGroup();
		Checkbox chkMale = new Checkbox("Male", true, cbg);
		Checkbox chkFemale = new Checkbox("Female", true, cbg);
		Label l1=new Label("Enter Name");
		boolean str;
		String st=new String();
		int x, y;
		public InputForm()
			{	setSize(600,300);
				setBackground(Color.LIGHT_GRAY);
				 butClear.addActionListener( this);			 // Component Registration
				 butOk.addActionListener(this);					// Component Registration
				 chkSal.addItemListener(this);						  // Component Registration
				 addWindowListener(new MyWindowListener());
				 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 
				Panel p = new Panel();
				//p.addMouseMotionListener(this);
				//p.setLayout(new FlowLayout());
				p.add(lblNm); p.add(txtNm);
				p.add(lblPwd); txtPwd.setEchoChar('*');p.add(txtPwd); 
				p.add(lblAdd); p.add(txtAdd);
				p.add(lblGen); p.add(chkMale); p.add(chkFemale);
				p.add(chkSal); 
				p.add(butOk);p.add(butClear);
				p.add(lblRes); p.add(txtRes);
				txtAdd.addMouseListener(this);
				this.add(p);
			}
		public void itemStateChanged(ItemEvent ie)
			{ if (ie.getSource()==chkSal)
						{		str=chkSal.getState();
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
					{ 	txtNm.setText("");
						txtPwd.setText("");
						txtAdd.setText("");
						txtRes.setText("xxxxxx");
					}
				else								 // For Ok button
					{  if (chkMale.getState())
							txtRes.setText(txtRes.getText()+"Male");
						else
							txtRes.setText(txtRes.getText()+"Female");
						txtRes.setText(txtNm.getText()+st);							 
					}
			}
		public void mouseClicked(MouseEvent e) 
			{  x=e.getX();  y=e.getY();
				txtRes.setText("Mouse clicked at "+"X:"+x+"  "+"Y:"+y);
				this.validate();
			}
		public  void mouseEntered(MouseEvent e) 
			{  x=e.getX();  y=e.getY();
				txtRes.setText("Mouse entered at "+"X:"+x+"  "+"Y:"+y);
				this.validate();
			}
		public  void mouseExited(MouseEvent e) 
			{  x=e.getX();  y=e.getY();
				txtRes.setText("Mouse exited at "+"X:"+x+"  "+"Y:"+y);
				this.validate();
			}
		public void mousePressed(MouseEvent e) 
			{  x=e.getX();  y=e.getY();
				txtRes.setText("Mouse pressed at "+"X:"+x+"  "+"Y:"+y);
				this.validate();
			}
		public  void mouseReleased(MouseEvent e) 
		   {  x=e.getX();  y=e.getY();
				txtRes.setText("Mouse released at "+"X:"+x+"  "+"Y:"+y);
				this.validate();
			}
	}
class MyWindowListener extends WindowAdapter
	{	 MyWindowListener()
				{ super(); }
		 public void windowClosing( WindowEvent e)
				{ System.exit(0); }
	}
public class TestInputForm04
	{	public static void main(String [] argv)
			{	InputForm inF = new InputForm();
				//inF.setVisible(false);
				inF.show();
				//inF.validate();
			}
	}