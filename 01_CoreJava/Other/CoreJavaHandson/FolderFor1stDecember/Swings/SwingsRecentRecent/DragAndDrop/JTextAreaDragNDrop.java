import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This example demonstrates adding drag and drop (DnD)
// support to a JTextArea.
public class JTextAreaDragNDrop
{
    JFrame aFrame;
    JPanel aPanel;
   
    JTextArea ta1;
    JTextArea ta2;

    // Constructor
    public JTextAreaDragNDrop()
    	{	aFrame = new JFrame("JTextArea Drag and Drop Demo");
	        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        aFrame.setSize(220, 20);
	        
	        aPanel = new JPanel();
	        aPanel.setLayout(new GridLayout(2, 1));
	        aPanel.setSize(220, 20);
	        // Add the widgets.
	        addWidgets();
	
	        // Add the panel to the frame.
	        aFrame.getContentPane().add(aPanel, BorderLayout.CENTER);

	        // Show the panel.
	        aFrame.pack();
	        aFrame.setVisible(true);
	    }

    // Create and add the widgets to the panel.
    private void addWidgets()
    	{	// Create widgets.
    	
    		// Declare two text areas
	        ta1 = new JTextArea();
	        ta2 = new JTextArea();
	        
	        //Dimension d = new Dimension(100, 10);
	        ta1.setSize(10, 100);
	        ta2.setSize(10, 100);
	        
	        // Declare and add two textareas in a scroll pane
	        JScrollPane jsp1 = new JScrollPane(ta1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        JScrollPane jsp2 = new JScrollPane(ta2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        
	        
	        ta1.setDragEnabled(true);
	        ta2.setTransferHandler(new TransferHandler("text"));
	        
	        MouseListener ml = new MouseAdapter()
	        	{	public void mousePressed(MouseEvent e)
		            	{	JComponent c = (JComponent)e.getSource();
		            		
		            		TransferHandler th = c.getTransferHandler();
		            		th.exportAsDrag(c, e, TransferHandler.COPY_OR_MOVE);
		            	}
	        	};
	        ta1.addMouseListener(ml);
	        
	        // Add widgets to container.
	        aPanel.add(jsp1);
	        aPanel.add(jsp2);
    	}

    // main method
    public static void main(String[] args)
    	{	// Set the look and feel.
	        try {	UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName());}
	        catch(Exception e) {}

	        JTextAreaDragNDrop example = new JTextAreaDragNDrop();
    	}
	}

