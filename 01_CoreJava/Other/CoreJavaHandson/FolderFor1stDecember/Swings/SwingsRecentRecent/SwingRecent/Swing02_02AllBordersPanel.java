import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class Swing02_02AllBordersPanel extends JPanel
	{	JLabel lblBevel, lblEmpty, lblEtched, lblLine, lblMatte, lblTitled;
		public Swing02_02AllBordersPanel()
			{	lblBevel = new JLabel("Bevel");
				lblEmpty= new JLabel("Empty");
				lblEtched= new JLabel("Etched");
				lblLine= new JLabel("Line");
				lblMatte= new JLabel("Matte");
				lblTitled= new JLabel("Titled");
				
				lblBevel.setBorder(new BevelBorder(BevelBorder.RAISED));
				lblEmpty.setBorder(new EmptyBorder(10, 10,10, 10));
				lblEtched.setBorder(new EtchedBorder());
				lblLine.setBorder(new LineBorder(Color.orange, 3));
				lblMatte.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLUE));
				lblTitled.setBorder(new TitledBorder("Titled"));
				
				add(lblBevel);
				add(lblEmpty);
				add(lblEtched);
				add(lblLine);
				add(lblMatte);
				add(lblTitled);
			}
		
		public static void main(String [] argv)
			{	JFrame jf = new JFrame();
				jf.setBounds(200, 200, 300, 90);
				Swing02_02AllBordersPanel abp = new Swing02_02AllBordersPanel(); 
				jf.getContentPane().add(abp);
				jf.setSize(300, 90);
				jf.setVisible(true);
			}

	}
