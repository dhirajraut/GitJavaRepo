// Implemented under 1.4.
// Type a value into the text field, select the text, then hold the mouse button down over the textfield
// and drag a few pixels. An icon appears under the cursor. Release the icon over the JLabel and see the text
// replace the "Drop Here" text. Simultaneously with the drop, the text is removed from the source textfield.
// The default behavior for drag and drop is MOVE. To change the behavior to COPY, hold down the CONTROL key
// while selecting the text. On Windows, a plus sign appears in the icon. When the text is released on the target,
// it is left intact in the source.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// This example demonstrates adding drag and drop (DnD)
// support to a JLabel.
public class JLabelDragNDrop {
    JFrame aFrame;
    JPanel aPanel;
    JTextField tf;
    JLabel tl;

    // Constructor
    public JLabelDragNDrop() {
        // Create the frame and container.
        aFrame = new JFrame("JLabel Drag and Drop Demo");
        aFrame.setSize(50, 50);
        aPanel = new JPanel();
        aPanel.setLayout(new GridLayout(2, 2));

        // Add the widgets.
        addWidgets();

        // Add the panel to the frame.
        aFrame.getContentPane().add(aPanel, BorderLayout.CENTER);

        // Exit when the window is closed.
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Show the panel.
        aFrame.pack();
        aFrame.setVisible(true);
    }

    // Create and add the widgets to the panel.
    private void addWidgets() {
        // Create widgets.
        tf = new JTextField(40);
        tf.setDragEnabled(true);
        tl = new JLabel("Drop Here", SwingConstants.LEFT);
        tl.setTransferHandler(new TransferHandler("text"));	// Empty constructor not available.
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JComponent c = (JComponent)e.getSource();
                TransferHandler th = c.getTransferHandler();
                th.exportAsDrag(c, e, TransferHandler.COPY);
            }
        };
        tl.addMouseListener(ml);

        // Add widgets to container.
        aPanel.add(tf);
        aPanel.add(tl);

        tl.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // main method
    public static void main(String[] args) {
        // Set the look and feel.
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e) {}

        JLabelDragNDrop example = new JLabelDragNDrop();
    }
}


