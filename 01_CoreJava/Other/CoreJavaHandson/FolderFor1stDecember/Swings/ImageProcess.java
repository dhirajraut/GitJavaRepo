import java.applet.Applet;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ImageProcess	extends Applet //implements Runnable
	{ 	String [] jpgList = {"Blue hills.jpg", "Water lilies.jpg", "Winter.jpg", "Sunset.jpg"};
		Image  image[] = new Image[4];
		int n = 0;

	      public void init()
	      	{	for(int i=0; i<image.length; i++)
	      			image[i] = getImage(getDocumentBase(), jpgList[i]);
	      	}

	  	  public void paint(Graphics g)
	  	  	{ g.drawImage(image[n], 0, 0, this); }

	  	  public void start()
		  	  { Timer timer;
		  	  	timer = new Timer();
		  	  	timer.schedule(new schedular(), 0, 1000l);
		  	  }

	  	  class schedular extends TimerTask
	  	  	{	public void run()
	  	  			{	n = (n>2)? 0: n+1;
	  	  				repaint();
		  	  		}
	  	  	}
	}
