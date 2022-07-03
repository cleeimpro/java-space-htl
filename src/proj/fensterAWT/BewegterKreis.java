package proj.fensterAWT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import lib.awt.Fenster;
import lib.awt.form.Kreis;

public class BewegterKreis extends Fenster {
	
	private static final long serialVersionUID = 7526411062902158294L;
	
	/** Stellt den Kreis am Bildschirm da */ 
	Kreis k;
	
	private boolean follow = false;

	/** Konstruktor */
	public BewegterKreis() {
		super();
		k = new Kreis(this.getWidth()/2,this.getHeight()/2,10);
		this.setVisible(true);
	}
	
	/** Startet das Fenster */
	public static void main(String[] args) {
		new BewegterKreis();	
	}
	
	/** zeichnet das Fenster neu */
	@Override
	public void paint(Graphics g) {
		k.paint(g);
	}
	
	/**
	 * Schaut welche Taste gedrückt wird und führt dementsprechend eine Funktion aus.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN: k.move(0, 10); if(k.getMy()>this.getHeight()) k.setMy(0); break;
			case KeyEvent.VK_UP: k.move(0, -10); if(k.getMy()<0) k.setMy(this.getHeight()); break;
			case KeyEvent.VK_RIGHT: k.move(10, 0); if(k.getMx()>this.getWidth()) k.setMx(0); break;
			case KeyEvent.VK_LEFT: k.move(-10, 0); if(k.getMx()<0) k.setMx(this.getWidth()); break;	
			case KeyEvent.VK_CLOSE_BRACKET: k.increaseL(10); break;
			case KeyEvent.VK_SLASH: k.increaseL(-10); break;
			case KeyEvent.VK_F: k.setFuellung(!k.isFuellung()); break;
			case KeyEvent.VK_R: if(e.isMetaDown()) k.setFarbe(Color.red); else k.setHintergrund(Color.red); break;
			case KeyEvent.VK_G: if(e.isMetaDown()) k.setFarbe(Color.green); else k.setHintergrund(Color.green); break;
			case KeyEvent.VK_B: if(e.isMetaDown()) k.setFarbe(Color.blue); else k.setHintergrund(Color.blue); break;
			case KeyEvent.VK_S: if(e.isMetaDown()) k.setFarbe(Color.black); else k.setHintergrund(Color.black); break;
			case KeyEvent.VK_M: k.setMx(this.getWidth()/2); k.setMy(this.getHeight()/2); break;	
		}
	
			
		 this.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(MouseEvent.BUTTON1 == e.getButton())
			k.setPoint(e.getPoint());
		if(MouseEvent.BUTTON3 == e.getButton())
			follow = !follow;
		this.repaint();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		k.increaseR(e.getWheelRotation());
		 this.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (follow) { 
			k.setPoint(e.getPoint());
			 
		}
		this.repaint();
	}
}
