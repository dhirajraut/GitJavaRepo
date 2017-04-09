import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class ImageTextWithApplet extends JApplet
	{	private BufferedImage bi;
		
		public void init()
			{	bi = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D)bi.createGraphics();
				g2.setFont(new Font("Serif", Font.PLAIN, 36));
				g2.drawString("Hello BufferedImage", 50, 50);
			}
		
		public void paint(Graphics g)
			{	Graphics2D g2 = (Graphics2D)g;
				g2.drawImage(bi, 0, 0, this);
			}
	}

class ImageWithPanel extends JPanel
{	private BufferedImage bi;
	Image itmp;
	public void init()
		{	
			String path = "C:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\classes\\Winter.jpg";
			File img = new File(path);
			BufferedImage temp;
			
			try {	temp= ImageIO.read(img);
					
					bi = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
					bi.getGraphics().drawImage(temp, 0, 0, this);
				}
			catch (Exception ie)
				{	ie.printStackTrace();	}
		}
	
	public void paint(Graphics g)
		{	
			g.drawImage(bi, 0, 0, this);
		}
}
public class Graphics2DForImages
	{	public static void main(String[] args)
			{	JFrame f1 = new JFrame();
				f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f1.setLocation(100, 100);
				f1.setSize(400, 400);
				ImageTextWithApplet ja1 = new ImageTextWithApplet();
				f1.getContentPane().add(ja1);
				ja1.init();
				f1.setVisible(true);
				
				JFrame f2 = new JFrame();
				f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f2.setSize(400, 400);
				f2.setLocation(500, 100);
				
				ImageWithPanel ja2 = new ImageWithPanel();
				f2.getContentPane().add(ja2);
				ja2.init();
				f2.setVisible(true);
			}
	}
