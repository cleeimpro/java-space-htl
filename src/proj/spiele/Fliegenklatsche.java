package proj.spiele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import lib.awt.FensterAnimated;
import lib.awt.form.Kreis;

public class Fliegenklatsche extends FensterAnimated{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Kreis welcher geclickt werden soll*/
	private Kreis k;
	/** Gesamte Spielzeit */
	private static int GlobalTimer;
	/** Zeit, bis der Kreis wo anderes hingeht */
	private static int BallTimer;
	/** Wie viele Kreise gefangen wurden */
	private static int Score;
	
	/**
	 * Konstruktor
	 */
	public Fliegenklatsche() {
		super();
		GlobalTimer = 120000;
		BallTimer = 2000;
		k = new Kreis((int)(Math.random()*(this.getWidth()-2*40))+40,(int)(Math.random()*(this.getHeight()-2*40-22))+40+22,40);
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if((e.getX()-k.getMx())*(e.getX()-k.getMx())+
			(e.getY()-k.getMy())*(e.getY()-k.getMy())<=
			k.getR()*k.getR()){
				Score++;
				BallTimer = 2000;
				k.setMx((int)(Math.random()*(this.getWidth()-2*40))+40);
				k.setMy((int)(Math.random()*(this.getHeight()-2*40-22))+40+22);
			}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_R: GlobalTimer = 120000; BallTimer = 2000; Score = 0; break;
			case KeyEvent.VK_ESCAPE: System.exit(0); break; 
		}
		
	}

	
	@Override
	public void paint(Graphics g) {
		GlobalTimer-=20;
		BallTimer-=20;
		
		if(BallTimer<=0) {
			k.setMx((int)(Math.random()*(this.getWidth()-2*40))+40);
			k.setMy((int)(Math.random()*(this.getHeight()-2*40-22))+40+22);
			BallTimer = 2000;
		}
		if(GlobalTimer<=0) {
			FensterAnimated.RUN = false;
			g.setColor(Color.BLACK);
			g.drawString("Die Zeit ist abgelaufen! Dein Score: "+Score, 200, this.getHeight()-20);
		}
		
		k.paint(g);
		g.drawRect(0, 0, 80, 50);
		g.drawRect(this.getWidth()-80, 0, 80, 50);
		g.drawString(GlobalTimer/60000+":"+GlobalTimer%60000/1000+"."+GlobalTimer%60000%1000, 10, 40);
		g.drawString(Score+"", this.getWidth()-60, 40);
	}

	/**
	 * Erzeugt das Spiel Fliegenklatsche
	 * @param args
	 */
	public static void main(String[] args) {
		new Fliegenklatsche();
	}

}
