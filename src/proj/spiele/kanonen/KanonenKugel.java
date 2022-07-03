package proj.spiele.kanonen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class KanonenKugel {

	/** Gravitationskonstante */
	public static final double G = 0.001;
	/** Reflexionskonstante */
	public static final double RF = 0.3;

	/** X - Mittelpunkt */
	private double mx;
	/** Y - Mittelpunkt */
	private double my;
	/** Geschwindigkeit X */
	private double vx;
	/** Geschwindigkeit Y */
	private double vy;
	/** Beschleunigung X */
	private double ax;
	/** Beschleunigung Y */
	private double ay;
	/** Radius */
	private double r;
	/** Farbe vom Ball */
	private Color color;
	/** Bild der Kugel */
	private Image img;
	/** true, wenn kugel gezeichnet werden soll */
	private boolean visible = true;
	/** Geschwindigkeit*/
	private double speed;
	/** true, wenn kugel gefaerlich ist */
	private boolean danger = true;
	/** Schadensmultiplikator */
	private double damageMulti = 1;
	/** Markierung wenn der Ball nach obend das Spielfeld verlässt */
	private boolean marker;

	// AlteElemente
	private double mxa, mya, vxa, vya;

	// Konstruktor 1
	public KanonenKugel(double mx, double my, double vx, double vy, double r, Image img) {
		this.r = r;
		this.mx = mx;
		this.my = my;
		this.vx = vx;
		this.vy = vy;
		this.ax = 0;
		this.ay = G;
		this.color = Color.BLACK;
		this.img = img;
	}

	// Konstruktor 2
	public KanonenKugel(double mx, double my, double vx, double vy, double r, Color color) {
		this.r = r;
		this.mx = mx;
		this.my = my;
		this.vx = vx;
		this.vy = vy;
		this.ax = 0;
		this.ay = G;
		this.color = color;
	}

	/**
	 * Brechnet das physikalische Verhalten der Kanonenkugel
	 * @param dT Zeit zwischen repaint in ms
	 */
	public void calcPhysics(double dT) {
		// Bestimmung der alten Werte
		mxa = mx;
		mya = my;
		vxa = vx;
		vya = vy;
		// Differenzengleichungen
		mx = mxa + vxa * dT;
		vx = vxa + ax * dT;
		my = mya + vya * dT;
		vy = vya + ay * dT;
	}
	
	/**
	 * Berechnet die Reflexionen im Fenster
	 * @param width Breite des Fensters
	 * @param height Hoehe des Fensters
	 */
	public void calcReflex(double width, double height) {
		// Boden
		if(my+r>height){
			my = 2*height-2*r-my;
			vx*=RF;
			vy*=-RF;
		}
		
		// Rechte Wand
		if(mx+r>width) {
			mx = 2*width-2*r-mx;
			vy*=RF;
			vx*=-RF;
		}
		
		// Linke Wand
		if(mx-r < 0) {
			mx = -mx+2*r;
			vy*=RF;
			vx*=-RF;
		}
		
		// Oben
		if (my + r < 22)
			marker = true;
		else
			marker = false;
	}

	/**
	 * Ueberprueft ob die Kugel in Bewegung ist
	 * @return true, wenn Kanonenkugel in Bewegung ist
	 */
	public boolean ifMotion() {
		
		//TODO Erkennen wann Kugel sich wirklich nichtmehr bewegt
		System.out.println("vx: " + vx + "| vxa: " + vxa + "vy: " + vy + "| vya: " + vya);

		return !(Math.abs(vx) < 0.01 && Math.abs(vxa) < 0.01 && Math.abs(vy) < 0.01 && Math.abs(vya) < 0.01);
	}

	/**
	 * Zeichnet die Kanonenkugel oder den Marker fuer die Position der Kugel wenn my<0
	 * @param g Graphicshandler
	 */
	public void paint(Graphics g) {	
		if (visible) {
			if (my>0) {
				if (img == null) {
					g.setColor(color);
					g.fillOval((int) (mx - r), (int) (my - r), (int) (2 * r), (int) (2 * r));
				} else {
					g.drawImage(img, (int) (mx - r), (int) (my - r), (int) (2 * r), (int) (2 * r), null);
				}
			}else {
				int[] temp1 = { (int) mx - 4, (int) mx + 4, (int) mx };
				int[] temp2 = { 22 + 8, 22 + 8, 22 };
				g.fillPolygon(temp1, temp2, 3);
			}
		}
	}

	public void setFlightAngle(int rot) {
		vx = Math.cos(Math.toRadians(rot));
		vy = -Math.sin(Math.toRadians(rot));
	}

	public double getMx() {
		return mx;
	}

	public void setMx(double mx) {
		this.mx = mx;
	}

	public double getMy() {
		return my;
	}

	public void setMy(double my) {
		this.my = my;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getAx() {
		return ax;
	}

	public void setAx(double ax) {
		this.ax = ax;
	}

	public double getAy() {
		return ay;
	}

	public void setAy(double ay) {
		this.ay = ay;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double s) {
		this.speed = s;
		vx *= s;
		vy *= s;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}

	public double getDamageMulti() {
		return damageMulti;
	}

	public void setDamageMulti(double damageMulti) {
		this.damageMulti = damageMulti;
	}

	public boolean isMarker() {
		return marker;
	}

	public void setMarker(boolean marker) {
		this.marker = marker;
	}

	public double getMxa() {
		return mxa;
	}

	public void setMxa(double mxa) {
		this.mxa = mxa;
	}

	public double getMya() {
		return mya;
	}

	public void setMya(double mya) {
		this.mya = mya;
	}

	public double getVxa() {
		return vxa;
	}

	public void setVxa(double vxa) {
		this.vxa = vxa;
	}

	public double getVya() {
		return vya;
	}

	public void setVya(double vya) {
		this.vya = vya;
	}


}
