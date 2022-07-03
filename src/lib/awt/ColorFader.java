package lib.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import lib.awt.form.Rechteck;

public class ColorFader extends Fenster{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int width;
	private int height;
	private int borderWidth;
	private static Color borderColor = new Color(57, 53, 53);
	private static Color sliderColor;
	private Color currentColor;
	private Color c1, c2;
	private boolean dragged;
	private Rechteck slider;
	
	public ColorFader(int x, int y, int width, int height, Color c1, Color c2) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.borderWidth = 2;
		this.c1 = c1;
		this.c2 = c2;
		this.currentColor = c1;
		this.slider = new Rechteck(x-3+3,y-3+height/2+3,7,height+6);
		this.dragged = false;
		this.slider.setColor(sliderColor);
		
	}
	
	public void mousePressed(MouseEvent e) {
		if(e.getX() > this.x -2 && e.getY() > this.y && e.getX() < this.x+this.width && e.getY() <this.y+this.height) {
			slider.setMx(e.getX());
			dragged = true;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(dragged && e.getX() > this.x -2 && e.getX() < this.x+this.width) {
			slider.setMx(e.getX());
			currentColor = new Color((int)(c1.getRed() + (float)(c2.getRed() - c1.getRed()) / width * (slider.getMx()-this.x)),
					(int)(c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) / width * (slider.getMx()-this.x)),
					(int)(c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) / width * (slider.getMx()-this.x)));
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		dragged = false;
		
		currentColor = new Color((int)(c1.getRed() + (float)(c2.getRed() - c1.getRed()) / width * (slider.getMx()-this.x)),
				(int)(c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) / width * (slider.getMx()-this.x)),
				(int)(c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) / width * (slider.getMx()-this.x)));
		
		
		System.out.println(currentColor);
		
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i = 0; i < width; i++) {
			
			
			Color c = new Color((int)(c1.getRed() + (float)(c2.getRed() - c1.getRed()) / width * i),
					(int)(c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) / width * i),
					(int)(c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) / width * i));
			
			g.setColor(c);
			g.drawLine(x+i, y, x+i, y+height);
		}
		
		g2.setStroke(new BasicStroke(borderWidth));
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
		
		slider.setColor(sliderColor);
		slider.paint(g);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
		
		double maxValue[] = {(int)(c1.getRed() + (float)(c2.getRed() - c1.getRed())),(int)(c1.getGreen() + (float)(c2.getGreen() - c1.getGreen())),(int)(c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()))};
		
		int x = (int) lib.Felder.getMaxValue(maxValue);
		
		for(int i = 0; i < x; i++) {
			if(currentColor.getRed() != 0 && (int)(x / width * i) >= currentColor.getRed() 
					|| currentColor.getGreen() != 0 && (int)(x / width * i) >= currentColor.getGreen() 
					|| currentColor.getBlue() != 0  && (int)(x / width * i) >= currentColor.getBlue()) {
				
				slider.setMx(this.x+(int)(x / width * i));
				System.out.println("Check");
				break;
			}
		}
	}

	public Color getC1() {
		return c1;
	}

	public void setC1(Color c1) {
		this.c1 = c1;
	}

	public Color getC2() {
		return c2;
	}

	public void setC2(Color c2) {
		this.c2 = c2;
	}

	public static Color getBorderColor() {
		return borderColor;
	}

	public static void setBorderColor(Color borderColor) {
		ColorFader.borderColor = borderColor;
	}

	public static Color getSliderColor() {
		return sliderColor;
	}

	public static void setSliderColor(Color sliderColor) {
		ColorFader.sliderColor = sliderColor;
	}

}
