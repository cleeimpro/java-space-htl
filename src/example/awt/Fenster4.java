package example.awt;

import java.awt.Graphics;

/**
 * AWT-Ausgabe eines Fensters mit schwarzen und weißen Kreisen
 * 
 * @author Clemens Freudentaler
 * @date 27.03.2019
 * @version 1.0
 *
 */

public class Fenster4 extends Fenster{
	
	/**
	 * Erzeug ein neues Fenster 800*600
	 */
	public Fenster4() {
		this.setTitle("Viertes Programm");
		this.setSize(800,600);
		this.setVisible(true);
		this.addWindowListener(this);
		this.addComponentListener(this);
	}
	
	/** Anzahl der Kreise in X-Richtung */
	public static final int m = 20;
	/** Anzahl der Kreise in Y-Richtung */
	public static final int n = 30;
	
	/**
	 * Zeichnet ein Fenster voll mit schwarzen und weißen Kreisen
	 */
	@Override
    public void paint(Graphics g) {
        int breite = this.getWidth();
        int hoehe = this.getHeight();
        
		// Abstand von Kreis Anfang zu Kreis Anfang in X-Richtung
		int dx = breite/m*9/10;
		// Abstandes der Kreise in Y-Richtung
		int dy = (hoehe-30)/(n+1);
		// Kreisdurchmessers
		int d = (dx > dy ? dy : dx)*8/10;	
		// Startpos in Y-Richtung
		int y = dy/2+30;
		
		// Schleife welche jede Zeile durchläuft
		for (int i=0; i<n; i++) {
			// Startwert X-Achse setzen
			int x = dx/2;
			// Schleife welche jede Spalte durchläuft
			for (int j=0; j<m; j++) {
				if ((i+j)%2==0)
						g.fillOval(x, y, d, d);
				else
						g.drawOval(x, y, d, d);
				x+=dx;			// X-Pos für nächsten Kreis setzen
			}
			
			// y Startwert für nächste Zeile setzen
			y+=dy;
		}
        
    }
	
}
