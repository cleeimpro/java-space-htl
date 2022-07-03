package proj.spiele.snakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import lib.Site;
import lib.awt.FensterAnimated;
import lib.awt.form.Rechteck;

public class Snake {
	
	//Parameter
	/** Name der Schlange*/
	private String name;
	/** Breite der Schlange */
	private int width;
	/** Position des Schlangenkopfes */
	private Point position;
	/** Farbe der Schlange */
	private Color colorBody;
	/** Farbe des Kopfes */
	private Color colorHead;
	/** Dauer der Pause bis zur naechsten Bewegung */
	private int sleeptime;
	/** Startlaenge der Schlange */
	private int startLength;
	/** Richtung der Schlange */
	private Site direction;
	/** Vector aus Rechtecken welche die Schlange darstellt */
	private Vector<Rechteck> snake;
	
	// Konstruktor
	/** Erzeugt eine Schlange mit Defaultwerten */
	public Snake(Point sP, int width, int length) {
		this.name = "Snake";
		this.position = sP;
		this.width = width;
		this.colorBody = Color.BLUE;
		this.colorHead = new Color(0,0,100);
		this.sleeptime = 150;
		this.startLength = length;
		this.direction = Site.RIGHT;
		this.snake = new Vector<Rechteck>();
		
		//Kopf erzeugen
		this.addSnakeElement(sP,width);
		snake.get(0).setOldPosition(new Point((int)sP.getX(), (int)sP.getY()-width));
		snake.get(0).setHintergrund(colorHead);
		//Koerper erzeugen
		for(int i = 1; i<startLength;i++) {
			addSnakeElement(width-4);
			snake.get(i).setOldPosition(new Point((int)sP.getX(), (int)sP.getY()-width*(i+1)));
		}
	}
	
	/** 
	 * Fuegt beim Aufrufen ein neues Rechteck an die Schlange an 
	 * @param s Kantenlaenge des neuen Rechteckes
	 * @param c Farbe des neuen Rechteckes
	 */
	public void addSnakeElement(int s) {	
		snake.add(new Rechteck(snake.get(snake.size()-1).getOldPosition(),s));
	}
	
	/** 
	 * Fuegt beim Aufrufen ein neues Rechteck an die Schlange an 
	 * @param p Punkt an welchem der Mittelpunkt des neuen Rechteckes sein soll
	 * @param s Kantenlaenge des neuen Rechteckes
	 * @param c Farbe des neuen Rechteckes
	 */
	public void addSnakeElement(Point p, int s) {	
		snake.add(new Rechteck(p,s));
	}
	
	/**
	 * Bewegt die gesamte Schlange, also den Kopf und alle weitern Glieder ruecken nach.
	 * @param x dX
	 * @param y dY
	 */
	public void move(int x, int y) {
		snake.get(0).setOldPosition(snake.get(0).getPosition());
		snake.get(0).move(x,y);
		for(int i=1;i<snake.size();i++) {
			snake.get(i).setOldPosition(snake.get(i).getPosition()); 
			snake.get(i).move(snake.get(i-1).getOldPosition());
		}
		position = snake.get(0).getPosition();
	}
	
	/**
	 * Bewegt die Schlange in die Richtung welche in der Direction gespeichert ist
	 */
	public void moveSnake() {
		switch(this.direction) {
		case UP: move(0, -width);break;
		case DOWN: move(0, width);break;
		case LEFT: move(-width, 0);break;
		case RIGHT: move(width, 0);break;
		case STOPP: break;
		}
	}
	
	/**
	 * Ueberprueft ob die Schlange den Apfel gefressen hat
	 * @return true wenn der Apfel gefressen ist
	 */
	public boolean catchTheApple(Apple a){
		if(((a.getPosition().getX()-this.position.getX())*(a.getPosition().getX()-this.position.getX())+
			(a.getPosition().getY()-this.position.getY())*(a.getPosition().getY()-this.position.getY())<
			(a.getWidth()/2+this.width/2)*(a.getWidth()/2+this.width/2)))
			return true;
		return false;
	}
	
	/**
	 * Ueberprueft ob die Schlange in den Rand gefahren ist
	 * @return true wenn sie angefahren ist
	 */
	public boolean catchTheBorder(int bX, int bY) {	
		if(this.position.getX()<0||this.position.getX()>bX||
			this.position.getY()<22||this.position.getY()>bY)
			return true;	
		return false;
	}
	
	/**
	 * Ueberprueft ob sich die Schlange selber in Schwanz beisst
	 * @return true wenn sie sich gebissen hat
	 */
	public boolean catchTheSnake() {
		for(int i = 1; i<this.snake.size();i++) {
			if(this.position.equals(this.snake.get(i).getPosition())){
				snake.get(i).setHintergrund(Color.PINK);
				return true;
			}
		}		
			return false;
	}
	
	
	
	/** zeichnet die Schlange neu */
	public void paint(Graphics g) {
		
		// Zeichnet die einzelnen Schlangenglieder neu
		for(int i = 0; i<snake.size();i++)
			snake.get(i).paint(g);
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

	public int getLength() {
		return snake.size();
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Color getColorBody() {
		return colorBody;
	}

	public void setColorBody(Color colorBody) {
		this.colorBody = colorBody;
		for(int i = 1; i<snake.size(); i++) {
			snake.get(i).setHintergrund(colorBody);
		}
	}

	public Color getColorHead() {
		return colorHead;
	}

	public void setColorHead(Color colorHead) {
		this.colorHead = colorHead;
		snake.get(0).setHintergrund(colorHead);
	}

	public int getSleeptime() {
		return sleeptime;
	}

	public void setSleeptime(int sleeptime) {
		this.sleeptime = sleeptime;
		FensterAnimated.SLEEPTIME = sleeptime;
	}

	public Site getDirection() {
		return direction;
	}

	public void setDirection(Site direction) {
		this.direction = direction;
	}

	
}
