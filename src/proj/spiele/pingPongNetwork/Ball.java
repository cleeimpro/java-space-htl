package proj.spiele.pingPongNetwork;

import java.awt.Graphics;

import lib.awt.form.Kreis;

public class Ball {
	
	/** X Position vom Ball */
	private int mx;
	/** Y Position vom Ball */
	private int my;
	/** Ausrichtung des Balles */
	private int angle;
	/** px/20ms */
	private int speed;
	/** Breite des Bildschirms */
	private int screenWidth;
	/** Hoehe des Bidlschirms */
	private int screenHeight;
	/** Kreis aus welchem der Ball besteht */
	private Kreis k;

	
	/** Konstruktor */
	public Ball(int mx, int my, int sW, int sH) {
		this.mx = mx;
		this.my = my;
		this.screenWidth = sW;
		this.screenHeight = sH;
		this.speed = 6;
		k = new Kreis(mx,my,sH/40);
	}
	
	/** Bewegt den Ball im Richtigen Winkel */
	public void moveBall() {
		k.move((int)(speed*Math.cos(Math.toRadians(k.getAngle()))),(int)(speed*Math.sin(Math.toRadians(k.getAngle()))));
		mx = (int) k.getMx();
		my = (int) k.getMy();
	}

	/** Wenn der Ball die obere oder untere Wand berührt */
	public boolean isBorder() {
		if(0>k.getMy()-k.getR()||k.getMy()+k.getR()>screenHeight) {
			k.setAngle(360-k.getAngle());
			return true;
		}
		return false;
	}
	
	public void paint (Graphics g) {
		isBorder();
		moveBall();
		k.paint(g);
	}
	
	
	//GETTER und SETTER Methoden der Parameter
	public int getMx() {
		return mx;
	}

	public void setMx(int mx) {
		this.mx = mx;
		k.setMy(mx);
	}

	public int getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
		k.setMy(my);
	}

	public int getAngle() {
		return (int) k.getAngle();
	}

	public void setAngle(int angle) {
		this.angle = angle;
		k.setAngle(angle);
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public int getR() {
		return (int) k.getR();
	}

	public void setR(int r) {
		k.setR(r);
	}
	
}
