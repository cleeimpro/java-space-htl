package proj.zeigerdiagramm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import lib.awt.*;

public class DrawArea extends Fenster{

	private static final long serialVersionUID = 1L;
	
	
	// Zeichnflaeche
	/** Koordinaten der Zeichnflaeche */
	private int x, y, width, height;
	/** Verschiebung der Zeichenflaeche */
	private int xDrawArea, yDrawArea;
	/** Temp zum Verschieben der Zeichenflaeche */
	private int xAreaTemp, yAreaTemp;
	/** Faktor fuer skalierung */
	private double xScale, yScale;
	/** Abstand zwischen zwei Markierungen in px */
	private int xD, yD;
	/** Temp zum veraendern des Scale-Faktors */
	private int xTemp, yTemp;
	/** Minimaler und Maximaler Abstand zwischen zwei Markierungen in px */
	private int minD = 60, maxD = 100;
	/** Wenn true, wird ein Gitter gezeichnet */
	private boolean gridOn = true;
	/** Schriftart fuer Achsenbeschriftung */
	private Font axleFont = new Font("CenturyGothicStandart", Font.PLAIN, 12);
	/** in welchem Hauptprogramm es drin is */
	private Frame frame;
	/** True, wenn Alt-Taste gedrueckt ist */
	private boolean altKey = false;
	
	// Vektoren
	/** Alle Vektoren */
	private List<Vector> allVectors = new ArrayList<Vector>();
	
	/** Konstruktor */
	public DrawArea(Frame frame, int x, int y, int width, int height) {
		this.frame = frame;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		// Deklaration
		xDrawArea = width / 2;
		yDrawArea = height / 2;
		xScale = yScale = 1;
		xD = yD = minD;
	}
	
	/** Fuegt Vektor zu Zeichenflaeche hinzu */
	/*public void addVector(Vector v) {
		allVectors.add(v);
	}*/

	@Override
	public void mousePressed(MouseEvent e) {
		if(frame.getCursor().getType() == Cursor.E_RESIZE_CURSOR ||
				frame.getCursor().getType() == Cursor.N_RESIZE_CURSOR) { // Skalierung der Achsen
			xTemp = e.getX();
			yTemp = e.getY();
		}else {	// Verschieben der Zeichnflaeche
			xAreaTemp = xDrawArea - e.getX();
			yAreaTemp = yDrawArea - e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		xTemp = yTemp = 0;
	}

	/** Verschieben der Zeichenflaeche */
	@Override
	public void mouseDragged(MouseEvent e) {
		if(frame.getCursor().getType() == Cursor.E_RESIZE_CURSOR) { // Ist X-Achse gedragged?
			increaseX(e.getX() - xTemp);
			xTemp = e.getX();
			yTemp = e.getY();
		} else if(frame.getCursor().getType() == Cursor.N_RESIZE_CURSOR) { // Ist Y-Achse gedragged?
			increaseY(-e.getY() + yTemp);
			xTemp = e.getX();
			yTemp = e.getY();
		} else { // Ist Zeichenflaeche gedragged?
			xDrawArea = e.getX() + xAreaTemp;
			yDrawArea = e.getY() + yAreaTemp;
		}
	}

	/** Wenn die Maus ueber einem Vektor hovert wird dieser farbig hinterlegt */
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= x + xDrawArea - 2 && e.getX() <= x + xDrawArea + 2) {
			frame.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR)); // Stellt X-Achse zum Verschieben bereit
		} else if (e.getY() >= y + yDrawArea - 2 && e.getY() <= y + yDrawArea + 2){
			frame.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR)); // Stellt Y-Achse zum Verschieben bereit
		} else
			frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Setzt auf den Standart-Cursor zurueck
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getPreciseWheelRotation());
		if(e.getPreciseWheelRotation() > 0) {
			increaseX(altKey ? 5 : 1);
			increaseY(altKey ? 5 : 1);
		} else {
			increaseX(-(altKey ? 5 : 1));
			increaseY(-(altKey ? 5 : 1));
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ALT) {
			altKey = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ALT)
			altKey = false;
	}
	
	/** Zeichnet das Interface */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(axleFont);

		g2.setStroke(new BasicStroke(0.5f));
		// X-Achse und X-Gitter
		for (int i = -xDrawArea * 5 / xD + (xDrawArea < 0 ? 1 : 0); i < (width - xDrawArea) * 5 / xD + (xDrawArea > width ? 0 : 1); i++) {
			if (gridOn) { // Gitter
				if (i % 5 == 0)
					g.setColor(Color.GRAY);
				else
					g.setColor(Color.LIGHT_GRAY);
				if(i != 0)
					g.drawLine(x + xDrawArea + i * xD / 5, y, x + xDrawArea + i * xD / 5, y + height);
			}

			if (i % 5 == 0 && xScale * i / 5 != 0) { // Beschriftung
				g.setColor(Color.BLACK);
				String s = "";
				if (xScale >= 1)
					s = "" + (int) (xScale * i / 5);
				else
					s = "" + xScale * i / 5;
				g.drawString(s, x + xDrawArea + i * xD / 5 - g.getFontMetrics().stringWidth(s) / 2, 
						(yDrawArea + 10 + g.getFont().getSize() >= height) ? y + height - 5: 
						(yDrawArea + 10 - g.getFont().getSize() <= 0) ? y + 10 + g.getFont().getSize() : 
							y + yDrawArea + 15);
				
			}
		}

		// Y-Achse und Y-Gitter
		for (int i = -yDrawArea * 5 / yD + (yDrawArea < 0 ? 1 : 0); i < (height - yDrawArea) * 5 / yD + (yDrawArea > height ? 0 : 1); i++) {
			if (gridOn) { // Gitter
				if (i % 5 == 0)
					g.setColor(Color.GRAY);
				else
					g.setColor(Color.LIGHT_GRAY);
				g.drawLine(x, y + yDrawArea + i * yD / 5, x + width, y + yDrawArea + i * yD / 5);
			}

			if (i % 5 == 0 && yScale * i / 5 != 0) { // Beschriftung
				g.setColor(Color.BLACK);
				String s = "";
				if (yScale >= 1)
					s = "" + (int) (-yScale * i / 5);
				else
					s = "" + -yScale * i / 5;
				g.drawString(s, (xDrawArea - g.getFontMetrics().stringWidth(s) - 5 < 10) ? x + 10 : 
					(xDrawArea >= width) ? x + width - g.getFontMetrics().stringWidth(s) -5 :
					x + xDrawArea - g.getFontMetrics().stringWidth(s) - 5, y + yDrawArea + i * yD / 5 + 4);
			}
		}
		
		// Nullpunkt Beschriftung
		g.setColor(Color.BLACK);
		if(xDrawArea > 15 && xDrawArea < width - 15 && yDrawArea > 0 && yDrawArea < height - 15)
			g.drawString("0", x + xDrawArea - 15, y + yDrawArea + 15);

		// Achsen
		g2.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		
		if(yDrawArea >= 0 && yDrawArea <= height)
			g.drawLine(x, y + yDrawArea, x + width, y + yDrawArea); // X
		if(xDrawArea >= 0 && xDrawArea <= width)
			g.drawLine(x + xDrawArea, y, x + xDrawArea, y + height); // Y

		// Verhaeltnisanzeige
		if(xTemp != 0 || yTemp != 0) {
			double temp = (xD / xScale) / (yD / yScale);
			if(temp > 1)
				g.drawString(String.format("x : y = %.2f : 1", temp), xTemp + 10, yTemp + 10 + 8);
			else
				g.drawString(String.format("x : y = 1 : %.2f", (yD / yScale) / (xD / xScale)),xTemp + 10, yTemp + 10 + 8);
		}
		
		
		
		// Vektoren werden gezeichnet
		g.translate(x + xDrawArea, y + yDrawArea);
		for(Vector v : allVectors)
			v.paint(g, xDrawArea, yDrawArea, width, height, xD / xScale, yD / yScale);

	}
	
	/**
	 * Skaliert die X-Achse neu und passt die Abstaende an
	 * @param d deltaX
	 */
	public void increaseX(int d) {
		xD += d;
		if(d > 0) {
			if (xD > maxD) {
				xD = minD;
				xScale /= (getFirstDigit((int) xScale) == 5 ? 2.5 : 2);
			}
		}else {
			if (xD < minD) {
				xD = maxD;
				if (getFirstDigit((int) xScale) == 2) {
					xScale *= 2.5;
					xD *= 1.25;
				} else {
					xScale *= 2;
				}
			}
		}
	}
	
	/**
	 * Skaliert die Y-Achse neu und passt die Abstaende an
	 * @param d deltaY
	 */
	public void increaseY(int d) {
		yD += d;
		if(d > 0) {
			if (yD > maxD) {
				yD = minD;
				yScale /= (getFirstDigit((int) yScale) == 5 ? 2.5 : 2);
			}
		}else {
			if (yD < minD) {
				yD = maxD;
				if (getFirstDigit((int) yScale) == 2) {
					yScale *= 2.5;
					yD *= 1.25;
				} else {
					yScale *= 2;
				}
			}
		}
	}
	
	/**
	 * Gibt die erst Ziffer einer Zahl zurueck
	 * @param number Zahl welche zerlegt werden soll
	 * @return Rueckgabewert
	 */
	public static int getFirstDigit(int number) {
		while(number >= 10) {
			number = number / 10;
		}
		return number;
	}

	public List<Vector> getAllVectors() {
		return allVectors;
	}

	public void setAllVectors(List<Vector> allVectors) {
		this.allVectors = allVectors;
	}

}
