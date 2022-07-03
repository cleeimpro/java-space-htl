package lib.awt.form;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Form{
	
	private double x1, y1, x2, y2;
	private Color c = Color.WHITE;
	private double linienStaerke;
	
	public Line(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.linienStaerke = 1;
	}
	
	public Line(double x1, double y1, double x2, double y2, double linienStaerke) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.linienStaerke = linienStaerke;
	}
	
	public Line(Point p1, Point p2) {
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.x2 = p2.getX();
		this.y2 = p2.getY();
	}
	
	public Line(Point p, double angle, double lenght) {
		this.x1 = p.getX();
		this.y1 = p.getY();
		this.x2 = Math.cos(angle)*lenght;
		this.y2 = Math.sin(angle)*lenght;
	}
	
	@Override
	public void move(double dx, double dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}

	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke((int)linienStaerke));
		g.setColor(c);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
	
	public double getLenght() {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}

	@Override
	public void setColor(Color c) {
		this.c = c;
		
	}
	
	
	
	
	

}
