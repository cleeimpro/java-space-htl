package lib.awt;

import java.awt.Graphics;

public class Brick {
	
	/** X - , und Y - Position des Bricks */
	private int x, y;
	/** Breite und Hoehe des Bricks */
	private int width, height;

	//Konstruktor
	public Brick(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	/**
	 * Brick wird bewegt
	 * @param dx deltaX
	 * @param dy deltaY
	 */
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	/**
	 * Zeichnet den Brick
	 * @param g Graphicshandler
	 */
	public void paint(Graphics g) {
		g.fillRoundRect(x + 2, y + 2, width - 4, height - 4, 5, 5);
	}

	public int getMx() {
		return x + width / 2;
	}

	public void setMx(int mx) {
		this.x = mx - width / 2;
	}

	public int getMy() {
		return y + height / 2;
	}

	public void setMy(int my) {
		this.y = my - height / 2;
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

	public int getHeight() {
		return height;
	}

}
