import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TestImages
	{	public static void main(String[] args)
			{	JFrame f2 = new JFrame();
				MyPhoto ja2 = new MyPhoto();
				
				f2.getContentPane().add(new MyPhoto());
				
				f2.setSize(200, 200);
				f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f2.setVisible(true);
			}
	}

class ScrollForPhoto extends JScrollPane
	{	public ScrollForPhoto(MyPhoto mf)
			{	//super(mf, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				this.setViewportView(mf);

		 		this.setVisible(true);
		 		this.validate();
			}
	}

class MyPhoto extends JPanel
	{	BufferedImage bi;
		public MyPhoto()
			{	String path = "E:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\classes\\Images\\chandu2.jpg";
				File img = new File(path);
				BufferedImage temp;
				
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
