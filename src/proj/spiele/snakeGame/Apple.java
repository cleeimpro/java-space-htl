package proj.spiele.snakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import lib.awt.form.Rechteck;

public class Apple {
	
	/** Name des Apfels */
	private String name;
	/** Groeße des Apfels */
	private int width;
	/** X-Achse des Fensters */
	private int screenWidth;
	/** Y-Achse des Fensters */
	private int screenHeight;
	/** Farbe des Apfels */
	private Color colorApple;
	/** Position des Apfels */
	private Point position;
	/** QuadratObjekt welches den Apfel beinhaltet */
	private Rechteck apple;
	
	public Apple(int width, int sW, int sH) {
		this.name = "Apfel";
		this.width = width;
		this.screenWidth = sW;
		this.screenHeight = sH;
		this.colorApple = Color.RED;
		spawnNew();
	}
	
	/**
	 * Laesst den Apfel irgendwo am Spielfeld neu spawnen
	 */
	public void spawnNew() {
		position = new Point(width*(int)(Math.random()*(screenWidth/width-1))+ width/2,
							width*(int)(Math.random()*(screenHeight/width-1))+ width/2+22);

		apple = new Rechteck(position, width);
		apple.setColor(colorApple);
	}
	
	/**
	 * Zeichnet den Apfel
	 */
	public void paint(Graphics g) {
		apple.paint(g);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Color getColorApple() {
		return colorApple;
	}

	public void setColorApple(Color colorApple) {
		this.colorApple = colorApple;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Rechteck getApple() {
		return apple;
	}

	public void setApple(Rechteck apple) {
		this.apple = apple;
	}
	
	

}
