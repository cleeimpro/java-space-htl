package lib.awt.form;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rechteck extends Form{
	
	/** Mittelpunkt X */
	private double mx;
	/** Mittelpunkt Y */
	private double my;
	/** Laenge des Rechtseck */
	private double seiteA;
	/** Laenge des Rechtseck */
	private double seiteB;
	/** Geschwindigkeit des Quadrats in px/20ms */
	private double speed;
	/** Farbe des Quadrats */
	private Color hintergrund = Color.BLUE;
	/** Rotation des Quadrates in grad/ms */
	private double rotation;
	/** Alte Position */
	private Point oldPosition;
	/** Gefuellt oder nicht */
	private boolean filled = true;
	/** True, wenn Border gezeichnet werden soll */
	private int borderWidth = 1;
	
	
	/** Konstruktor */
	public Rechteck(int mx, int my, int seiteA) {
		this.mx = mx;
		this.my = my;
		this.seiteA = this.seiteB = seiteA;
	}
	
	/** Konstruktor */
	public Rechteck(double mx, double my, double seiteA, double seiteB) {
		this.mx = mx;
		this.my = my;
		this.seiteA = seiteA;
		this.seiteB = seiteB;
	}
	
	/** Konstruktor */
	public Rechteck(Point p, int seiteA) {
		this.mx = p.getX();
		this.my = p.getY();
		this.seiteA = this.seiteB = seiteA;
		
	}
	
	@Override
	public void move(double dx, double dy) {
		mx += dx;
		my += dy;
	}
	
	public void move(Point p) {
		mx = p.getX();
		my = p.getY();
	}
	
	/** Zeichnet das Quadrat */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
	    g2d.setColor(hintergrund);
	    g2d.setStroke(new BasicStroke(1));
	    
	    if(filled)
	    	g2d.fillRect((int)(mx-seiteA/2), (int)(my-seiteB/2), (int)seiteA, (int)seiteB);
	    else {
	    	
	    	g2d.setStroke(new BasicStroke(borderWidth));
	    	g2d.drawRect((int)(mx-seiteA/2), (int)(my-seiteB/2), (int)seiteA, (int)seiteB);
	    	
	    }
	    
	}

	public double getSeiteA() {
		return seiteA;
	}

	public void setSeiteA(double d) {
		this.seiteA = d;
	}

	public double getSeiteB() {
		return seiteB;
	}

	public void setSeiteB(double d) {
		this.seiteB = d;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Color getHintergrund() {
		return hintergrund;
	}

	public void setHintergrund(Color hintergrund) {
		this.hintergrund = hintergrund;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Point getPosition() {
		return new Point((int)mx, (int)my);
	}

	public void setPosition(Point position) {
		this.mx = position.getX();
		this.my = position.getY();
	}
	
	public void setMx(double mx) {
		this.mx = mx;
	}
	
	public void setMy(double my) {
		this.my = my;
	}
	
	public double getMx() {
		return mx;
	}
	
	public double getMy() {
		return my;
	}

	public Point getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(Point oldPosition) {
		this.oldPosition = oldPosition;
	}

	@Override
	public void setColor(Color c) {
		this.hintergrund = c;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	
	

}
