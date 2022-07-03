package lib.awt;

import java.awt.Graphics;
import java.util.Vector;

public class Wall {
	/** Linke obere Ecke */
	private int x,y;
	/** Hoehe und Breite der Wand */
	private int width, height;
	/** Vector mit den Bricks */
	private Vector<Brick> wall;
	/** Anzahl der Bricks */
	private int numberOfBricks;
	/** Zeitdelay bis zum naechsten Brick */
	private int nextBrickTime = 50; 
	/** Timer bis zum naechsten Brick */
	private int brickTimer = 0;
	/** Breite eines Bricks */
	private int brickW = 30;
	/** Breite eines Width */
	private int brickH = 20;
	
	//Konstruktor
	public Wall(int x, int y, int width, int height, int n) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.numberOfBricks = n;
		wall = new Vector<Brick>();
		addBrick();
	}
	
	/**
	 * Fuegt einen neuen Brick zum Vector Wall hinzu
	 */
	public void addBrick() {
		wall.add(new Brick(x+(int)(Math.random()*width/brickW*2)*brickW/2,y,brickW,brickH));
	}
	
	/**
	 * Bewegt alle Bricks der Wall nach unten bis sie aufeinander stehen
	 * @return true, solange Bricks bewegt werden koennen
	 */
	public boolean bricksMove() {
		int temp = wall.get(wall.size()-1).getMy();
		for(int i = 0; i<wall.size();i++) {
			wall.get(i).move(0, 50);
			if(wall.get(i).getMy()+wall.get(i).getHeight()/2>height) {
				wall.get(i).setMy(height-wall.get(i).getHeight()/2);	
			}
			for(int j = 0; j<i; j++) {
				if(wall.get(i).getMy()+wall.get(i).getHeight()/2>wall.get(j).getMy()-wall.get(j).getHeight()/2&&
					wall.get(i).getMx()>wall.get(j).getMx()-brickW &&
					wall.get(i).getMx()<wall.get(j).getMx()+brickW) {
					wall.get(i).setMy(wall.get(j).getMy()-brickH);
				}
			}
		}
		if(temp == wall.get(wall.size()-1).getMy())
			return false;
		return true;
	}
	
	/**
	 * Zeichnet alle Bricks und laesst eine Wand entstehen
	 * @param g Graphicshandler
	 */
	public void paint(Graphics g) {
		brickTimer+=20;
		if(brickTimer>=nextBrickTime && wall.size()<=numberOfBricks) {
			brickTimer = 0;
			addBrick();
		}
		
		for(Brick b:wall)
			b.paint(g);
		
		//g.drawRect(x,y,w,h); // Begrenzungsrahmen der Wall
	}

	public int getNumberOfBricks() {
		return numberOfBricks;
	}

	public void setNumberOfBricks(int numberOfBricks) {
		this.numberOfBricks = numberOfBricks;
	}

	public Vector<Brick> getWall() {
		return wall;
	}

	public void setWall(Vector<Brick> wall) {
		this.wall = wall;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNextBrickTime() {
		return nextBrickTime;
	}

	public void setNextBrickTime(int nextBrickTime) {
		this.nextBrickTime = nextBrickTime;
	}

	public int getBrickTimer() {
		return brickTimer;
	}

	public void setBrickTimer(int brickTimer) {
		this.brickTimer = brickTimer;
	}

	public int getBrickW() {
		return brickW;
	}

	public void setBrickW(int brickW) {
		this.brickW = brickW;
	}

	public int getBrickH() {
		return brickH;
	}

	public void setBrickH(int brickH) {
		this.brickH = brickH;
	}
	
}
