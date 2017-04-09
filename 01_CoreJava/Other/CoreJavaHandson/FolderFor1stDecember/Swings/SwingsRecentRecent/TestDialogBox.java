import javax.swing.JOptionPane;

public class TestDialogBox
	{	public static void main(String[] args)
			{	JOptionPane jop = new JOptionPane();
				int i;
				String str;
				// Displays three buttons 'yes', 'no' and 'cancel' along with given message.
				// and a default icon.
				// Its a model dialog box returns 'int'. 0 for yes, 1 for no and 2 for cancel.
				
				// **** Confirmation Dialog Box
				//i=jop.showConfirmDialog(null, "I am confirm dialog box.");
				//i=jop.showConfirmDialog(null, "I am confirm D.B. with CANCEL_OPTION", "Option Pane",JOptionPane.CANCEL_OPTION );
				//i=jop.showConfirmDialog(null, "I am confirm D.B.with CLOSED_OPTION", "Option Pane",JOptionPane.CLOSED_OPTION);
				//i=jop.showConfirmDialog(null, "I am confirm D.B.with DEFAULT_OPTION", "Option Pane",JOptionPane.DEFAULT_OPTION);
				//i=jop.showConfirmDialog(null, "I am confirm D.B.with NO_OPTION", "Option Pane",JOptionPane.NO_OPTION);
				//i=jop.showConfirmDialog(null, "I am confirm D.B.with OK_CANCEL_OPTION", "Option Pane",JOptionPane.OK_CANCEL_OPTION);
				i=jop.showConfirmDialog(null, "I am confirm D.B.with OK_OPTION", "Option Pane",JOptionPane.OK_OPTION);
				//i=jop.showConfirmDialog(null, "I am confirm D.B.with PLAIN_MESSAGE", "Option Pane",JOptionPane.PLAIN_MESSAGE);
				System.out.println(i);
				
				// **** Input Dialog Box
				//str=jop.showInputDialog(null, "Enter Department Name.");
				//str =jop.showInputDialog(null, "I am message DB with initial value", "Chandrapur");
				// This above db sends Empty string message initially, latter, entered string
				// So, it throws two strings.
				
				//str =jop.showInputDialog(null, "I am message DB with initial value","Initial Value");
				//Object selectedValue=jop.getValue();
				//System.out.println("getValue:"+jop.getValue());
				//System.out.println("getSelectedValue:"+jop.getSelectionValues());
				//System.out.println("getInputValue:"+jop.getInputValue());
				//System.out.println("getReturnedValue:"+str);
				//if (str==null)	// Irrespective of value, true on pressing 'Cancel' button
					//jop.showMessageDialog(null,"Cancel Pressed." );
					//System.out.println("Cancel Pressed.");
				//else
					//if (str.equalsIgnoreCase(""))	// Input is empty.
						//jop.showMessageDialog(null,"Empty String" );
						//System.out.println("Empty Screen.");
					//else
						//jop.showMessageDialog(null,str );
						//System.out.println(str);
				
				//**** Message Dialog Box
				//jop.showMessageDialog(null, "I am DB MessageDialog");
				jop.showMessageDialog(null, "I am DB with Mess dia, title and ERROR_MESSAGE", "Option Pane", JOptionPane.ERROR_MESSAGE);
				//jop.showMessageDialog(null, "I am DB with Mess dia, title and INFORMATION_MESSAGE", "Option Pane", JOptionPane.INFORMATION_MESSAGE);
				//jop.showMessageDialog(null, "I am DB with Mess dia, title and PLAIN_MESSAGE", "Option Pane", JOptionPane.PLAIN_MESSAGE);
				//jop.showMessageDialog(null, "I am DB with Mess dia, title and QUESTION_MESSAGE", "Option Pane", JOptionPane.QUESTION_MESSAGE);
				//jop.showMessageDialog(null, "I am DB with Mess dia, title and WARNING_MESSAGE", "Option Pane", JOptionPane.WARNING_MESSAGE);
				
				//****
				
				//System.out.println(i);
				//System.exit(0);
			}
	}
