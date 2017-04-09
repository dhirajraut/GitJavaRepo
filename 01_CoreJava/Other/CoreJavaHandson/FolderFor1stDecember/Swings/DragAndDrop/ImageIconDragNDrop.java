// This program is not allowing drag and drop on images.  Not working.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This example demonstrates adding drag and drop (DnD)
// support to a JTextArea.
public class ImageIconDragNDrop
{
    JFrame aFrame;
    JPanel aPanel;
   
    ImageIcon i1;
    ImageIcon i2;

    // Constructor
    public ImageIconDragNDrop()
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
    		String path = "C:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\src\\Swings\\DragAndDrop\\Icons";
	        i1 = new ImageIcon(path, "dukeWaveRed.gif");
	        i2 = new ImageIcon();
	        
	        // Declare and add two textareas in a scroll pane
	        //JScrollPane jsp1 = new JScrollPane(i1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        //JScrollPane jsp2 = new JScrollPane(i2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        
	        
	        i1.setDragEnabled(true);
	        i2.setTransferHandler(new TransferHandler("text"));
	        
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

	        ImageIconDragNDrop example = new ImageIconDragNDrop();
    	}
	}