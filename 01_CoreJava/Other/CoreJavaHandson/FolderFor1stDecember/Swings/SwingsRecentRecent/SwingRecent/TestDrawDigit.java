import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawDigit extends JPanel
	{	Color colToHighlight = Color.BLUE;
		Color colToHide = this.getBackground();
		private int digit;
		int x, y;
		
		int [][] pattern = { { 1, 2, 3, 0, 5, 6, 7},/* 0 */
							 { 0, 0, 3, 0, 0, 0, 7},/* 1 */
							 { 0, 2, 3, 4, 5, 6, 0},/* 2 */
							 { 0, 2, 3, 4, 0, 6, 7},/* 3 */
							 { 1, 0, 3, 4, 0, 0, 7},/* 4 */
							 { 1, 2, 0, 4, 0, 6, 7},/* 5 */
							 { 1, 2, 0, 4, 5, 6, 7},/* 6 */
							 { 1, 2, 3, 0, 0, 0, 7},/* 7 */
							 { 1, 2, 3, 4, 5, 6, 7},/* 8 */
							 { 1, 2, 3, 4, 0, 6, 7},/* 9 */
							};
		int [][] barParams = { 	{ 00, 10, 10, 50}, /* Bar 1 */
								{ 10, 00, 30, 10}, /* Bar 2 */
								{ 40, 10, 10, 50}, /* Bar 3 */
								{ 10, 60, 30, 10}, /* Bar 4 */
								{ 00, 70, 10, 50}, /* Bar 5 */
								{ 10, 120, 30, 10},/* Bar 6 */
								{ 40, 70, 10, 50}  /* Bar 7 */
							};
		
		{ 	this.setLayout(null);
			this.setVisible(true);
			this.setSize(51, 164);
		}
		public void setBounds(int x, int y)
			{	super.setBounds(x, y, 51, 164);
				this.x = x;
				this.y = y;
			}
		
		public DrawDigit()
			{ 	digit = 0;	}
		
		public DrawDigit(int n)
			{	digit = n;	}

		public void paint(Graphics g)
			{	for(int i=0; i<7; i++)
					{	if (pattern[digit][i]==0)
							g.setColor(colToHide);
						else
							g.setColor(colToHighlight);
							g.fillRect(barParams[i][0]+x, barParams[i][1]+y, barParams[i][2], barParams[i][3]);
					}
			}
	
		public void dispDigit(int n)
			{	digit = n;
				this.repaint();
			}
	}

public class TestDrawDigit
	{	public static void main(String[] arg)
			{	JFrame fr = new JFrame();
				JPanel pan = new JPanel();
				
				DrawDigit d1 = new DrawDigit(1);d1.setBounds(0, 0);
				DrawDigit d2 = new DrawDigit(2);d2.setBounds(d1.getWidth()+30, 0);
				DrawDigit d3 = new DrawDigit(3);d3.setBounds((d1.getWidth()+30)*2, 0);
				
				fr.getContentPane().add(d1);
				fr.getContentPane().add(d2);
				fr.getContentPane().add(d3);
				
				fr.setSize(d1.getWidth()*3+60, d1.getHeight()+10);
				fr.setVisible(true);
				fr.validate();
			}
	}