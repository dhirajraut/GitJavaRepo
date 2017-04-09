import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

class MainMenuItem implements ActionListener, MouseListener, MenuKeyListener
	{ 	JMenuBar mbar;
		JOptionPane jop;
		JMenu master, issue, exit;
		public MainMenuItem()
			{ 	mbar=new JMenuBar();
				mbar.setToolTipText("Issue Tracking System");
				
				master = new JMenu("Master");	master.setMnemonic('M');master.setToolTipText("Master Data Bases Management");
				issue = new JMenu("Issues");	issue.setMnemonic('I');issue.setToolTipText("Issues Management");
				exit = new JMenu("Exit");	exit.setMnemonic('E');exit.setToolTipText("Thanks! Visit Again");
				exit.addMouseListener(this); exit.addMenuKeyListener(this);
				
				// Read Submenu for master
				JMenuItem depttsMaster = new JMenuItem("Departments");	depttsMaster.addActionListener(this);
				JMenuItem usersMaster = new JMenuItem("Users");	usersMaster.addActionListener(this);
				JMenuItem locationsMaster = new JMenuItem("Locations");locationsMaster.addActionListener(this);
				JMenuItem equipmentsMaster = new JMenuItem("Equipment Types");equipmentsMaster.addActionListener(this);
				//equipmentsMaster.addMenuListener(this);
				
				master.add(depttsMaster);
				master.add(usersMaster);
				master.add(locationsMaster);
				master.add(equipmentsMaster);
						
				// Add menues to mbar
				mbar.add(master);
				mbar.add(issue);
				mbar.add(exit);
				mbar.setBorder(new BevelBorder(BevelBorder.RAISED));
			}
	public void actionPerformed(ActionEvent e)
		{ if ((e.getActionCommand()).equalsIgnoreCase("Departments"))
				{	// Disableing unnecessary menues.
					master.setEnabled(false);
					issue.setEnabled(false);
					jop.showConfirmDialog(null, "I am confirm dialog box in Master.");
					master.setEnabled(true);
					issue.setEnabled(true);
					TestMenuBar.f.setVisible(true);
				}
		  if ((e.getActionCommand()).equalsIgnoreCase("Users"))
		  		{	// Disableing unnecessary menues.
					master.setEnabled(false);
					issue.setEnabled(false);
					TestMenuBar.f.setSize(500, 300);
					jop.showConfirmDialog(null, "I am confirm dialog box in User.");
					master.setEnabled(true);
					issue.setEnabled(true);
					TestMenuBar.f.setVisible(true);			  
		  		} 
		  if ((e.getActionCommand()).equalsIgnoreCase("Locations"))
		  		{}
		  if ((e.getActionCommand()).equalsIgnoreCase("Equipment Types"))
		  {}
		}
	public void mouseClicked(MouseEvent me)		// For Exit only
		{	System.exit( 0);}
	public void menuKeyTyped(MenuKeyEvent ke)	// For Exit only
		{	System.exit( 0);}
	public void mousePressed(MouseEvent me)
	 	{}
	public void mouseReleased(MouseEvent me)
		{}
	public void mouseExited(MouseEvent me)
		{}
	public void mouseEntered(MouseEvent me)
		{}
	public void menuKeyPressed(MenuKeyEvent ke)
		{}
	public void menuKeyReleased(MenuKeyEvent ke)
		{}
	}

public class TestMenuBar
	{	static JFrame f;
		static MainMenuItem Jmenu;
		public static void main(String []argv)
			{	f = new JFrame("ISSUE TRACKING SYSTEM");
				
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Jmenu=new MainMenuItem();
				f.setJMenuBar(Jmenu.mbar);
				//f.setGlassPane(login.loginBox);
				(f.getJMenuBar()).setVisible(true);
				//f.getGlassPane().setVisible(true);
				//(f.getGlassPane()).add(login.loginBox, BorderLayout.CENTER);
				
				f.setSize(320,200);
				(f.getContentPane()).setLayout(new BorderLayout());
				f.setVisible(true);
			}
	}

/*class MyWindowListener extends WindowAdapter
	{	 MyWindowListener()
				{ super(); }
		 public void windowClosing( WindowEvent e)
				{ System.exit(0); }
	}*/
