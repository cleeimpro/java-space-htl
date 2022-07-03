package lib.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;

import lib.awt.form.Form;

/**
 * Erstellt einen Button fuer ein AwtFenster 
 * @author Clemens
 *
 */
public class AwtButton {

	/** X-Koordinate Startpunkt */
	private int x;
	/** Y-Koordinate Startpunkt */
	private int y;
	/** Breite des Buttons*/
	private int width;
	/** Hoehe des Buttons */
	private int height;
	/** Hauptfarbe / ContentFarbe */
	private Color mainColor;
	/** Hintergrundfarbe */
	private Color backgroundColor;
	/** Alte Hauptfarbe */
	private Color oldMainColor;
	/** Alte Hintergrundfarbe */
	private Color oldBackgroundColor;
	/** Inhalt des Buttons als Form */
	private Vector<Form> content;
	/** Text im Button */
	private String text;
	/** Groesse des Textes */
	private int stringSize;
	/** True, wenn Button "aktiv" ist */
	private boolean activ;
	/** Strichstaerke der Border, wenn =null wird keine Border gezeichnet */
	private int borderSize;

	/**
	 * SubKonstruktor
	 * @param x Koordinate
	 * @param y Koordinate
	 * @param width Breite
	 * @param height Hoehe
	 * @param mainColor Hauptfarbe
	 * @param backgroundColor Hintergrundfarbe
	 * @param text Text im Button
	 * @param stringSize Groesse des Textes
	 * @param content Inhalt
	 */
	public void constructAwtButton(int x, int y, int width, int height, Color mainColor, Color backgroundColor,
			String text, int stringSize, Form... content) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.oldMainColor = this.mainColor = mainColor;
		this.oldBackgroundColor = this.backgroundColor = backgroundColor;
		this.text = text;
		this.stringSize = stringSize;
		this.content = new Vector<Form>();
		if (content != null)
			for (int i = 0; i < content.length; i++) {
				this.content.add(content[i]);
			}
		else
			this.content = null;
		activ = false;
		this.borderSize = 0;
	}

	/**
	 * Konstruktor 1
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param mainColor
	 * @param backgroundColor
	 * @param text
	 * @param stringSize
	 */
	public AwtButton(int x, int y, int width, int height, Color mainColor, Color backgroundColor, String text,
			int stringSize) {
		constructAwtButton(x, y, width, height, mainColor, backgroundColor, text, stringSize, null);
	}

	/**
	 * Konstruktor 2
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param mainColor
	 * @param backgroundColor
	 * @param content
	 */
	public AwtButton(int x, int y, int width, int height, Color mainColor, Color backgroundColor, Form... content) {
		constructAwtButton(x, y, width, height, mainColor, backgroundColor, null, 0, content);
	}

	/**
	 * Konstruktor 3
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public AwtButton(int x, int y, int width, int height) {
		constructAwtButton(x, y, width, height, new Color(0, 0, 0), new Color(255, 255, 255), null, 0, null);
	}

	/**
	 * Gibt die die vier wichtigsten Parameter in einem String aus
	 */
	public String toString() {
		return "x:" + x + ", y:" + y + ", width:" + width + ", height:" + height;
	}

	/**
	 * Zeichnet den Button an seinem richtigen Platz
	 * @param g Graphicshandler
	 */
	public void paint(Graphics g) {
		if (activ)
			activateColor();
		else
			disableColor();

		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(mainColor);
		if (borderSize!=0) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(borderSize));
			g.drawRect(x, y, width, height);
		}
		if (content != null)
			for (Form p : content) {
				p.setColor(mainColor);
				p.paint(g);
			}

		if (text != null) {
			g.setFont(new Font("CenturyGothicStandart", Font.BOLD, stringSize));
			FontMetrics fm = g.getFontMetrics();
			g.drawString(text, x + width / 2 - fm.stringWidth(text) / 2, y + height / 2 + stringSize / 2);
		}
	}

	/**
	 * Checkt, ob die Mouse ueber dem Button ist
	 * @param e MouseEvent vom Hauptprogramm
	 * @return true, wenn Button geclicket wurde
	 */
	public boolean isClicked(MouseEvent e) {
		return (e.getX() > x && e.getX() < x + width && e.getY() > y && e.getY() < y + height);
	}

	/**
	 * Tauscht die Farben
	 */
	public void toggleColor() {
		Color c = mainColor;
		mainColor = backgroundColor;
		backgroundColor = c;
	}

	/**
	 * Wechselt einmalig und mit immer gleichem Ergebniss die Farben
	 */
	public void activateColor() {
		mainColor = oldBackgroundColor;
		backgroundColor = oldMainColor;

	}

	/**
	 * Wechselt einmalig und mit immer gleichem Ergebniss die Farben
	 */
	public void disableColor() {
		mainColor = oldMainColor;
		backgroundColor = oldBackgroundColor;
	}
	
	/**
	 * Bewegt den Button
	 * @param dx delta X-Koordinate
	 * @param dy delta Y-Koordinate
	 */
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;
	}

	public boolean isActiv() {
		return activ;
	}

	public void setActiv(boolean activ) {
		this.activ = activ;
	}

	public Vector<Form> getContent() {
		return content;
	}

	public void setContent(Form... content) {
		this.content = new Vector<Form>();
		for (int i = 0; i < content.length; i++) {
			this.content.add(content[i]);
		}
	}

	public Color getMainColor() {
		return mainColor;
	}

	public void setMainColor(Color mainColor) {
		this.oldMainColor = this.mainColor = mainColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.oldBackgroundColor = this.backgroundColor = backgroundColor;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getOldMainColor() {
		return oldMainColor;
	}

	public Color getOldBackgroundColor() {
		return oldBackgroundColor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStringSize() {
		return stringSize;
	}

	public void setStringSize(int stringSize) {
		this.stringSize = stringSize;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public void setContent(Vector<Form> content) {
		this.content = content;
	}


}
