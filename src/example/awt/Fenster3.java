package example.awt;

import java.awt.Graphics;

public class Fenster3 extends Fenster{

	public Fenster3() {
		this.setTitle("Drittes Programm");
		this.setSize(800,600);
		this.setVisible(true);
		this.addWindowListener(this);
		this.addComponentListener(this);
	}
	
	public static final int ABSTAND = 20;
	
	@Override
    public void paint(Graphics g) {
        int breite = this.getWidth();
        int hoehe = this.getHeight();

        for(int i = 1; i< (breite<hoehe?breite:hoehe)/(2*ABSTAND);i++) {
            g.drawRect(ABSTAND*i, ABSTAND*i+30, breite-ABSTAND*2*i, hoehe-30-ABSTAND*i*2);
        }
    }
	
}
