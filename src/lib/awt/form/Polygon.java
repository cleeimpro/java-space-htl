package lib.awt.form;

import java.awt.Color;
import java.awt.Graphics;

public class Polygon extends Form{
	
	private int[] x,y;
	private int c;
	private boolean fuellung;
	private Color hintergrund;
	
	public Polygon(int[] x, int[] y, int corners) {
		this.x = x;
		this.y = y;
		this.c = corners;
		this.fuellung = true;
	}

	@Override
	public void move(double dx, double dy) {
		for (int i = 0; i < x.length; i++) {
			x[i] += dx; 
		}
		for (int i = 0; i < y.length; i++) {
			y[i] += dy; 
		}
	}

	@Override
	public void paint(Graphics g) {
		if(fuellung) {
			g.setColor(hintergrund);
			g.fillPolygon(x,y,c);	
		}
		else {
			g.drawPolygon(x,y,c);
		}
	}

	@Override
	public void setColor(Color c) {
		this.hintergrund = color;
	}
	
}
