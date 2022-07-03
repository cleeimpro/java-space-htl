package lib.awt.form;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Kreis extends Form{
	
	/** Mittelpunkt X-Koordinate */
	private double mx;
	/** Mittelpunkt Y-Koordinate */
	private double my;
	/** Radius des Kreise */
	private double r;
	/** Linienstärke */
	private double linienStaerke;
	/** Hintergrundfarbe des Kreise */
	private Color hintergrund;
	/** Farbe des Umfangs */
	private Color farbe;
	/** Ob der Kreis gefüllt werden soll */
	private boolean fuellung;
	/** Geschwindigkeit des Kreises in px/20ms */
	private int speed;
	/** Winkel beim Bewegen */
	private double angle;
	/** Sichtbarkeit */
	private boolean visible = true;
	
	
	/**
	 * Erzeugt einen Kries
	 * @param mx X-Koordinate
	 * @param my Y-Koordinate
	 * @param r Radius
	 */
	public Kreis(double mx, double my, double r) {
		this.mx = mx;
		this.my = my;
		this.r = r;
		this.linienStaerke = 1;
		this.farbe = Color.black;
		this.hintergrund = Color.WHITE;
		this.fuellung = true;
	}
	
	public Kreis(double mx, double my, double r, double linienStaerke) {
		this.mx = mx;
		this.my = my;
		this.r = r;
		this.linienStaerke = linienStaerke;
		this.farbe = Color.black;
		this.hintergrund = Color.WHITE;
		this.fuellung = false;
	}
	
	
	/**
	 * Erzeugt einen Kreis
	 * @param m Mittelpunkt
	 * @param r Radius
	 */
	public Kreis(Point m, double r) {
		setPoint(m);
		this.r = r;
		this.linienStaerke = 5;
		this.farbe = Color.black;
		this.hintergrund = Color.blue;
		this.fuellung = true;
		this.angle = 0;
	}
	
	/**
	 * Setzt den Mittelpunkt des Kreise
	 * @param p Punkt-Objekt mit X, Y Koordinate
	 */
	public void setPoint(Point p) {
		this.mx = p.x;
		this.my = p.y;
	}
	
	/**
	 * Zeichnet sich selber als Kreis
	 * @param g Graphicshandler mit allen Methoden zum Zeichnen
	 */
	public void paint(Graphics g) {
		if(visible) {
			Graphics2D g2 = (Graphics2D)g;
			g.setColor(farbe);
			g2.setStroke(new BasicStroke((int)linienStaerke));
			if(fuellung) {
				g.setColor(hintergrund);
				g.fillOval((int)mx-(int)r,(int)my-(int)r,2*(int)r,2*(int)r);
			}else
				g.drawOval((int)mx-(int)r,(int)my-(int)r,2*(int)r,2*(int)r);
		}
	}
	
	/**
	 * Aendert die Groeße des Kreise
	 * @param dr aenderung in px
	 */
	public void increaseR(int dr) {
		r+=dr;
		if(r <= 5)
			r=5;
	}
	
	/**
	 * Verschiebt den Kreis
	 * @param dx px in xRichtung
	 * @param dy px in yRichtung
	 */
	public void move(double dx, double dy) {
		mx+=dx;
		my+=dy;
	}
	
	/**
	 * Vergroessert die Linienstaerke
	 * @param dl aenderung
	 */
	public void increaseL(int dl) {
		linienStaerke += dl;
		if(linienStaerke >= r)
			linienStaerke = r;
		if(linienStaerke <= 0)
			linienStaerke = 1;
	}
	
	/**
	 * Beschleunigt den Kreis
	 * @param a deltaV in px/ms
	 */
	public void acceleration(int a) {
		speed+=a;
	}

	public double getMx() {
		return mx;
	}

	public void setMx(int mx) {
		this.mx = mx;
	}

	public double getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
	}

	public double getR() {
		return r;
	}

	public void setR(double d) {
		this.r = d;
	}
	

	public boolean isFuellung() {
		return fuellung;
	}

	public void setFuellung(boolean fuellung) {
		this.fuellung = fuellung;
	}


	public Color getHintergrund() {
		return hintergrund;
	}


	public void setHintergrund(Color hintergrund) {
		this.hintergrund = hintergrund;
	}


	public double getLinienStaerke() {
		return linienStaerke;
	}


	public void setLinienStaerke(double d) {
		this.linienStaerke = d;
	}


	public Color getFarbe() {
		return farbe;
	}


	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public double getAngle() {
		return angle;
	}


	public void setAngle(double d) {
		this.angle = d;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	@Override
	public void setColor(Color c) {
		this.hintergrund = c;
		this.farbe = c;
		
	}

	
	
	
	
	
	
	
	
}
