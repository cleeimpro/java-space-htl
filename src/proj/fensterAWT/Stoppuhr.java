package proj.fensterAWT;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import lib.awt.FensterAnimated;

public class Stoppuhr extends FensterAnimated{
	/** */
	private static final long serialVersionUID = 1L;
	/** Zeit welche immer weiter gezaehlt wird wenn run = true */
	private int timer;
	/** Zeit welche ausgegeben wird */
	private int freezeTime;
	/** True, wenn timer laeuft */
	private boolean run = false;
	/** True, wenn timer in pause */
	private boolean pause = false;
	/** Schrift fuer digitalausgabe */
	private Font timerFont;
	/** String in welchen die digitalAusgabe formatiert wird */
	private String output;
	
	// Konstruktor
	public Stoppuhr() {
		FensterAnimated.SLEEPTIME = 10;
		this.setSize(500,400);
		this.setBackground(new Color(57, 53, 53));
		timerFont = new Font("CenturyGothicStandart", Font.BOLD, 70);
		this.setVisible(true);
	}
	
	/**
	 * Nimmt die Befehle von der Tastatur an
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_S:
			run = !run;
			break;
		case KeyEvent.VK_Z: 
			if(run)
				pause = !pause;
			else if(!run && pause)
				pause = !pause;
			else
				timer = freezeTime = 0;
			break;
		case KeyEvent.VK_ESCAPE: 
			System.exit(0);
			break;
		}
		
	}
	
	
	/**
	 * zeichnet die beiden analogen Uhren
	 * @param g
	 */
	public void clockPaint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		g.drawOval(50,22+timerFont.getSize()+10+50,200,200);
		int min = freezeTime/60000;
		g.setColor(Color.WHITE);
		g.drawLine(150, 22+timerFont.getSize()+10+50+100,150+(int) (Math.sin(min/60d%1d*2*Math.PI)*70), 22+timerFont.getSize()+10+50+100+(int)(-Math.cos(min/60d%1d*2*Math.PI)*70));
		int s = freezeTime/1000;
		g2.setStroke(new BasicStroke(8));
		g.setColor(Color.RED);
		g.drawLine(150, 22+timerFont.getSize()+10+50+100,150+(int) (Math.sin(s/60d%1d*2*Math.PI)*80), 22+timerFont.getSize()+10+50+100+(int)(-Math.cos(s/60d%1d*2*Math.PI)*80));
		
		g.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(6));
		g.drawOval(this.getWidth()/2+50,22+timerFont.getSize()+10+100,100,100);
		if(pause) {
			g.drawLine(this.getWidth()/2+50+50, 22+timerFont.getSize()+10+100+50,this.getWidth()/2+100+ (int) (Math.sin(freezeTime/1000d%1d*2*Math.PI)*50), 22+timerFont.getSize()+10+150+(int)(-Math.cos(freezeTime/1000d%1d*2*Math.PI)*50));
		}
	}
	
	/**
	 * Zeichnet den ganzen digital und analog
	 */
	@Override
	public void paint(Graphics g) {
		if(run)
			timer+=10;
		if(!pause)
			freezeTime = timer;
		
		g.setColor(Color.WHITE);
		g.setFont(timerFont);
		output =  String.format("%02d:%02d:%02d.%03d", freezeTime / 3600000, freezeTime % 3600000 / 60000,
				freezeTime % 60000 / 1000, freezeTime % 1000);
		g.drawString(output, 10, 22+timerFont.getSize()+10);
		clockPaint(g);
	}
	
	// Erstellt eine neues Objekt Stoppuhr
	public static void main(String[] args) {
		new Stoppuhr();
	}

}
