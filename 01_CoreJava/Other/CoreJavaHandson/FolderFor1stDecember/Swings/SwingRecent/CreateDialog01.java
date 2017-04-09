import javax.swing.*;
import java.awt.*;

public class CreateDialog01 {

	public static void messageDialog(String message) {
		JOptionPane.showMessageDialog(null,message);
		System.out.println("asdfasdf");
	}
	
	public static void main (String [] args)  {
		CreateDialog01.messageDialog("Hello ! This is a Swing Dialog box");
	}
}

