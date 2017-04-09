import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TestBiodataForm
	{	public static void main(String[] arg)
		{	MainFrame fr = new MainFrame();
			BiodataPanel centerPanel = new BiodataPanel();
			ButtonPanel southPanel = centerPanel.getButtonPanel(fr); 
			fr.setPanels(null, southPanel, null, null, centerPanel);
			
			fr.setVisible(true);
			fr.validate();
		}
	}

class MainFrame extends JFrame
	{	public MainFrame()
			{	
				UIManager.getSystemLookAndFeelClassName();
				
				this.setSize(575, 200);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setVisible(true);
			}
		
		public void setPanels(JPanel eastPanel,JPanel southPanel, JPanel westPanel, JPanel northPanel, JPanel centerPanel)
			{	if (eastPanel!=null)
					this.getContentPane().add(eastPanel, BorderLayout.EAST);
				if (southPanel!=null)
					this.getContentPane().add(southPanel, BorderLayout.SOUTH);
				if (westPanel!=null)
					this.getContentPane().add(westPanel, BorderLayout.WEST);
				if (northPanel!=null)
					this.getContentPane().add(northPanel, BorderLayout.NORTH);
				if (centerPanel!=null)
					this.getContentPane().add(centerPanel, BorderLayout.CENTER);
			}
	}

class BiodataPanel extends JPanel
	{	JTextField firstNmFld, middleNmFld, lastNmFld;
		JTextArea addTxtAr;
		JComboBox countryCombo;
		JRadioButton maleRadio, femaleRadio;
		JCheckBox musicChk,sportsChk, readChk, actChk, cookChk, gardenChk;
		ChandraPhoto photo;
		
		final int MAX_COMPONENTS= 13; // Represents total number of components in this class
		
		public BiodataPanel()
			{	JLabel nameLbl=new JLabel("Name:"), frNmLbl=new JLabel("First"),
				mdNmLbl=new JLabel("Middle"), lstNmLbl=new JLabel("Last"),
				addLbl=new JLabel("Address:"), countryLbl=new JLabel("Country"),
				genderLbl=new JLabel("Gender:"), hobbiesLbl=new JLabel("Hobbies:");
				
				firstNmFld = new JTextField(15);
				middleNmFld = new JTextField(15);
				lastNmFld = new JTextField(15);
				addTxtAr = new JTextArea(15, 30);
				countryCombo = new JComboBox();
				maleRadio = new JRadioButton("Male", true);
				femaleRadio = new JRadioButton("Female", false);
				sportsChk = new JCheckBox("Sports", true);
				musicChk = new JCheckBox("Music", false);
				readChk = new JCheckBox("Reading", false);
				actChk = new JCheckBox("Acting", false);
				cookChk = new JCheckBox("Cooking", false);
				gardenChk = new JCheckBox("Gardening", false);
				photo = new ChandraPhoto();
				
				// Setting combobox values
				countryCombo.addItem("India");
				countryCombo.addItem("Nepal");
				countryCombo.addItem("Bangladesh");
				countryCombo.addItem("Shrilanka");
				countryCombo.addItem("Mouritous");
				countryCombo.addItem("Pakistan");
				
				ButtonGroup bg = new ButtonGroup();
				bg.add(maleRadio);
				bg.add(femaleRadio);
				
				this.setLayout(new GridBagLayout());
				// this.setLayout(new FlowLayout());
				GridBagConstraints cn = new GridBagConstraints();
				
				// Setting common values
				cn.ipadx = 1; cn.ipady = 1; cn.weightx = 0.0f; cn.weighty = 0.0f;
				cn.fill = GridBagConstraints.NONE;
				cn.gridheight = 1; cn.gridwidth = 1;
				
				// Setting constraints for Name fields
				cn.gridx = 0; cn.gridy = 0;
				this.add(nameLbl, cn);
				cn.gridx = 1; cn.gridy = 0;cn.fill = GridBagConstraints.HORIZONTAL;
				this.add(firstNmFld, cn);cn.fill = GridBagConstraints.NONE;
				cn.gridx = 2; cn.gridy = 0;cn.fill = GridBagConstraints.HORIZONTAL;
				this.add(middleNmFld, cn);cn.fill = GridBagConstraints.NONE;
				cn.gridx = 3; cn.gridy = 0;cn.fill = GridBagConstraints.HORIZONTAL;
				this.add(lastNmFld, cn);cn.fill = GridBagConstraints.NONE;
				cn.gridx = 6; cn.gridy = 0;cn.fill = GridBagConstraints.HORIZONTAL;
				this.add(photo, cn);
				cn.gridx = 1; cn.gridy = 1;
				this.add(frNmLbl, cn);
				cn.gridx = 2; cn.gridy = 1;
				this.add(mdNmLbl, cn);
				cn.gridx = 3; cn.gridy = 1;
				this.add(lstNmLbl, cn);
				
				// Setting Constrains for Address and Country
				cn.gridx = 0; cn.gridy = 3;
				this.add(addLbl, cn);
				cn.gridx = 1; cn.gridy = 3;cn.fill = GridBagConstraints.BOTH;
				this.add(addTxtAr, cn);cn.fill = GridBagConstraints.NONE;
				
				cn.gridx = 3; cn.gridy = 3;
				this.add(countryLbl, cn);
				cn.gridx = 4; cn.gridy = 3;
				this.add(countryCombo, cn);
				
				// Setting constraints for Radio Buttons
				cn.gridx = 0; cn.gridy = 5;
				this.add(genderLbl, cn);
				cn.gridx = 1; cn.gridy = 5;
				this.add(maleRadio, cn);
				cn.gridx = 2; cn.gridy = 5;
				this.add(femaleRadio, cn);
				
				// Setting constraints for Check box
				cn.gridx = 0; cn.gridy = 7;
				this.add(hobbiesLbl, cn);
				cn.gridx = 1; cn.gridy = 7;
				this.add(sportsChk, cn);
				cn.gridx = 2; cn.gridy = 7;
				this.add(musicChk, cn);
				cn.gridx = 3; cn.gridy = 7;
				this.add(readChk, cn);
				cn.gridx = 4; cn.gridy = 7;
				this.add(actChk, cn);
				cn.gridx = 5; cn.gridy = 7;
				this.add(cookChk, cn);
				
				cn.gridx = 6; cn.gridy = 7;
				this.add(gardenChk, cn);
				
				this.setVisible(true);
				this.setSize(100, 300);
			}
		public ButtonPanel getButtonPanel(Container fr)
			{	Component [] comp=new Component[MAX_COMPONENTS];
				comp[0] = firstNmFld; comp[1] = middleNmFld; comp[2] = lastNmFld;
				comp[3] = addTxtAr; comp[4] = countryCombo;
				comp[5] = maleRadio; comp[6] = femaleRadio;
				comp[7] = musicChk; comp[8] = sportsChk; comp[9] = readChk;
				comp[10] = actChk; comp[11] = cookChk; comp[12] = gardenChk;
				
				return new ButtonPanel(fr, comp);
			}
	}

class ButtonPanel extends JPanel
	{	final int MAX_BUTTONS = 3; 
		JButton[] but = new JButton[MAX_BUTTONS];
		
		public ButtonPanel(Container fr, Component[] comp)
			{	but[0] = new JButton("OK");
				but[1] = new JButton("CLEAR");
				but[2] = new JButton("CANCEL");
				
				ButtonPanelActions Actions = new ButtonPanelActions(but, fr, comp);
				but[0].addActionListener(Actions);
				but[1].addActionListener(Actions);
				but[2].addActionListener(Actions);
				
				for(int i=0; i<3; i++)
					this.add(but[i]);
			}
	}

class ButtonPanelActions implements ActionListener
	{	JButton okBut, clearBut, cancBut;
	
		JTextField firstNmFld, middleNmFld, lastNmFld;
		JTextArea addTxtAr;
		JComboBox countryCombo;
		JRadioButton maleRadio, femaleRadio;
		JCheckBox musicChk,sportsChk, readChk, actChk, cookChk, gardenChk;
		
		JFrame fr;
		public ButtonPanelActions(JButton[] but, Container fr, Component[] comp)
			{	okBut = but[0]; clearBut = but[1]; cancBut = but[2];
				
				fr = (JFrame)fr;
				firstNmFld = (JTextField) comp[0];
				middleNmFld = (JTextField) comp[1];
				lastNmFld = (JTextField) comp[2];
				
				addTxtAr = (JTextArea) comp[3];
				countryCombo = (JComboBox) comp[4];
				maleRadio = (JRadioButton) comp[5];
				femaleRadio = (JRadioButton) comp[6];
				
				musicChk = (JCheckBox) comp[7];
				sportsChk = (JCheckBox) comp[8];
				readChk = (JCheckBox) comp[9];
				actChk = (JCheckBox) comp[10];
				cookChk = (JCheckBox) comp[11];
				gardenChk = (JCheckBox) comp[12];
			}
		public void actionPerformed(ActionEvent e)
			{	if (e.getSource() == clearBut)
					{	firstNmFld.setText("");
						middleNmFld.setText("");
						lastNmFld.setText("");
						
						addTxtAr.setText("");
						
						countryCombo.setSelectedIndex(-1);
						
						maleRadio.setSelected(true);
						femaleRadio.setSelected(false);
						
						musicChk.setSelected(false);
						sportsChk.setSelected(false);
						readChk.setSelected(false);
						actChk.setSelected(false);
						cookChk.setSelected(false);
						gardenChk.setSelected(false);
					}
				else if (e.getSource() == cancBut)
					{	System.exit(0);	}
				else
					{	// Write code to add the record to the data base which generates
						//key automatically.
						System.out.println("Record being added.");
					}
			}
	}
class ChandraPhoto extends JPanel
	{	BufferedImage bi;
		public ChandraPhoto()
			{	String path = "E:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\classes\\Images\\chandu3.jpg";
				File img = new File(path);
				BufferedImage temp=null;
				
				try {	bi = ImageIO.read(img);
						this.setSize(bi.getWidth(), bi.getHeight());
					}
				catch (Exception ie)
					{	ie.printStackTrace();	}
				System.out.println("Width:"+bi.getWidth()+" Height:"+bi.getHeight());
			}
		public void paint(Graphics g)
			{	g.drawImage(bi, 0, 0, this);	}
		public int getWidth()
			{	return bi.getWidth();	}
		public int getHeight()
			{	return bi.getHeight();	}
	}