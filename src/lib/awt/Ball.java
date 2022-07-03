package lib.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Ball{
	
	public static final double G  = 0.001;
	public static final double RF = 0.8;
	
	/** Koordinate X*/
	private double x;
	/** Koordinate Y*/
	private double y;
	/** Geschwindigkeit X*/
	private double vx;
	/** Geschwindigkeit Y*/
	private double vy;
	/** Beschleunigung X*/
	private double ax;
	/** Beschleunigung Y*/
	private double ay;
	/** Radius */
	private double r;
	/** Farbe vom Ball */
	private Color c;
	
	private Image fb;
	
	private double t = 0;
	
	
	
	// AlteElemente
	private double xa,ya,vxa,vya;
	
	// Konstruktor
	public Ball(double r, double x, double y, double vx, double vy, Color c, Image fb) {
		this.r = r;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = 0;
		this.ay = G;
		this.c = c;
		this.fb = fb;
	}
	
	public Ball(double r, double x, double y, double vx, double vy, Color c) {
		this.r = r;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = 0;
		this.ay = G;
		this.c = c;
	}
	
	public void calcPhysics(double dT) {
		t+=dT;
		// Bestimmung der alten Werte
		xa = x; ya = y; vxa = vx; vya = vy;
		// Differenzengleichungen
		x = xa + vxa*dT;
		vx= vxa+ ax*dT;
		y = ya + vya*dT;
		vy= vya+ ay*dT;
	}
	
	public void calcReflex(double w, double h) {
		if(y+r>h){
			y = 2*h-2*r-y;
			vx*=RF;
			vy*=-RF;
		}
		
		if(x+r>w) {
			x = 2*w-2*r-x;
			vy*=RF;
			vx*=-RF;
		}
		
		if(x-r < 0) {
			x = -x+2*r;
			vy*=RF;
			vx*=-RF;
		}
			
	}
	
	// Paint
	public void paint(Graphics g) {
		if(fb == null) {
			g.setColor(c);
			g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
		}else {
			Graphics2D g2 = (Graphics2D) g;
			g2.translate((int) x, (int)y); 
			g2.rotate((t/1000 *2*Math.PI));
			g.drawImage(fb, (int)(-r),(int)(-r),(int)(2*r),(int)(2*r),null);
			g2.rotate((-t/1000 *2*Math.PI));
			g2.translate((int) -x, (int)-y);
		}
	}
	
	
	
	public void setAngle(int rot) {
		vx=Math.cos(Math.toRadians(rot));
		vy=-Math.sin(Math.toRadians(rot));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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

	public double getXa() {
		return xa;
	}

	public void setXa(double xa) {
		this.xa = xa;
	}

	public double getYa() {
		return ya;
	}

	public void setYa(double ya) {
		this.ya = ya;
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
