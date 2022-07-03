package proj.perlenweben;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import lib.awt.AwtButton;
import lib.awt.ColorChooser;
import lib.awt.FensterAnimated;

/**
 * ArmbandDesginer ist ein Programm mit dem ein Perlenarmband entworfen werden kann
 * und anschliessen als png exportiert oder direkt ausgedruckt werden kann um es am Webrahmen
 * Realitaet werden zu lassen
 * @author Clemens Freudenthaler
 * @version 2
 *
 */
public class ArmbandDesigner extends FensterAnimated {

	/** Weils halt sein muss */
	private static final long serialVersionUID = 1L;
	/** String mit der Information welches OS verwendet wird */
	private static String OS = System.getProperty("os.name").toLowerCase();
	/** Hoehe der Headline */
	private int hlh;
	/** Breite der sidebar */
	private final int sidebarWidth = 30;

	/** X-Felder des Armbands */
	private int xf;
	/** Y-Felder des Armbands */
	private int yf;
	/** pxBreite und Hoehe einer Farbflaeche */
	private int wh = 20;
	/** Vector mit allen gesetzten Farbfeldern */
	private Vector<AwtButton> felder;
	/** True, wenn Farbfelder angezeigt werden */
	private boolean colorChooser = false;
	/** Farbpalette */
	private Vector<AwtButton> colorPalette = new Vector<AwtButton>();
	/** Aktuelles Feld welches mit dem Courser ausgewahlt werden kann */
	private int currentFeld = 0;
	/** Markierung */
	private Rectangle mark;
	/** True, solange gespeichert ist */
	private boolean saved = true;
	/** True wenn SaveBeforeQuit gecancelt wurde */
	private boolean cancel = false;
	/** Menubar */
	private MenuBar menuBar;
	/** Color Chooser */
	private ColorChooser chooser;
	/** Filename des aktuellen Armbands */
	private File currentFile = null;
	/** CurrentColor Button in SideBar */
	private AwtButton currentColorAB;
	/** Kopierte Felder */
	private Vector<Color> copyColor;
	/** Felder in XRichtung bei den kopierten Farben */
	private int cxf;

	/**
	 * Konstruktor
	 */
	public ArmbandDesigner() {
		this.setTitle("ArmbandDesigner");// FensterTitel

		// Stellt MenuBar bereit
		prepareMenu();

		// Differenziert Zwischen Windows und Macintosh
		if (OS.indexOf("win") >= 0)
			hlh = 30;
		else if (OS.indexOf("mac") >= 0) {
			hlh = 22;
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");
		}

		this.xf = 10;
		this.yf = 20;

		// Erstellt einen Vector mit allen Farbfeldern
		felder = new Vector<AwtButton>();
		for (int i = 0; i < yf; i++) {
			for (int j = 0; j < xf; j++)
				felder.add(new AwtButton(j * wh, hlh + i * wh, wh, wh));
		}

		mark = new Rectangle(felder.get(currentFeld).getX(), felder.get(currentFeld).getY(), wh, wh);

		// Erstellt FarbButton fuer Sidebar
		currentColorAB = new AwtButton(xf * wh, hlh, sidebarWidth, 2 * wh);
		currentColorAB.setBackgroundColor(Color.RED);

		// Farbpalette in der SideBar
		for (int i = 0; i < 10; i++) {
			colorPalette.add(new AwtButton(xf * wh + (sidebarWidth - wh) / 2, hlh + (i + 3) * wh + 3, wh, wh - 4));
		}

		// Farbenauswahl
		chooser = new ColorChooser();

		// Kopiere Felder, wird erst bei Strg+C belegt
		copyColor = null;

		this.setSize(xf * wh + sidebarWidth, hlh + yf * wh);
		this.setVisible(true);
	}

	/**
	 * Bereitet die MenuBar vor und fuegt ActionListener hinzu
	 */
	public void prepareMenu() {
		menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		Menu export = new Menu("Export");
		Menu editMenu = new Menu("Edit");
		Menu windowMenu = new Menu("Window");
		Menu size = new Menu("Size");
		Menu helpMenu = new Menu("Help");

		MenuItem newItem = new MenuItem("New", new MenuShortcut(KeyEvent.VK_N));
		MenuItem openItem = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
		MenuItem saveItem = new MenuItem("Save", new MenuShortcut(KeyEvent.VK_S));
		MenuItem saveAsItem = new MenuItem("Save As", new MenuShortcut(KeyEvent.VK_S, true));
		MenuItem printItem = new MenuItem("Print", new MenuShortcut(KeyEvent.VK_P));

		MenuItem exportPNGItem = new MenuItem("to PNG", new MenuShortcut(KeyEvent.VK_E, true));
		MenuItem exportSVGItem = new MenuItem("to SVG");

		MenuItem copyItem = new MenuItem("Copy", new MenuShortcut(KeyEvent.VK_C));
		MenuItem pasteItem = new MenuItem("Paste", new MenuShortcut(KeyEvent.VK_V));
		MenuItem selectAllItem = new MenuItem("Select All", new MenuShortcut(KeyEvent.VK_A));
		MenuItem addRowItem = new MenuItem("Add Row", new MenuShortcut(KeyEvent.VK_PLUS));
		MenuItem delRowItem = new MenuItem("Delete Row", new MenuShortcut(KeyEvent.VK_MINUS));
		MenuItem addColumnItem = new MenuItem("Add Column", new MenuShortcut(KeyEvent.VK_PLUS, true));
		MenuItem delColumnItem = new MenuItem("Delete Column", new MenuShortcut(KeyEvent.VK_MINUS, true));

		MenuItem s50Item = new MenuItem("50%");
		MenuItem s75Item = new MenuItem("75%");
		MenuItem s100Item = new MenuItem("100%");
		MenuItem s125Item = new MenuItem("125%");
		MenuItem s150Item = new MenuItem("150%");

		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		printItem.addActionListener(this);
		exportPNGItem.addActionListener(this);
		exportSVGItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		selectAllItem.addActionListener(this);
		addRowItem.addActionListener(this);
		delRowItem.addActionListener(this);
		addColumnItem.addActionListener(this);
		delColumnItem.addActionListener(this);

		s50Item.addActionListener(this);
		s75Item.addActionListener(this);
		s100Item.addActionListener(this);
		s125Item.addActionListener(this);
		s150Item.addActionListener(this);

		export.add(exportPNGItem);
		export.add(exportSVGItem);

		size.add(s50Item);
		size.add(s75Item);
		size.add(s100Item);
		size.add(s125Item);
		size.add(s150Item);

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(export);
		fileMenu.add(printItem);

		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(selectAllItem);
		editMenu.add(addRowItem);
		editMenu.add(delRowItem);
		editMenu.add(addColumnItem);
		editMenu.add(delColumnItem);

		windowMenu.add(size);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(windowMenu);
		menuBar.setHelpMenu(helpMenu);

		this.setMenuBar(menuBar);
	}

	/**
	 * Faengt jede Aktion des Menues ein und verarbeitet diese
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Erstellt ein leeres Armband mit der alten Groesse
		if (e.getActionCommand().equals("New")) {
			neu();
		}

		// Oeffnet ein schon bestehendes Armband
		else if (e.getActionCommand().equals("Open")) {
			open();
		}

		// Speichert das Armband ab
		// Wenn noch kein File besteht wird nach einem neuen Speicherort gefragt
		else if (e.getActionCommand().equals("Save")) {
			save();
		}

		// Speichert das Armband an einer neuen Adresse
		else if (e.getActionCommand().equals("Save As")) {
			currentFile = null;
			save();
		}

		// Schickt das Img zum zuletzt verwendeten Drucker
		else if (e.getActionCommand().equals("Print")) {
			print();
		}

		// Exportiert das Armband als PNG Bild
		else if (e.getActionCommand().equals("to PNG")) {
			exportToPNG();
		}

		// Exportiert das Armband als SVG Bild
		else if (e.getActionCommand().equals("to SVG")) {
			exportToSVG();
		}

		// Fuegt eine Spalte hinzu
		else if (e.getActionCommand().equals("Add Column")) {
			xf++;
			for (int i = 1; i <= yf; i++) {
				felder.add(i * xf - 1, new AwtButton((xf - 1) * wh, hlh + (i - 1) * wh, wh, wh));
			}

			currentColorAB.move(wh, 0);
			for (AwtButton ab : colorPalette) {
				ab.move(wh, 0);
			}
		}

		// Entfernt eine Spalte
		else if (e.getActionCommand().equals("Delete Column")) {
			xf--;
			if (xf < 4)
				xf = 4;
			else {
				for (int i = 0; i < yf; i++) {
					felder.remove((xf + 1) + (xf) * i - 1);
				}
				currentColorAB.move(-wh, 0);
				for (AwtButton ab : colorPalette) {
					ab.move(-wh, 0);
				}
			}

		}

		// Fuegt eine Zeile hinzu
		else if (e.getActionCommand().equals("Add Row")) {
			yf++;
			for (int i = 0; i < xf; i++) {
				felder.add(new AwtButton(i * wh, hlh + (yf - 1) * wh, wh, wh));
			}
		}

		// Entfernt eine Zeile
		else if (e.getActionCommand().equals("Delete Row")) {
			yf--;
			for (int i = 0; i < xf; i++) {
				felder.remove(felder.size() - 1);
			}
		}

		// Setzt die Feldergroesse auf 50 prozent
		else if (e.getActionCommand().equals("50%")) {

		}

		// Setzt die Feldergroesse auf 75 prozent
		else if (e.getActionCommand().equals("75%")) {

		}

		// Setzt die Feldergroesse auf 100 prozent
		else if (e.getActionCommand().equals("100%")) {

		}

		// Setzt die Feldergroesse auf 125 prozent
		else if (e.getActionCommand().equals("125%")) {

		}

		// Setzt die Feldergroesse auf 150 prozent
		else if (e.getActionCommand().equals("150%")) {

		}
		
		// Kopiert den markierten Bereich
		else if (e.getActionCommand().equals("Copy") && (mark.height >= wh || mark.width >= wh)) {
			copy();
		}
		
		// Kopiert den markierten Bereich
		else if (e.getActionCommand().equals("Paste") && copyColor != null) {
			paste();
		}
		
		// Kopiert den markierten Bereich
		else if (e.getActionCommand().equals("Select All")) {
			selectAll();		
		}

		resize();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseReleased(MouseEvent e) {
		saved = false;
		if (e.getX() > 0 && e.getX() < xf * wh && e.getY() > hlh && e.getY() < hlh + yf * wh && this.getCursorType() != Cursor.CROSSHAIR_CURSOR) { // Faerbt ein Feld ein
			for (int i = 0; i<felder.size()-1; i++) {
				AwtButton ab = felder.get(i);
				if (ab.isClicked(e)) {
					if (ab.getBackgroundColor() == currentColorAB.getBackgroundColor())
						ab.setBackgroundColor(new Color(255, 255, 255));
					else
						ab.setBackgroundColor(currentColorAB.getBackgroundColor());
				}
			}
		} else if (e.getX() > xf * wh && e.getX() < xf * wh + sidebarWidth && e.getY() > hlh // Klappt den ColorChanger
																								// aus
				&& e.getY() < hlh + sidebarWidth) {
			colorChooser = !colorChooser;
			chooser.setVisible(colorChooser);

			resize();

		}

		for (AwtButton ab : colorPalette) {
			if (ab.isClicked(e))
				currentColorAB.setBackgroundColor(ab.getBackgroundColor());
		}
		this.setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() >= 0 && e.getY() >= hlh && e.getX() <= xf*wh && e.getY() <= hlh+yf*wh) {
			for (int i = 0; i<felder.size()-1; i++) {
				AwtButton ab = felder.get(i);
				if (ab.isClicked(e)) {
					currentFeld = i;
					mark.x = ab.getX(); mark.y = ab.getY();
					mark.width = mark.height = wh;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseDragged(MouseEvent e) {
		this.setCursor(Cursor.CROSSHAIR_CURSOR);
		if(e.getX() >= 0 && e.getY() >= hlh && e.getX() <= xf*wh && e.getY() <= hlh+yf*wh) {
			for (int i = 0; i<felder.size()-1; i++) {
				AwtButton ab = felder.get(i);
				if (ab.isClicked(e)) {
					//currentFeld = i;
					mark.width = ab.getX()-mark.x+wh; mark.height = ab.getY()-mark.y+wh;
				}
			}
		}
	}

	/**
	 * Deckt alle KeyEvents ab die nicht im Menu sind
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_9) {
			currentColorAB.setBackgroundColor(colorPalette.get(e.getKeyCode() - KeyEvent.VK_1).getBackgroundColor());
		} else if (e.getKeyCode() == KeyEvent.VK_0) {
			currentColorAB.setBackgroundColor(colorPalette.get(9).getBackgroundColor());

		} else if (e.getKeyCode() == KeyEvent.VK_UP && e.isShiftDown()) {
			if (mark.height - wh < wh)
				mark.height = wh;
			else {
				mark.height -= wh;
				currentFeld -= xf;
				if (currentFeld < 0)
					currentFeld += xf;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && e.isShiftDown()) {
			currentFeld += xf;
			if (currentFeld > xf * yf)
				currentFeld -= xf;
			mark.height = mark.height + wh;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && e.isShiftDown()) {
			if (mark.width - wh < wh)
				mark.width = wh;
			else {
				mark.width -= wh;
				currentFeld--;
				if (currentFeld < 0)
					currentFeld = 0;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.isShiftDown()) {
			currentFeld++;
			if (currentFeld > xf * yf)
				currentFeld = xf * yf;
			mark.width = mark.width + wh;

		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			mark.width = mark.height = wh;
			currentFeld -= xf;
			if (currentFeld < 0)
				currentFeld += xf;
			mark.x = felder.get(currentFeld).getX();
			mark.y = felder.get(currentFeld).getY();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			mark.width = mark.height = wh;
			currentFeld += xf;
			if (currentFeld > xf * yf)
				currentFeld -= xf;
			mark.x = felder.get(currentFeld).getX();
			mark.y = felder.get(currentFeld).getY();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			mark.width = mark.height = wh;
			currentFeld--;
			if ((currentFeld + 1) % xf == 0) {
				currentFeld++;
			}
			mark.x = felder.get(currentFeld).getX();
			mark.y = felder.get(currentFeld).getY();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mark.width = mark.height = wh;
			currentFeld++;
			if ((currentFeld) % xf == 0) {
				currentFeld--;
			}
			mark.x = felder.get(currentFeld).getX();
			mark.y = felder.get(currentFeld).getY();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (felder.get(currentFeld).getBackgroundColor() == currentColorAB.getBackgroundColor())
				felder.get(currentFeld).setBackgroundColor(new Color(255, 255, 255));
			else
				felder.get(currentFeld).setBackgroundColor(currentColorAB.getBackgroundColor());
		} else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isMetaDown()) {
			for (AwtButton ab : felder) {
				if (ab.getX() >= mark.x && ab.getY() >= mark.y && ab.getX() < mark.x + mark.width
						&& ab.getY() < mark.y + mark.height) {
					ab.setBackgroundColor(Color.WHITE);
				}
			}
		}

	}

	/**
	 * Schliesst den ArmbandDesigner
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		while (!cancel) {
			if (saved) {
				System.exit(0);
			} else {
				cancel = false;
				saveBeforeQuit();
			}
		}
		cancel = false;
	}

	/**
	 * Zeichnet die Arbeitsflaeche auf den Screen
	 */
	@Override
	public void paint(Graphics g) {

		g.setColor(new Color(80, 80, 80));
		g.fillRect(xf * wh, hlh, this.getWidth() - xf * wh, this.getHeight());

		sidebar(g);
		for (AwtButton ab : felder)
			ab.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		for (int i = 1; i <= xf; i++)
			g.drawLine(wh * i, hlh, wh * i, hlh + yf * wh); // Streifen in Y-Richtung
		for (int i = 1; i <= yf; i++)
			g.drawLine(0, hlh + wh * i, xf * wh, hlh + wh * i); // Streifen in X-Richtung

		g2.setStroke(new BasicStroke(3));
		g.drawRect(mark.x, mark.y, mark.width, mark.height);
	}

	/**
	 * Auswahlfenster fuer Save, Cancel und Quit
	 */
	public void saveBeforeQuit() {
		String[] options = { "Save", "Cancel", "Quit" };

		int x = JOptionPane.showOptionDialog(null, "", "Save Before Quit", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		switch (x) {
		case 0:
			save();
			break;
		case 1:
			saved = false;
			cancel = true;
			break;
		case 2:
			saved = true;
			break;
		}

	}

	/**
	 * Erstelle eine neue leere Arbeitsflaeche
	 */
	public void neu() {
		while (!cancel) {
			if (saved) {
				cancel = true;
				felder = new Vector<AwtButton>();
				for (int i = 0; i < yf; i++) {
					for (int j = 0; j < xf; j++)
						felder.add(new AwtButton(j * wh, hlh + i * wh, wh, wh));
				}
			} else {
				cancel = false;
				saveBeforeQuit();
			}
		}
		cancel = false;
	}

	/**
	 * Speichert das Armband in einem bestehenden oder neuen File
	 */
	public void save() {
		if (currentFile == null) {
			FileDialog fileDialog = new FileDialog(new Frame(), "Save Armband", FileDialog.SAVE);
			fileDialog.setFilenameFilter(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".perarm");
				}
			});
			fileDialog.setFile("Untitled.perarm");
			fileDialog.setVisible(true);
			currentFile = new File(fileDialog.getDirectory() + fileDialog.getFile());
		}
		System.out.println(currentFile.getPath());

		if (currentFile.getPath().equals("nullnull")) {
			saved = false;
		} else {
			FileWriter fw = null;
			try {
				fw = new FileWriter(currentFile);
				fw.write(LocalDate.now().toString()+"\n\n");
				fw.write("xf:" + xf + "\n");
				fw.write("yf:" + yf + "\n");
				fw.write("\nFarbflaechen:\n");
				for (AwtButton ab : felder) {
					fw.write(ab.getBackgroundColor() + "\n");
				}
				fw.write("\nFarbpalette:\n");
				for (AwtButton ab : colorPalette) {
					fw.write(ab.getBackgroundColor() + "\n");
				}
				fw.write("end");
				saved = true;
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Oeffnet ein Armband aus einem File
	 */
	public void open() {
		
		while(!cancel) {
			if(saved) {
				cancel = true;
				FileDialog fileDialog = new FileDialog(new Frame(), "Load Armband", FileDialog.LOAD);
				fileDialog.setFilenameFilter(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.endsWith(".perarm");
					}
				});
				fileDialog.setVisible(true);
				currentFile = new File(fileDialog.getDirectory() + fileDialog.getFile());
		
				try {
					FileReader fr = new FileReader(currentFile);
					int anz;
					char buffer[] = new char[10];
					Vector<String> input = new Vector<String>();
					String temp = "";
		
					while ((anz = fr.read(buffer)) > 0) {
						for (int i = 0; i < anz; i++) {
							char z = buffer[i];
		
							if (z == '\n') {
								input.add(temp);
								if (temp.indexOf("end") >= 0)
									break;
								temp = "";
							} else
								temp += z;
						}
					}
					for (int i = 0; i < input.size(); i++) {
						String s = input.get(i);
						if (s.indexOf("xf:") >= 0) {
							String arr[] = s.split(":");
							xf = Integer.parseInt(arr[1]);
						} else if (s.indexOf("yf:") >= 0) {
							String arr[] = s.split(":");
							yf = Integer.parseInt(arr[1]);
						} else if (s.indexOf("Farbflaechen:") >= 0) {
							felder = new Vector<AwtButton>();
							for (int j = 0; j < yf; j++) {
								for (int k = 0; k < xf; k++) {
									felder.add(new AwtButton(k * wh, hlh + j * wh, wh, wh));
									s = input.get(++i);
									int r, g, b;
									String arr[] = s.split("=");
									r = Integer.parseInt(arr[1].replaceAll(",g", ""));
									g = Integer.parseInt(arr[2].replaceAll(",b", ""));
									b = Integer.parseInt(arr[3].replaceAll("]", ""));
		
									System.out.println(felder.get(felder.size() - 1));
		
									felder.get(felder.size() - 1).setBackgroundColor(new Color(r, g, b));
								}
							}
						} else if (s.indexOf("Farbpalette:") >= 0) {
							int r, g, b;
							
							for (AwtButton ab : colorPalette) {
								s = input.get(++i);
								String arr1[] = s.split("=");
								r = Integer.parseInt(arr1[1].replaceAll(",g", ""));
								g = Integer.parseInt(arr1[2].replaceAll(",b", ""));
								b = Integer.parseInt(arr1[3].replaceAll("]", ""));
								ab.setBackgroundColor(new Color(r,g,b));
							}
						}
					}
					for(int i = 0; i<10; i++)
						chooser.getColorPalette().get(i).setBackgroundColor(colorPalette.get(i).getBackgroundColor());
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				cancel = false;
				saveBeforeQuit();
			}
				
		}
		cancel = false;
	}

	/**
	 * Kopiert den markierten Bereich
	 */
	public void copy() {
		copyColor = new Vector<Color>();
		for (AwtButton ab : felder) {
			if (ab.getX() >= mark.x && ab.getY() >= mark.y && ab.getX() < mark.x + mark.width
					&& ab.getY() < mark.y + mark.height) {
				copyColor.add(ab.getBackgroundColor());
				System.out.println(copyColor.get(copyColor.size()-1));
			}
		}
		cxf = mark.width / wh;
	}
	
	/** 
	 * Fuegt den kopierten Bereich beim currentFeld ein 
	 */
	public void paste() {
		int x = 0;
		for(int j = 0; j < copyColor.size() / cxf; j++) {
			for(int i = 0; i<cxf; i++) {
				felder.get(i+j*xf + currentFeld).setBackgroundColor(copyColor.get(x));
				x++;
			}
		}
	}
	
	/** 
	 * Wahlt das ganze Feld aus 
	 */
	public void selectAll() {
		mark.x = 0; mark.y = hlh; mark.width = xf*wh; mark.height = yf*wh;
		currentFeld = felder.size()-1;
	}
	
	/**
	 * Exportiert das Armband als SVG-File
	 */
	public void exportToSVG() {
		FileDialog fileDialog = new FileDialog(new Frame(), "Export SVG", FileDialog.SAVE);
		fileDialog.setFilenameFilter(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".svg");
			}
		});
		fileDialog.setFile(stripExtension((currentFile == null) ? "Untitled" : currentFile.getName()) + ".svg");
		fileDialog.setVisible(true);
		File svgFile = new File(fileDialog.getDirectory() + fileDialog.getFile());

		FileWriter fw = null;
		try {
			fw = new FileWriter(svgFile);
			fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + xf * wh + "\" height=\""
					+ yf * wh + "\">\n");
			for (AwtButton ab : felder) {
				fw.write("<rect x=\"" + ab.getX() + "\" y=\"" + (ab.getY() - hlh) + "\" width=\"" + wh + "\" height=\""
						+ wh + "\"  fill=\"" + format(ab.getBackgroundColor()) + "\"/>\n");
			}
			for (int i = 1; i <= xf; i++)
				fw.write("<line x1=\"" + wh * i + "\" y1=\"" + 0 + "\" x2=\"" + wh * i + "\" y2=\"" + yf * wh
						+ "\" style=\"stroke:rgb(0,0,0);stroke-width:1\"/>\n");
			for (int i = 1; i <= yf; i++)
				fw.write("<line x1=\"" + 0 + "\" y1=\"" + wh * i + "\" x2=\"" + (this.getHeight() - hlh) + "\" y2=\""
						+ wh * i + "\" style=\"stroke:rgb(0,0,0);stroke-width:1\"/>\n");
			fw.write("</svg>");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exportiert das Armband als PNG-File
	 */
	public void exportToPNG() {
		FileDialog fileDialog = new FileDialog(new Frame(), "Export PNG", FileDialog.SAVE);
		fileDialog.setFilenameFilter(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".png");
			}
		});
		fileDialog.setFile(stripExtension((currentFile == null) ? "Untitled" : currentFile.getName()) + ".png");
		fileDialog.setVisible(true);
		File pngFile = new File(fileDialog.getDirectory() + fileDialog.getFile());

		BufferedImage bufferedImage = new BufferedImage(xf * wh, yf * wh, BufferedImage.TYPE_INT_RGB);

		Graphics g = bufferedImage.createGraphics();

		for (AwtButton ab : felder) {
			g.setColor(ab.getBackgroundColor());
			g.fillRect(ab.getX(), ab.getY() - hlh, wh, wh);
		}
		g.setColor(Color.BLACK);
		for (int i = 1; i < xf; i++)
			g.drawLine(wh * i, 0, wh * i, yf * wh); // Streifen in Y-Richtung
		for (int i = 1; i < yf; i++)
			g.drawLine(0, wh * i, xf * wh, wh * i); // Streifen in X-Richtung

		g.dispose();
		RenderedImage rendImage = bufferedImage;

		try {
			ImageIO.write(rendImage, "png", pngFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Druckt das Armband mit dem Standartdrucker aus
	 */
	public void print() {

		BufferedImage bufferedImage = new BufferedImage(xf * wh, yf * wh, BufferedImage.TYPE_INT_RGB);

		Graphics g = bufferedImage.createGraphics();

		for (AwtButton ab : felder) {
			g.setColor(ab.getBackgroundColor());
			g.fillRect(ab.getX(), ab.getY() - hlh, wh, wh);
		}
		g.setColor(Color.BLACK);
		for (int i = 1; i < xf; i++)
			g.drawLine(wh * i, 0, wh * i, yf * wh); // Streifen in Y-Richtung
		for (int i = 1; i < yf; i++)
			g.drawLine(0, wh * i, xf * wh, wh * i); // Streifen in X-Richtung

		g.dispose();
		RenderedImage rendImage = bufferedImage;

		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(new Printable() {
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if (pageIndex != 0) {
					return NO_SUCH_PAGE;
				}
				graphics.drawImage((Image) rendImage, 0, 0, rendImage.getWidth(), rendImage.getHeight(), null);
				return PAGE_EXISTS;
			}
		});
		try {
			printJob.print();
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
		if (!saved)
			save();
	}

	/**
	 * Entfernt den Suffix eines Dateinamens
	 * 
	 * @param str ganzer Dateiname
	 * @return nur name, ohne Suffix
	 */
	public static String stripExtension(String str) {
		// Handle null case specially.

		if (str == null)
			return null;

		// Get position of last '.'.

		int pos = str.lastIndexOf(".");

		// If there wasn't any '.' just return the string as is.

		if (pos == -1)
			return str;

		// Otherwise return the string, up to the dot.

		return str.substring(0, pos);
	}

	/**
	 * Formatiert eine Java Color in eine HTML Farbe
	 * 
	 * @param c Farbe welche formatiert wird
	 * @return String mit HTML Farbe
	 */
	public static final String format(Color c) {
		String r = (c.getRed() < 16) ? "0" + Integer.toHexString(c.getRed()) : Integer.toHexString(c.getRed());
		String g = (c.getGreen() < 16) ? "0" + Integer.toHexString(c.getGreen()) : Integer.toHexString(c.getGreen());
		String b = (c.getBlue() < 16) ? "0" + Integer.toHexString(c.getBlue()) : Integer.toHexString(c.getBlue());
		return "#" + r + g + b;
	}

	/**
	 * Passt das Fenster den neuen Groessen an
	 */
	public void resize() {
		if (hlh + yf * wh < colorPalette.get(9).getY() + wh - 1)
			this.setSize(xf * wh + sidebarWidth, colorPalette.get(9).getY() + wh - 1);
		else
			this.setSize(xf * wh + sidebarWidth, hlh + yf * wh);
	}

	public void sidebar(Graphics g) {
		currentColorAB.paint(g);

		if (colorChooser) {
			currentColorAB.setBackgroundColor(chooser.getCurrentColor());
			for (int i = 0; i < 10; i++)
				colorPalette.get(i).setBackgroundColor(chooser.getColorPalette().get(i).getBackgroundColor());
		}

		for (AwtButton ab : colorPalette)
			ab.paint(g);
	}

	/**
	 * Startet einen neuen ArmbandDesginer
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ArmbandDesigner();
	}
}
