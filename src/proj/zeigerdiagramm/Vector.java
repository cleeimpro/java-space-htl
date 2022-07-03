package proj.zeigerdiagramm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import lib.Complex;
import lib.awt.form.Polygon;

public class Vector {

	/** Komplexe Zahl des Vektors */
	private Complex complex;
	/** Start des Vektors */
	private int x, y;
	/** Farbe */
	private Color color = Color.BLUE;
	/** Staerke des Strichs */
	private int strokeSize = 2;
	/** True, wenn Vektor ausgewaehlt wurde */
	private boolean select = false;
	/** True, wenn ueber Vektor gehover wird */
	private boolean hover = false;

	public Vector(Complex c, int x, int y) {
		this.x = x;
		this.y = y;
		this.complex = c;
	}

	public void paint(Graphics g, int xDrawArea, int yDrawArea, int width, int height, double xScale, double yScale) {
		Graphics2D g2 = (Graphics2D) g;
		int x = (int)(this.x * xScale);
		int y = (int)(this.y * yScale);
		int re = (int)(complex.getRe() * xScale);
		int im = (int)(complex.getIm() * yScale);

		g2.setStroke(new BasicStroke(strokeSize));
		g.setColor(color);

		int x1, y1, x2, y2;

		// X1 Y1
		if(x + xDrawArea < 0) {
			x1 = this.x - xDrawArea;
			y1 = - y + xDrawArea * im / re;
		} else if(- y + yDrawArea < 0) {
			x1 =  x + (- y + yDrawArea) * re / im;
			y1 =  - y - yDrawArea;
		} else {
			x1 = x;
			y1 = -y;
		}
		
		

		x2 = x + re;

		y2 = - y - im;

		g.drawLine(x1, y1, x2, y2);

//		// Pfeil an der Spitze des Vektors
//		int[] xPoly = { (int) ((x + complex.getRe()) * xScale),
//				(int) ((x + complex.getRe()) * xScale - Math.cos(Math.toRadians(complex.getArg() - 15)) * 10),
//				(int) ((x + complex.getRe()) * xScale - Math.sin(Math.toRadians(90 - complex.getArg() - 15)) * 10) };
//
//		int[] yPoly = { (int) ((y - complex.getIm()) * yScale),
//				(int) ((y - complex.getIm()) * yScale + Math.sin(Math.toRadians(complex.getArg() - 15)) * 10),
//				(int) ((y - complex.getIm()) * yScale + Math.cos(Math.toRadians(90 - complex.getArg() - 15)) * 10) };
//
//		Polygon arrow = new Polygon(xPoly, yPoly, 3);
//		arrow.setColor(color);
//		arrow.paint(g);
	}

	public boolean clicked(MouseEvent e, int x, int y, double xScale, double yScale) {
		return e.getX() >= x - this.x * xScale && e.getX() <= x - this.x * xScale + complex.getRe() * xScale
				&& e.getY() >= y - this.y * yScale - complex.getIm() * yScale && e.getY() <= y - this.y * yScale;
	}

	public Complex getComplex() {
		return complex;
	}

	public void setComplex(Complex complex) {
		this.complex = complex;
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

	public int getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(int strokeSize) {
		this.strokeSize = strokeSize;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public double getDirection() {
		return -complex.getIm() / complex.getRe();
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

}
