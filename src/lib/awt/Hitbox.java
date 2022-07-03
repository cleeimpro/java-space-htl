package lib.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Hitbox {
	/** Ankerpunkt x-Achse */
	private int ax;
	/** Ankerpunkt y-Achse */
	private int ay;
	/** Seite A */
	private int sx;
	/** Seite B */
	private int sy;
	/** Differenz von Nullpunkt zu Ankerpunkt */
	private int dx;
	/** Differenz von Nullpunkt zu Ankerpunkt */
	private int dy;
	/** Drehung in math pos Richtung in Grad*/
	private int angle;
	
	public Hitbox(int sx, int sy, int ax, int ay, int dx, int dy, int rot) {
		this.sx = sx;
		this.sy = sy;
		this.ax = ax;
		this.ay = ay;
		this.dx = dx;
		this.dy = dy;
		this.angle = rot;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(ax, ay);
		g2.rotate(Math.toRadians(-angle));
		g.drawRect(-dx, -dy, sx, sy);
		g2.rotate(Math.toRadians(angle));
		g2.translate(-ax, -ay);
	}

	public int getAx() {
		return ax;
	}

	public void setAx(int ax) {
		this.ax = ax;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public int getSx() {
		return sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	
	
}
