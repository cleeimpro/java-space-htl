package proj.spiele;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import lib.awt.FensterAnimated;
import lib.awt.form.Kreis;
import lib.awt.form.Rechteck;


public class SnakeMain extends FensterAnimated{
	
	
	/** serialVersionUID */
	private static final long serialVersionUID = -7495577955137121394L;
	
	/** Moeglichen Richtung der Schlange */
	private static enum Direction{UP,DOWN,LEFT,RIGHT,STOPP}
	/** Vector mit den einzelnen Rechtecken der Schlange */
	private Vector<Rechteck> snake = new Vector<Rechteck>();
	/** Aktuelle Richtung der Schlange */
	private Direction snakeDirection = Direction.RIGHT;
	/** Apfel */
	private Rechteck apple;
	/** Punkte */
	private int Points=1;
	/** Kreis fuer EndCard */
	private Kreis k;
	
	/** Kaestchen auf X-Achse */
	private final int casketX = 30; 
	/** Seitenlaenge eines Kaestchens */
	private final int c = this.getWidth()/casketX;
	/** Kaestchen auf Y-Achse */
	private final int casketY = this.getHeight()/c-1;
	
	/** Zufallsgenerator fuer Farben und sonstiges */
	private Random rand = new Random();
	/** Erste Farbe fuer Hintergrund */
	private Color c1 = new Color(rand.nextInt(50)+205, rand.nextInt(50)+205, rand.nextInt(50)+205);
	/** Zweite Farbe fuer Hintergrund */
	private Color c2 = new Color(rand.nextInt(50)+205, rand.nextInt(50)+205, rand.nextInt(50)+205);  
	
	
	/** Konstruktor
	 *  Stellt das Spielfeld bereit
	 */
	public SnakeMain() {
		super();
		addSnakeElement(new Point(c/2,c/2+22),c);
		snake.get(0).setHintergrund(new Color(0,0,100));
		spawnNewApple();
		FensterAnimated.SLEEPTIME = 150;
		this.setVisible(true);
	}
	
	/** 
	 * Fuegt beim Aufrufen ein neues Rechteck an die Schlange an 
	 */
	public void addSnakeElement(Point p, int s) {
		snake.add(new Rechteck(p,s));
		snake.get(snake.size()-1).setHintergrund(Color.BLUE);
	}
	
	/**
	 * Laesst den Apfel irgendwo am Spielfeld neu spawnen
	 */
	public void spawnNewApple() {
		apple = new Rechteck(c*(int)(Math.random()*casketX)+c/2,
							c*(int)(Math.random()*casketY)+c/2+22,
							c);
		apple.setHintergrund(Color.RED);
	}
	
	/**
	 * Ueberprueft ob die Schlange den Apfel gefressen hat
	 * @return true wenn der Apfel gefressen ist
	 */
	public boolean catchTheApple(){
		if(((apple.getPosition().getX()-snake.get(0).getPosition().getX())*(apple.getPosition().getX()-snake.get(0).getPosition().getX())+
			(apple.getPosition().getY()-snake.get(0).getPosition().getY())*(apple.getPosition().getY()-snake.get(0).getPosition().getY()) <
			(apple.getSeiteA()/2+snake.get(0).getSeiteA()/2)*(apple.getSeiteA()/2+snake.get(0).getSeiteA()/2)))
			return true;
		return false;
	}
	
	/**
	 * Ueberprueft ob die Schlange in den Rand gefahren ist
	 * @return true wenn sie angefahren ist
	 */
	public boolean catchTheBorder() {	
		if(snake.get(0).getPosition().getX()<0||snake.get(0).getPosition().getX()>c*casketX||
			snake.get(0).getPosition().getY()<22||snake.get(0).getPosition().getY()>c*casketY+22)
			return true;	
		return false;
	}
	
	/**
	 * Ueberprueft ob sich die Schlange selber in Schwanz beist
	 * @return true wenn sie sich gebissen hat
	 */
	public boolean catchTheSnake() {
		for(int i = 1; i<snake.size();i++) {
			if(((snake.get(i).getPosition().getX()-snake.get(0).getPosition().getX())*(snake.get(i).getPosition().getX()-snake.get(0).getPosition().getX())+
				(snake.get(i).getPosition().getY()-snake.get(0).getPosition().getY())*(snake.get(i).getPosition().getY()-snake.get(0).getPosition().getY()) <
				(snake.get(i).getSeiteA()/2+snake.get(0).getSeiteA()/2)*(snake.get(i).getSeiteA()/2+snake.get(0).getSeiteA()/2))) {
				snake.get(i).setHintergrund(Color.PINK);
				return true;
			}
		}		
			return false;
	}
	
	/**
	 * Bewegt die gesamte Schlange, also den Kopf und alle weitern Glieder ruecken nach.
	 * @param x dX
	 * @param y dY
	 */
	public void moveSnake(int x, int y) {
		snake.get(0).setOldPosition(snake.get(0).getPosition());
		snake.get(0).move(x,y);
		for(int i=1;i<snake.size();i++) {
			snake.get(i).setOldPosition(snake.get(i).getPosition()); 
			snake.get(i).move(snake.get(i-1).getOldPosition());
		}
		k = new Kreis(snake.get(0).getPosition(),this.getWidth());
	}
	
	/**
	 * ENDcard wird aufgerufen wenn der Spieler einen Fehler gemacht hat
	 */
	public void endCard(Graphics g) {
		
		FensterAnimated.SLEEPTIME = 20;
		Graphics2D g2 = (Graphics2D) g;
		
		// Weisser Kreis, welcher sich schliesst
		if(k.getR()-k.getLinienStaerke()/2>c*5) {
			k.setFuellung(false);
			k.setFarbe(Color.WHITE);
			k.setLinienStaerke(k.getLinienStaerke()+50);
			k.paint(g);
		}else {
			// Schriftzug mit Ergebniss
			k.paint(g);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("BookmanOldStyle",Font.PLAIN,40));
			g2.drawString("Runde beendet!", 250, this.getHeight()/2);
			g2.drawString("Punkte: "+Points, 250, this.getHeight()/2+50);
			FensterAnimated.RUN = false;
		}

	}
	
	/**
	 * KeyListener wird aktiviert wenn eine Taste gedrueckt wurde
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(snakeDirection != Direction.STOPP) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP: 
					if(snakeDirection!=Direction.DOWN) snakeDirection = Direction.UP; break;
				case KeyEvent.VK_DOWN: 
					if(snakeDirection!=Direction.UP) snakeDirection = Direction.DOWN; break;
				case KeyEvent.VK_LEFT: 
					if(snakeDirection!=Direction.RIGHT) snakeDirection = Direction.LEFT; break;
				case KeyEvent.VK_RIGHT: 
					if(snakeDirection!=Direction.LEFT) snakeDirection = Direction.RIGHT; break;
			}
		}
			switch(e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE: 
					System.exit(0); break;
				case KeyEvent.VK_SPACE:
					switch(snakeDirection) {
						case UP: moveSnake(0, -c*3);break;
						case DOWN: moveSnake(0, c*3);break;
						case LEFT: moveSnake(-c*3, 0);break;
						case RIGHT: moveSnake(c*3, 0);break;
						case STOPP: break;
					}
			}
	}
	
	/** zeichnet das Fenster neu */
	@Override
	public void paint(Graphics g) {
		
		// Bewegt die Schlange in eine Richtung
		switch(snakeDirection) {
			case UP: moveSnake(0, -c);break;
			case DOWN: moveSnake(0, c);break;
			case LEFT: moveSnake(-c, 0);break;
			case RIGHT: moveSnake(c, 0);break;
			case STOPP: break;
		}
		
		// Setzt neuen Apfel wenn die Schlange den Apfel gefressen hat
		if(catchTheApple()) {
			addSnakeElement(snake.get(snake.size()-1).getOldPosition(),c-4);
			spawnNewApple();
			Points++;
		}
		
	
		// Spielfeld Hintergrund
		for(int i = 0; i < casketY+1; i++) {
			for(int j = 0; j < casketX+1; j++) {
				if((i+j)%2==0) {
					g.setColor(c1);
					g.fillRect(j*c, i*c+22, c, c);
				}else {
					g.setColor(c2);
					g.fillRect(j*c, i*c+22, c, c);
				}
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(0,0,casketX*c, casketY*c+22);
		
		
		//Punkte Anzeige
		g.setColor(Color.BLACK);
		g.drawString("Länge: "+Points, c/2, c/2+22+4);
		
		// Zeichnet die einzelnen Schlangenglieder neu
		for(int i = 0; i<snake.size();i++)
			snake.get(i).paint(g);
		
		// Zeichnet Apfel
		apple.paint(g);
		
		// Haelt das Spiel an wenn die Schlange angefahren ist
		if(catchTheBorder()||catchTheSnake()) {
			snakeDirection=Direction.STOPP;
			snake.get(0).setHintergrund(Color.PINK);
			endCard(g);
		}
		
		
	}
	
	/**
	 * Erzeug das Spiel Snake
	 * @param args
	 */
	public static void main(String[] args) {
		new SnakeMain();
	}

}
