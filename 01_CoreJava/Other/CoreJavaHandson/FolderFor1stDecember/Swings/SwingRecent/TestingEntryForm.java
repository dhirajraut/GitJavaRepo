import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestingEntryForm extends JFrame implements ActionListener
	{	JTextField txtEmpNo	= new JTextField();
		JTextField txtEmpNm = new JTextField();
		JTextField txtEmpBal= new JTextField();
		
		JButton butOk = new JButton("OK");
		JButton butClear = new JButton("CLEAR");
		JButton butCancel = new JButton("CANCEL");
		
		public TestingEntryForm()
			{	JLabel lblEmpNo = new JLabel("Acount Number");
				JLabel lblEmpNm = new JLabel("A/c Holder's Name");
				JLabel lblEmpBal= new JLabel("Account Balance");
				
				JPanel entryPan = new JPanel(new GridLayout(3, 2));
				entryPan.add(lblEmpNo);
				entryPan.add(txtEmpNo);
				entryPan.add(lblEmpNm);
				entryPan.add(txtEmpNm);
				entryPan.add(lblEmpBal);
				entryPan.add(txtEmpBal);
				
				butOk.addActionListener(this); butOk.setToolTipText("If entry is over, click here.");
				butClear.addActionListener(this);butClear.setToolTipText("To clear all contents, click here.");
				butCancel.addActionListener(this);butCancel.setToolTipText("To close application.");
				
				JPanel buttonPan = new JPanel();
				buttonPan.add(butOk);
				buttonPan.add(butClear);
				buttonPan.add(butCancel);
				
				JLabel bankName = new JLabel("asdasdf Bank"); 
				
				Container c = this.getContentPane();
				c.add(bankName, BorderLayout.NORTH);
				c.add(entryPan, BorderLayout.CENTER);
				c.add(buttonPan, BorderLayout.SOUTH);
				
				this.setSize(600, 400);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public void actionPerformed(ActionEvent ae)
			{	if (ae.getSource()== butOk)
					{	int empNo = Integer.parseInt(txtEmpNo.getText());
						String empNm = txtEmpNm.getText();
						float empBal = Float.parseFloat(txtEmpBal.getText());
						System.out.println(empNo+" "+empNm+" "+empBal);
					}
				else if (ae.getSource()==butClear)
					{	txtEmpNo.setText("");
						txtEmpNm.setText("");
						txtEmpBal.setText("");
					}
				else
					System.exit(0);
			}
		
		public static void main(String [] argv)
			{	TestingEntryForm f = new TestingEntryForm();
			
				f.setVisible(true);
			}
	}
