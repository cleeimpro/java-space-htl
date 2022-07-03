package proj.fensterAWT;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import lib.awt.AwtButton;
import lib.awt.FensterAnimated;
import lib.awt.form.*;

/**
 * einfacher Timer mit min, s und ms
 * 
 * @author Clemens
 *
 */
public class Timer extends FensterAnimated {

	private static final long serialVersionUID = 1L;
	/** zaehler */
	private int counter = 0;
	/** aktueller zeahler */
	private int akTime = 0;
	/** Schriftart fuer AWT-Ausgabe */
	private Font font;
	/** Zeichenschrift */
	private Font iconFont;
	/** Schriftart fuer Rundenausgabe */
	private Font roundsFont;
	/** Hintergrundfarbe */
	private Color backgroundColor = new Color(57, 53, 53);
	/** Color */
	private Color mainColor = new Color(112, 112, 112);
	/** Headline, interessant bei Windows */
	private int hlh = 0;
	/** String mit der Information welches OS verwendet wird */
	private static String OS = System.getProperty("os.name").toLowerCase();
	/** Vector mit den Rundenzeiten in ms */
	private Vector<Integer> rounds = new Vector<Integer>();
	/** Button */
	private AwtButton playButton;
	private AwtButton pauseButton;
	private AwtButton stoppButton;
	private AwtButton exitButton;
	private AwtButton clockButton;
	private AwtButton dropDownButton;
	private AwtButton roundButton;

	// Konstruktor
	public Timer() {
		this.setTitle("Timer");

		if (OS.indexOf("win") >= 0) {
			hlh = 30;
		} else if (OS.indexOf("mac") >= 0) {
			hlh = 22;
		}

		this.setSize(650, 100 + hlh);
		this.setBackground(backgroundColor);

		File f = new File("./img/timer/clock.png");
		try {
			this.setIconImage(ImageIO.read(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = new Font("CenturyGothicStandart", Font.BOLD, 70);
		iconFont = new Font("ZapfDingbats", Font.PLAIN, 37);
		roundsFont = new Font("CenturyGothicStandart", Font.PLAIN, 20);

		int[][] playPoly = { { 12, 12, 37 }, { hlh + 12, hlh + 12 * 3, hlh + 25 } };
		int[][] dropDownPoly = { { 100 + 12, 100 + 37, 100 + 25 }, { hlh + 50 + 15, hlh + 15 + 50, hlh + 50 + 35 } };
		
		Form playForm = new Polygon(playPoly[0], playPoly[1], 3);
		Form pauseForm1 = new Rechteck(60+5,hlh+12+12.5,10,25);
		Form pauseForm2 = new Rechteck(80+5,hlh+12+12.5,10,25);
		Form stoppForm = new Rechteck(25,hlh+50+25,25,25);
		Form clockForm1 = new Kreis(125,hlh+25,12.5,3);
		Form clockForm2 = new Line(100 + 25,hlh+25,100+25,hlh+13,3);
		Form dropDownForm = new Polygon(dropDownPoly[0],dropDownPoly[1],3);
			
		playButton = new AwtButton(0, hlh, 50, 50, mainColor, backgroundColor, playForm);
		pauseButton = new AwtButton(50, hlh, 50, 50, mainColor, backgroundColor, pauseForm1, pauseForm2);
		stoppButton = new AwtButton(0, hlh+50, 50, 50, mainColor, backgroundColor, stoppForm);
		exitButton = new AwtButton(50, hlh+50, 50, 50, mainColor, backgroundColor, "✖", 37);
		clockButton = new AwtButton(100, hlh, 50, 50, mainColor, backgroundColor, clockForm1, clockForm2);
		dropDownButton = new AwtButton(100,hlh+50,50,50,mainColor,backgroundColor,dropDownForm);
		roundButton = new AwtButton(0, hlh + 350,this.getWidth(),50, mainColor, backgroundColor, "new round", 16);
		
		
		this.setVisible(true);
	}

	/**
	 * zeichnet die beiden analogen Uhren
	 * 
	 * @param g
	 */
	public void paintClock(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(6));
		g.setColor(Color.WHITE);
		// Uhr fuer Stunden, Minuten und Sekunden
		g.drawOval(150, hlh + 100 + 50, 200, 200);
		g.setColor(mainColor);
		int h = akTime / 3600000;
		// Stundenzeiger
		g.drawLine(250, hlh + 250, 250 + (int) (Math.sin(h / 60d % 1d * 2 * Math.PI) * 60),
				hlh + 250 + (int) (-Math.cos(h / 60d % 1d * 2 * Math.PI) * 60));
		int min = akTime / 60000;
		// Minutenzeiger
		g.drawLine(250, hlh + 250, 250 + (int) (Math.sin(min / 60d % 1d * 2 * Math.PI) * 70),
				hlh + 250 + (int) (-Math.cos(min / 60d % 1d * 2 * Math.PI) * 70));
		int s = akTime / 1000;
		g2.setStroke(new BasicStroke(4));
		g.setColor(Color.RED);
		// Sekundenzeiger
		g.drawLine(250, hlh + 250, 250 + (int) (Math.sin(s / 60d % 1d * 2 * Math.PI) * 80),
				hlh + 250 + (int) (-Math.cos(s / 60d % 1d * 2 * Math.PI) * 80));
		g.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(6));
		// Uhr fuer Millisekunden
		g.drawOval(400, hlh + 100 + 50 + 50, 100, 100);
		if (pauseButton.isActiv()) {
			g.setColor(mainColor);
			// Millisekundenzeiger
			g.drawLine(450, hlh + 250, 450 + (int) (Math.sin(akTime / 1000d % 1d * 2 * Math.PI) * 40),
					hlh + 250 + (int) (-Math.cos(akTime / 1000d % 1d * 2 * Math.PI) * 40));
		}
	}

	/**
	 * zeichnet alle Rundenzeiten untereinander
	 * 
	 * @param g
	 */
	public void paintRounds(Graphics g) {
		g.setFont(roundsFont);
		g.setColor(Color.WHITE);
		for (int i = rounds.size() - 1; i >= (rounds.size() > 10 ? rounds.size() - 10 : 0); i--) {
			String print = String.format("%02d:%02d:%02d.%03d", rounds.get(i) / 3600000,
					rounds.get(i) % 3600000 / 60000, rounds.get(i) % 60000 / 1000, rounds.get(i) % 1000);
			g.drawString(i + ". " + print, 150 + 20,
					hlh + 125 + (((rounds.size() > 10) ? 10 : rounds.size() - 1) - i) * 25);
		}
	}

	/**
	 * Timer wird schoen formatiert ausgegeben
	 */
	@Override
	public void paint(Graphics g) {
		if (playButton.isActiv() && !(dropDownButton.isActiv() && pauseButton.isActiv()))
			counter += 20;

		if (!pauseButton.isActiv() || dropDownButton.isActiv())
			akTime = counter;

		if (clockButton.isActiv() || dropDownButton.isActiv())
			this.setSize(650, 400 + hlh);
		else
			this.setSize(650, 100 + hlh);

		if (clockButton.isActiv()) {
			paintClock(g);
		} else if (dropDownButton.isActiv()) {
			paintRounds(g);
			int[][] dropDownPoly = { { 100 + 12, 100 + 37, 100 + 25 }, { hlh + 50 + 35,
				hlh + 35 + 50, hlh + 50 + 15 } };
			Form dropDownForm = new Polygon(dropDownPoly[0],dropDownPoly[1],3);
			dropDownButton.setContent(dropDownForm);
			roundButton.paint(g);
		} else {
			int[][] dropDownPoly = { { 100 + 12, 100 + 37, 100 + 25 }, { hlh + 50 + 15, hlh + 15 + 50, hlh + 50 + 35 } };
			Form dropDownForm = new Polygon(dropDownPoly[0],dropDownPoly[1],3);
			dropDownButton.setContent(dropDownForm);
		}

		g.setFont(font);
		g.setColor(Color.WHITE);
		String print = String.format("%02d:%02d:%02d.%03d", akTime / 3600000, akTime % 3600000 / 60000,
				akTime % 60000 / 1000, akTime % 1000);
		g.drawString(print, 150 + 20, hlh + font.getSize());
		g.setColor(mainColor);
		
		playButton.paint(g);
		pauseButton.paint(g);
		stoppButton.paint(g);
		exitButton.paint(g);
		clockButton.paint(g);
		dropDownButton.paint(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(playButton.isClicked(e))
			actionPressed(KeyEvent.VK_S);
		else if(pauseButton.isClicked(e))
			actionPressed(KeyEvent.VK_P);
		else if (stoppButton.isClicked(e))
			actionPressed(KeyEvent.VK_T);
		else if (exitButton.isClicked(e))
			actionPressed(KeyEvent.VK_ESCAPE);
		else if (clockButton.isClicked(e))
			actionPressed(KeyEvent.VK_C);
		else if (dropDownButton.isClicked(e))
			actionPressed(KeyEvent.VK_D);
		else if (roundButton.isClicked(e))
			actionPressed(KeyEvent.VK_SPACE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (stoppButton.isClicked(e))
			actionReleased(KeyEvent.VK_T);
		else if (exitButton.isClicked(e))
			actionReleased(KeyEvent.VK_ESCAPE);
		else if (clockButton.isClicked(e))
			actionReleased(KeyEvent.VK_C);
		else if (dropDownButton.isClicked(e))
			actionReleased(KeyEvent.VK_D);
		else if (roundButton.isClicked(e))
			actionReleased(KeyEvent.VK_SPACE);
	}

	/**
	 * Fuehrt Aktionen bei Tasten bzw. Mausdruck aus
	 */
	public void actionPressed(int e) {
		switch (e) {
		case KeyEvent.VK_P:
			if (playButton.isActiv())
				pauseButton.setActiv(!pauseButton.isActiv());
			break;
		case KeyEvent.VK_S:
			if (!playButton.isActiv()) {
				playButton.setActiv(true);
				counter = 0;
				pauseButton.setActiv(false);
			}
			break;
		case KeyEvent.VK_T:
			pauseButton.setActiv(false);
			playButton.setActiv(false);
			stoppButton.setActiv(true);
			break;
		case KeyEvent.VK_R:
			counter = 0;
			break;

		case KeyEvent.VK_C:
			clockButton.setActiv(!clockButton.isActiv());
			dropDownButton.setActiv(false);
			break;

		case KeyEvent.VK_D:
			dropDownButton.setActiv(!dropDownButton.isActiv());
			clockButton.setActiv(false);
			break;

		case KeyEvent.VK_ESCAPE:
			exitButton.setActiv(true);
			break;

		case KeyEvent.VK_SPACE:
			if (playButton.isActiv() && !pauseButton.isActiv() && dropDownButton.isActiv()) {
				rounds.add(counter);
				roundButton.setActiv(true);
			}
		}
	}

	/**
	 * Fuehrt Aktionen bei loslassen einer Taste bzw. Maus aus
	 */
	public void actionReleased(int e) {
		switch (e) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_T:
			stoppButton.setActiv(false);
			break;
		case KeyEvent.VK_SPACE:
			roundButton.setActiv(false);
			break;
		}
	}

	/**
	 * Fuehrt verschieden Aktionen bei Tastendruck aus
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		actionPressed(e.getKeyCode());
	}

	/** Wird beim loslassen einer Taste aufgerufen */
	@Override
	public void keyReleased(KeyEvent e) {
		actionReleased(e.getKeyCode());
	}

	/**
	 * Erstelle einen neuen Timer
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Timer();
	}
}
