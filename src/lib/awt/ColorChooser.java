package lib.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import lib.awt.form.Rechteck;

/**
 * Erstellt ein Farbauswahlfenster mit verschiedenen Farbsystemen
 * @author Clemens
 *
 */
public class ColorChooser extends FensterAnimated {
	
	/** Weils halt sein muss */
	private static final long serialVersionUID = 1L;
	/** String mit der Information welches OS verwendet wird */
	private static String OS = System.getProperty("os.name").toLowerCase();
	/** Hoehe der Headline */
	private int hlh;
	/** Hintergrundfarbe */
	private Color backgroundColor = new Color(57, 53, 53);
	/** Color */
	private Color mainColor = new Color(112, 112, 112);
	/** Menubar */
	private MenuBar menuBar;
	/** Aktuel ausgewahlte Farbe als Button verpackt */
	private AwtButton currentColor;
	/** Vector mit Buttons fuer die Farbpalette */
	private Vector<AwtButton> colorPalette;
	/** Zaehlervariable welche fuer die verschiedenen Farbsysteme ist */
	private int view = 0;
	/** Vector mit 12 Buttons in denen die 12 Java-Standardfarben */
	private Vector<AwtButton> standardColors;
	/** Vector mit groeser Auswahl an Buttons mit unterschiedlicher Farbe */
	private Vector<AwtButton> mixColors;
	/** Vector mit den drei Fadern fuer RGB */
	private Vector<ColorFader> rgbFader;
	/** Vector mit den drei Fadern fuer HSB */
	private Vector<ColorFader> hsbFader;
	/** Button in dem die aktuelle RGB-Farbe angezeigt wird */
	private AwtButton tempColorAB;
	/** Vector mit allen Buttons die per Drag'n'Drop in die Farbpalette gezogen werden koennen */
	private Vector<AwtButton> currentColorVector;
	/** Wird beim Drag'n'Drop von Farben verwendet */
	private Rechteck tempRect;

	/**
	 * Konstruktor fuer einen neuen ColoChooser
	 */
	public ColorChooser() {
		this.setTitle("Color Chooser"); // Title
		
		// Differenziert Zwischen Windows und Macintosh
		if (OS.indexOf("win") >= 0)
			hlh = 30;
		else if (OS.indexOf("mac") >= 0) {
			hlh = 22;
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");
		}
		
		this.setSize(400, hlh+300); // Fenstergroesse
		this.setBackground(backgroundColor); // Hintergrundfarbe
		this.setResizable(false); // Fenster ist nicht in der Groese veraenderbar
		
		prepareMenu(); // Bereitet die Menubar vor
		prepareStandard(); // Bereitet 12 Standard-Farbfelder
		prepareMix(); // Bereitet viele Farbfelder vor
		prepareRGB(); // Bereitet die drei RGB-Regler vor
		prepareHSB(); // Bereitet die drei HSB-Regler vor
		
		// Initialisiert den Button mit der aktuellen Farbe
		currentColor = new AwtButton(0, hlh, 30, 250); 
		currentColor.setBackgroundColor(Color.RED);
		
		// Initialisiert die Farbpalette
		colorPalette = new Vector<AwtButton>();
		for(int i = 0; i<10; i++) {
			colorPalette.add(new AwtButton(5+40*i,hlh+250+10,30,30));
		}
		
		// Initialisiert den Vector mit den Feldern fuer Drag'n'Drop
		currentColorVector = standardColors;
		currentColorVector.add(currentColor);
		
	}
	
	/**
	 * Bereitet die Menubar vor
	 */
	public void prepareMenu() {
		
		menuBar = new MenuBar();
		
		Menu window = new Menu("Window");
		Menu help = new Menu("Help");
		
		MenuItem standard = new MenuItem("Default", new MenuShortcut(KeyEvent.VK_1));
		MenuItem mix = new MenuItem("Mix", new MenuShortcut(KeyEvent.VK_2));
		MenuItem rgb = new MenuItem("RGB", new MenuShortcut(KeyEvent.VK_3));
		MenuItem hsb = new MenuItem("HSB", new MenuShortcut(KeyEvent.VK_4));
		MenuItem text = new MenuItem("Text", new MenuShortcut(KeyEvent.VK_5));
		
		standard.addActionListener(this);
		mix.addActionListener(this);
		rgb.addActionListener(this);
		hsb.addActionListener(this);
		text.addActionListener(this);
		
		window.add(standard);
		window.add(mix);
		window.add(rgb);
		window.add(hsb);
		window.add(text);
		
		menuBar.add(window);
		menuBar.setHelpMenu(help);
		
		//Fuegt die menubar in Fenster ein
		this.setMenuBar(menuBar);
	}

	/**
	 * Bereitet die StandardFarbfelder vor
	 */
	public void prepareStandard() {
		standardColors = new Vector<AwtButton>();
		for(int i = 0; i<12; i++) {
			standardColors.add(new AwtButton(30+10+60*(i>5?i-6:i),hlh+70+(i>5?60:0),50,50));
		}
		standardColors.get(0).setBackgroundColor(Color.BLACK);
		standardColors.get(1).setBackgroundColor(Color.BLUE);
		standardColors.get(2).setBackgroundColor(Color.CYAN);
		standardColors.get(3).setBackgroundColor(Color.DARK_GRAY);
		standardColors.get(4).setBackgroundColor(Color.LIGHT_GRAY);
		standardColors.get(5).setBackgroundColor(Color.GREEN);
		standardColors.get(6).setBackgroundColor(Color.MAGENTA);
		standardColors.get(7).setBackgroundColor(Color.ORANGE);
		standardColors.get(8).setBackgroundColor(Color.PINK);
		standardColors.get(9).setBackgroundColor(Color.RED);
		standardColors.get(10).setBackgroundColor(Color.WHITE);
		standardColors.get(11).setBackgroundColor(Color.YELLOW);
	}
	
	/**
	 * Bereitet die MixFarbfelder vor
	 */
	public void prepareMix() {
		mixColors = new Vector<AwtButton>();
		for(int i = 0; i<(this.getWidth()-30-10-10)/10; i++) {
			for(int j = 0; j<200/10; j++) {
				mixColors.add(new AwtButton(30+10+1+10*i,hlh+25+10*j,8,8));
				mixColors.get(mixColors.size()-1).setBackgroundColor(Color.getHSBColor(1f/(float)((this.getWidth()-30-10-10)/10)*i, 1f, 1-1f/(200f/10f)*j));
			}
		}
	}
	
	/**
	 * Bereitet den RGB-Button und die Fader vor
	 */
	public void prepareRGB() {
		tempColorAB = new AwtButton(30,hlh,this.getWidth()-30,30);
		
		rgbFader = new Vector<ColorFader>();
		
		rgbFader.add(new ColorFader(30+20,hlh+90+40*0,this.getWidth()-70,25,new Color(0,0,0),new Color(tempColorAB.getBackgroundColor().getRed(),0,0)));
		rgbFader.add(new ColorFader(30+20,hlh+90+40*1,this.getWidth()-70,25,new Color(0,0,0),new Color(0, tempColorAB.getBackgroundColor().getGreen(),0)));
		rgbFader.add(new ColorFader(30+20,hlh+90+40*2,this.getWidth()-70,25,new Color(0,0,0),new Color(0,0, tempColorAB.getBackgroundColor().getBlue())));
		ColorFader.setSliderColor(Color.WHITE);
	}
	
	/**
	 * Bereitet die HSB-Fader vor
	 */
	public void prepareHSB() {
		tempColorAB = new AwtButton(30,hlh,this.getWidth()-30,30);
		
		hsbFader = new Vector<ColorFader>();
		
		float[] hsb = new float[3];
		Color.RGBtoHSB(tempColorAB.getBackgroundColor().getRed(),
				tempColorAB.getBackgroundColor().getGreen(),
				tempColorAB.getBackgroundColor().getBlue(),hsb);
		
		hsbFader.add(new ColorFader(30+20,hlh+90+40*0,this.getWidth()-70,25,new Color(0,0,0),Color.getHSBColor(hsb[0], 0.0f, 0.0f)));
		hsbFader.add(new ColorFader(30+20,hlh+90+40*1,this.getWidth()-70,25,new Color(0,0,0),Color.getHSBColor(hsb[0], 0.0f, 0.0f)));
		hsbFader.add(new ColorFader(30+20,hlh+90+40*2,this.getWidth()-70,25,new Color(0,0,0),Color.getHSBColor(hsb[0], 0.0f, 0.0f)));
		ColorFader.setSliderColor(Color.WHITE);
		
	}

	
	/**
	 * Wird beim Clicken in der Menubar ausgefuehrt und fuert eine Aktion aus
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Setzt die Auswahlmoeglichkeit auf Default
		if (e.getActionCommand().equals("Default")) {
			view = 0;
			currentColorVector = standardColors;
			currentColorVector.add(currentColor);
		}
		
		// Setzt die Auswahlmoeglichkeit auf Mix
		else if (e.getActionCommand().equals("Mix")) {
			view = 1;
			currentColorVector = mixColors;
			currentColorVector.add(currentColor);
		}

		// Setzt die Auswahlmoeglichkeit auf Mix
		else if (e.getActionCommand().equals("RGB")) {
			view = 2;
			System.out.println(currentColor.getBackgroundColor());
			tempColorAB.setBackgroundColor(currentColor.getBackgroundColor());
			System.out.println(tempColorAB.getBackgroundColor());
			rgbFader.get(0).setC1(new Color(0,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
			rgbFader.get(0).setC2(new Color(255,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
			rgbFader.get(1).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),0,tempColorAB.getBackgroundColor().getBlue()));
			rgbFader.get(1).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),255,tempColorAB.getBackgroundColor().getBlue()));
			rgbFader.get(2).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),0));
			rgbFader.get(2).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),255));
			
			rgbFader.get(0).setCurrentColor(new Color(tempColorAB.getBackgroundColor().getRed(),0,0));
			rgbFader.get(1).setCurrentColor(new Color(0,tempColorAB.getBackgroundColor().getGreen(),0));
			rgbFader.get(2).setCurrentColor(new Color(0,0,tempColorAB.getBackgroundColor().getBlue()));
		
			currentColorVector = new Vector<AwtButton>();
			currentColorVector.add(tempColorAB);
			currentColorVector.add(currentColor);
		}
		
		// Setzt die Auswahlmoeglichkeit auf Mix
		else if (e.getActionCommand().equals("HSB")) {
			view = 3;
			System.out.println(currentColor.getBackgroundColor());
			tempColorAB.setBackgroundColor(currentColor.getBackgroundColor());
			System.out.println(tempColorAB.getBackgroundColor());
			hsbFader.get(0).setC1(new Color(0,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
			hsbFader.get(0).setC2(new Color(255,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
			hsbFader.get(1).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),0,tempColorAB.getBackgroundColor().getBlue()));
			hsbFader.get(1).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),255,tempColorAB.getBackgroundColor().getBlue()));
			hsbFader.get(2).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),0));
			hsbFader.get(2).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),255));
			
			hsbFader.get(0).setCurrentColor(new Color(tempColorAB.getBackgroundColor().getRed(),0,0));
			hsbFader.get(1).setCurrentColor(new Color(0,tempColorAB.getBackgroundColor().getGreen(),0));
			hsbFader.get(2).setCurrentColor(new Color(0,0,tempColorAB.getBackgroundColor().getBlue()));
		
			currentColorVector = new Vector<AwtButton>();
			currentColorVector.add(tempColorAB);
			currentColorVector.add(currentColor);
		}
		
		// Setzt die Auswahlmoeglichkeit auf Mix
		else if (e.getActionCommand().equals("Text")) {
			view = 4;
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		switch(view) {
		case 0: break;
		case 1: break;
		case 2: for(ColorFader cf : rgbFader) cf.mousePressed(e); break;
		case 3: for(ColorFader cf : hsbFader) cf.mousePressed(e); break;
		case 4: break;
		default: System.out.println("Error with variable \"view\""); break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		switch(view) {
		case 0: 
		case 1: 
			
			break;
		case 2: for(ColorFader cf : rgbFader) cf.mouseReleased(e); 
				
			break;
		case 3: for(ColorFader cf : hsbFader) cf.mousePressed(e);
			break;
		case 4: break;
		default: System.out.println("Error with variable \"view\""); break;
		}
		
		if(tempRect != null) {
			for(AwtButton ab : colorPalette) {
				if(ab.isClicked(e)) {
					ab.setBackgroundColor(tempRect.getHintergrund());
				}
			}
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}else if(tempRect == null) {
			for(AwtButton ab : currentColorVector) {
				if(ab.isClicked(e)) {
					currentColor.setBackgroundColor(ab.getBackgroundColor());
				}
			}
			
			for(AwtButton ab : colorPalette) {
				if(ab.isClicked(e)) {
					currentColor.setBackgroundColor(ab.getBackgroundColor());
				}
			}
		}
		tempRect = null;
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		switch(view) {
		case 0:	break;
		case 1:	break;	
		case 2: for(ColorFader cf : rgbFader) cf.mouseDragged(e); break;
		case 3: for(ColorFader cf : hsbFader) cf.mousePressed(e); break;
		case 4: break;
		default: System.out.println("Error with variable \"view\""); break;
		}
			
		if(tempRect == null) {
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			for(AwtButton ab : currentColorVector) {
				if(ab.isClicked(e)) {
					tempRect = new Rechteck(e.getX(), e.getY(),30);
					tempRect.setColor(ab.getBackgroundColor());
				}
			}
		}
		else if(tempRect != null) {
			tempRect.setMx(e.getX()); tempRect.setMy(e.getY());
		}
		
	}
	
	/**
	 * Zeichnet den ColorChooser
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//Main Content
		switch(view) {
		case 0: standard(g); break;
		case 1: mix(g); break;
		case 2: rgb(g); break;
		case 3: hsb(g); break;
		case 4: text(g); break;
		default: System.out.println("Error with variable \"view\""); break;
		}
		
		// ColorPalette
		currentColor.paint(g);
		g.setColor(mainColor);
		g2.setStroke(new BasicStroke(3));
		g.drawLine(1, hlh, 1, hlh+250);
		g.drawLine(0,hlh+250,this.getWidth(),hlh+250);
		for(AwtButton ab : colorPalette) {
			ab.paint(g);
		}
		
		// Temporaeres Rechteck fuer Drag'n'Drop
		if(tempRect != null)
			tempRect.paint(g);
		
	}

	public void standard(Graphics g) {
		for(AwtButton ab : standardColors) {
			ab.paint(g);
		}
	}
	
	public void mix(Graphics g) {
		for(AwtButton ab : mixColors) {
			ab.paint(g);
		}
	}
	
	public void rgb(Graphics g) {
		
		tempColorAB.setBackgroundColor(new Color(rgbFader.get(0).getCurrentColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		tempColorAB.setBackgroundColor(new Color(tempColorAB.getBackgroundColor().getRed(),rgbFader.get(1).getCurrentColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		tempColorAB.setBackgroundColor(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),rgbFader.get(2).getCurrentColor().getBlue()));
		rgbFader.get(0).setC1(new Color(0,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		rgbFader.get(0).setC2(new Color(255,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		rgbFader.get(1).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),0,tempColorAB.getBackgroundColor().getBlue()));
		rgbFader.get(1).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),255,tempColorAB.getBackgroundColor().getBlue()));
		rgbFader.get(2).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),0));
		rgbFader.get(2).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),255));
		
		for(ColorFader cf : rgbFader)
			cf.paint(g);
		tempColorAB.paint(g);
	}

	public void hsb(Graphics g) {
		tempColorAB.setBackgroundColor(new Color(hsbFader.get(0).getCurrentColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		tempColorAB.setBackgroundColor(new Color(tempColorAB.getBackgroundColor().getRed(),hsbFader.get(1).getCurrentColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		tempColorAB.setBackgroundColor(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),hsbFader.get(2).getCurrentColor().getBlue()));
		
		float hsb[] = new float[3];
		Color.RGBtoHSB(tempColorAB.getBackgroundColor().getRed(), tempColorAB.getBackgroundColor().getGreen(), tempColorAB.getBackgroundColor().getBlue(), hsb);
		
		hsbFader.get(0).setC1(new Color(0,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		hsbFader.get(0).setC2(new Color(255,tempColorAB.getBackgroundColor().getGreen(),tempColorAB.getBackgroundColor().getBlue()));
		hsbFader.get(1).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),0,tempColorAB.getBackgroundColor().getBlue()));
		hsbFader.get(1).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),255,tempColorAB.getBackgroundColor().getBlue()));
		hsbFader.get(2).setC1(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),0));
		hsbFader.get(2).setC2(new Color(tempColorAB.getBackgroundColor().getRed(),tempColorAB.getBackgroundColor().getGreen(),255));
		
		for(ColorFader cf : hsbFader)
			cf.paint(g);
		tempColorAB.paint(g);
	}
	
	public void text(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {

		ColorChooser cc = new ColorChooser();
		cc.setVisible(true);

	}

	public Color getCurrentColor() {
		return currentColor.getBackgroundColor();
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor.setBackgroundColor(currentColor);
	}

	public Vector<AwtButton> getColorPalette() {
		return colorPalette;
	}

	public void setColorPalette(Vector<AwtButton> colorPalette) {
		this.colorPalette = colorPalette;
	}

}
