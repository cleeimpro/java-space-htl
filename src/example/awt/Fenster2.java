package example.awt;

import java.awt.Graphics;

public class Fenster2 extends Fenster{

	public Fenster2() {
		this.setTitle("Zweites Programm");
		this.setSize(800,600);
		this.setVisible(true);
		this.addWindowListener(this);
		this.addComponentListener(this);
	}
	
	public static final int ABSTAND = 50;
	
	@Override
	public void paint(Graphics g) {
		int x = (int)Math.sqrt(ABSTAND*ABSTAND*2);	
		int breite = this.getWidth();
		int hoehe = this.getHeight();
		for(int i = -hoehe/x; i < breite/x-1;i++) {
			g.drawLine(i*x, 0, i*x+hoehe, hoehe);
		}
		for(int i = 1; i < (breite+hoehe)/x;i++) {
			g.drawLine(i*x, 0, i*x-hoehe, hoehe);
		}
		
	}
	
	
}
