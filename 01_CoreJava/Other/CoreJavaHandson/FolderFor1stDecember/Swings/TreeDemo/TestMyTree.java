import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TestMyTree {
	
	public static void createNodeTree(DefaultMutableTreeNode country) {
		
					DefaultMutableTreeNode maharashtra  = new DefaultMutableTreeNode("Maharashtra");
					
					country.add(maharashtra);
					
					DefaultMutableTreeNode	mumbai	= new DefaultMutableTreeNode("Mumbai");
					DefaultMutableTreeNode  pune	= new DefaultMutableTreeNode("Pune"); 
					DefaultMutableTreeNode chandra	= new DefaultMutableTreeNode("Chandrapur");
					
					
					maharashtra.add(mumbai);
					maharashtra.add(pune);
					maharashtra.add(chandra);
					
					DefaultMutableTreeNode  andhra	= new DefaultMutableTreeNode("Andhra Pradesh");
					DefaultMutableTreeNode	hydera	= new DefaultMutableTreeNode("Hyderabad");
					DefaultMutableTreeNode  kachi	= new DefaultMutableTreeNode("Kachiguda");
					
					country.add(andhra);
					andhra.add(hydera);
					andhra.add(kachi);
					
					DefaultMutableTreeNode madhya	= new DefaultMutableTreeNode("Madhya Pradesh"); 
					DefaultMutableTreeNode	bhopal  = new DefaultMutableTreeNode("Bhopal"); 
					DefaultMutableTreeNode  indore	= new DefaultMutableTreeNode("Indore");
					
					country.add(madhya);
					madhya.add(bhopal);
					madhya.add(indore);
			}
	
		public static void createGUI() {	
				DefaultMutableTreeNode top = new DefaultMutableTreeNode("INDIA");
				createNodeTree(top);
				JTree mt = new JTree( top);
				/*mt.setSize(200, 200);
				mt.setVisible(true);
				*/
				
				JScrollPane jsp = new JScrollPane(mt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				jsp.setSize(200, 200);
				jsp.setVisible(true);
				
				JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		        splitPane.setTopComponent(jsp);
		        splitPane.setDividerLocation(100);
		        splitPane.setPreferredSize(new Dimension(500, 300));
		        
				JPanel jp = new JPanel(new GridLayout(1, 0));
				//jp.add(BorderLayout.NORTH, new JButton("North"));
				jp.add(splitPane);
				//jp.add(jsp);
				//jp.add(BorderLayout.SOUTH, new JButton("South"));
				jp.setSize(new Dimension(500, 300));
				
				jp.setVisible(true);
				jp.setOpaque(true);
				
				JFrame.setDefaultLookAndFeelDecorated(true);
				JFrame jf = new JFrame("Testing JTree");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(500, 500);
				
				jf.getContentPane().add(jp);
				jf.setVisible(true);
			}
		
		 public static void main(String[] args)	{
			 javax.swing.SwingUtilities.invokeLater( new Runnable() {
				 public void run() {
					 createGUI();	
				 }
			 });
		 }
}


