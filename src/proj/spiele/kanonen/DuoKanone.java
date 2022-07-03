package proj.spiele.kanonen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lib.Site;
import lib.awt.Brick;
import lib.awt.FensterAnimated;
import lib.awt.Hitbox;
import lib.awt.Load;
import lib.awt.Wall;

public class DuoKanone extends lib.awt.FensterAnimated {

	private static final long serialVersionUID = 1L;

	/** Kanonen Links und Rechts */
	private Kanone kanL, kanR;
	/** PowerItem */
	private PowerItem powerItem;
	/** Wall in der Mitte */
	private Wall wall;

	// Bilder Zwecks der Optik
	private BufferedImage grass = null;
	private BufferedImage KanoneMitFunken = null;
	private BufferedImage kPic = null;
	private BufferedImage kanRad = null;
	private BufferedImage icon = null;

	/** Ladescreen */
	private Load load;

	// Schriftarten
	private Font fontEnd;
	private Font fontEndB;
	/** Seite, welche gerade am Zug ist */
	Site site = Site.LEFT;
	/** true, wenn Maus gedrueckt ist */
	private boolean mousePressedBol = false;
	/** true, wenn eine Kugel fliegt */
	private boolean kugelFliegt = false;
	/** true, wenn Startanimation laeuft */
	private boolean startAnimation = true;
	/** Wird solange hochgezaehlt wie Maus gedrueckt ist */
	private int speedTimer = 0;
	/** Geschwindigkeit der aktuellen Kanonekugel */
	private double speedKugel;
	
	private int dT1 = 0 , dT2 = 0;

	// Konstruktor
	public DuoKanone() {

		load = new Load(this.getWidth(), this.getHeight(), 50); // startet Ladescreen
		this.setTitle("Duo Kanone V1.0"); // Setzt Titel des Fenster

		// Definiert Schriftarten
		fontEnd = new Font("CenturyGothicStandart", Font.PLAIN, 12);
		fontEndB = new Font("CenturyGothicStandart", Font.BOLD, 12);

		powerItem = new PowerItem(this.getWidth(), this.getHeight() / 2); // Erstellt ein neues PowerItem

		// Laed alle Bilder
		try {
			File fRad = new File("./img/duoKanone/KanoneRad.png");
			File fPic = new File("./img/duoKanone/KanoneKoerper.png");
			File ficon = new File("./img/duoKanone/KanoneFarbe.png");
			File fgrass = new File("./img/duoKanone/grass.png");
			File fKanoneMitFunken = new File("./img/duoKanone/KanoneMitFunken.png");
			kanRad = ImageIO.read(fRad);
			kPic = ImageIO.read(fPic);
			icon = ImageIO.read(ficon);
			KanoneMitFunken = ImageIO.read(fKanoneMitFunken);
			grass = ImageIO.read(fgrass);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		this.setIconImage(icon); // Wirkt nur bei Windows

		kanL = new Kanone(60, this.getHeight() - 20, 45, KanoneMitFunken, kanRad, Site.LEFT); // Rechte Kanone erzeugen
		kanR = new Kanone(this.getWidth() - 60, this.getHeight() - 20, 315, mirrorImg(kPic), kanRad, Site.RIGHT); // Linke
		wall = new Wall(this.getWidth() / 2 - 150, 0, 300, this.getHeight(), 60); // Wall in der Mitte definieren

		this.setVisible(true); // AWT Fenster wird sichtbar
		load.setVisible(false); // Load wird unsichtbar
	}

	/**
	 * Bewegt die aktuelle Kanone im richtigen Winkel zur Maus
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		switch (site) {
		case LEFT:
			kanL.setAngle((int) (Math.toDegrees(Math.atan2(kanL.getMyRohr() - e.getY(), e.getX() - kanL.getMxRohr()))));
			if (kanL.getAngle() > 90)
				kanL.setAngle(90);
			else if (kanL.getAngle() < 0)
				kanL.setAngle(0);
			break;
		case RIGHT:
			kanR.setAngle(
					(int) (270 + Math.toDegrees(Math.atan2(kanR.getMxRohr() - e.getX(), kanR.getMyRohr() - e.getY()))));
			if (kanR.getAngle() < 270)
				kanR.setAngle(270);
			else if (kanR.getAngle() > 360)
				kanR.setAngle(360);
			break;
		}
	}

	/**
	 * Startet den Speedcounter
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!kugelFliegt) {
			mousePressedBol = true;
		}
	}

	/**
	 * Setzt Flugwinkel - , Mx und my - , Speed - , Danger - der KanonenKugel, und
	 * zählt den ShootCounter eins nach oben
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		switch (site) {
		case LEFT:
			kanL.getKugel().setFlightAngle(kanL.getAngle()); // Setzt Flugwinkel der linken Kanonenkugel
			kanL.getKugel().setMx(kanL.getMxRohr() - Math.sin(Math.toRadians(kanL.getAngle())) * 16);
			kanL.getKugel().setMy(kanL.getMyRohr() - Math.cos(Math.toRadians(kanL.getAngle())) * 16);
			kanL.getKugel().setSpeed(speedKugel);
			kanL.getKugel().setDanger(true);
			kanL.setShootCounter(kanL.getShootCounter() + 1);
			break;
		case RIGHT:
			kanR.getKugel().setFlightAngle(-kanR.getAngle()); // Setzt Flugwinkel der rechte Kanonenkugel
			kanR.getKugel().setVx(-kanR.getKugel().getVx()); // Dreht geschwindigkeitsVector der Kanonenkugel um
			kanR.getKugel().setMx(kanR.getMxRohr() - Math.cos(Math.toRadians(-kanR.getAngle() - 270)) * 16);
			kanR.getKugel().setMy(kanR.getMyRohr() - Math.sin(Math.toRadians(-kanR.getAngle() - 270)) * 16);
			kanR.getKugel().setSpeed(speedKugel);
			kanR.getKugel().setDanger(true);
			kanR.setShootCounter(kanR.getShootCounter() + 1);
			break;
		}
		speedKugel = 0;
		speedTimer = 0;
		mousePressedBol = false;
		kugelFliegt = true;
	}

	/**
	 * Setzt bei R das Spiel zurück Schliesst bei ESCAPE das Fenster
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			if (!FensterAnimated.RUN) {
				kanL = new Kanone(60, this.getHeight() - 20, 45, KanoneMitFunken, kanRad, Site.LEFT); // Rechte Kanone
																										// // erzeugen
				kanR = new Kanone(this.getWidth() - 60, this.getHeight() - 20, 315, mirrorImg(kPic), kanRad,
						Site.RIGHT); // Linke
				FensterAnimated.RUN = true;
			}
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}

	}

	/**
	 * Zeichnet das Fenster mit allen Inhalten
	 */
	@Override
	public void paint(Graphics g) {
		powerItem.paint(g); // PowerItem wird gezeichnet
		wall.paint(g); // Wand wird gezeichnet
		if (startAnimation) {
			g.drawImage(grass, 0, this.getHeight() - 40, this.getWidth(), 40, null);
			startCard(g);
		} else {
			if (mousePressedBol) {
				speedTimer += 20;
				speedKugel = speedTimer / 1000.0 > 1.5 ? 1.5 : speedTimer / 1000.0;
			}
			
			if (kugelFliegt) {
				switch (site) {
				case LEFT:
					if (kanL.getKugel().ifMotion()) {
						catchItem();
						kanL.getKugel().calcPhysics(20);
						kanL.getKugel().calcReflex(this.getWidth(),this.getHeight());
					} else {
						kugelFliegt = false;
						kanL.getKugel().setSpeed(0);
						kanL.setRohrImg(kPic);
						kanR.setRohrImg(mirrorImg(KanoneMitFunken));
						site = Site.RIGHT;
					}
					break;
				case RIGHT:
					if (kanR.getKugel().ifMotion()) {
						catchItem();
						kanR.getKugel().calcPhysics(20);
						kanR.getKugel().calcReflex(this.getWidth(),this.getHeight());
					} else {
						kugelFliegt = false;
						kanR.getKugel().setSpeed(0);
						kanR.setRohrImg(mirrorImg(kPic));
						kanL.setRohrImg(KanoneMitFunken);
						site = Site.LEFT;
					}
					break;
				}
			}

			// Kanonen mit Kugeln wird gezeichnet
			kanL.paint(g); 
			kanR.paint(g);

			// Wenn eine Kanonen HP<0 dann Endcard
			if (kanL.getHP() <= 0)
				endCard(Site.RIGHT, g);
			else if (kanR.getHP() <= 0)
				endCard(Site.LEFT, g);

			g.setColor(new Color(0, 204, 51));// Gruen fuer HP anzeige
			g.fillRect(this.getWidth() / 2 - 200, 22 + 10, 180 * kanL.getHP() / 100, 20);
			g.fillRect(this.getWidth() / 2 + 20 + 180 - 180 * kanR.getHP() / 100, 22 + 10, 180 * kanR.getHP() / 100,
					20);
			g.setColor(Color.WHITE);// Weiß fuer HP Anzeige schrift
			g.setFont(fontEndB);
			g.drawString(kanL.getHP() + " | " + 100, this.getWidth() / 2 - 190, 22 + 25);
			g.drawString(kanR.getHP() + " | " + 100, this.getWidth() / 2 + 20 + 110, 22 + 25);

			// Schleuderkraft Anzeige
			for (int i = 0; i < speedKugel * 10; i++) {
				g.setColor(new Color((int) (100 + i * 10), (int) (250 - i * 10), 0));
				g.fillRect(site == Site.LEFT ? 0 : this.getWidth() - 15,
						(int) (this.getHeight() - speedKugel * 10 * (this.getHeight() - 22) / 15), 15,
						(int) (speedKugel * 10 * this.getHeight() / 15) - this.getHeight() / 15 * i);
			}

			g.drawImage(grass, 0, this.getHeight() - 40, this.getWidth(), 40, null);
		}

	}

	/**
	 * 
	 */
	public void startCard(Graphics g) {
		startAnimation = wall.bricksMove();
	}

	/**
	 * Spielende mit Stats
	 * 
	 * @param s Site welche gewonnen hat
	 * @param g Graphic-handler
	 */
	public void endCard(Site s, Graphics g) {
		g.setColor(new Color(253, 191, 0));
		switch (s) {
		case LEFT:
			g.fillRect(this.getWidth() / 2 - 205, 22 + 5, 200, 30);
			break;
		case RIGHT:
			g.fillRect(this.getWidth() / 2 + 15, 22 + 5, 200, 30);
			break;
		}

		g.setColor(Color.BLACK); // Schwarz, fuer Schrift

		// Headline
		g.setFont(fontEndB);
		g.drawString("Stats LEFT", this.getWidth() / 2 - 200, 100);
		g.drawString("Stats RIGHT", this.getWidth() / 2 + 60, 100);

		// HP
		g.setFont(fontEndB);
		g.drawString("HP", this.getWidth() / 2 - 300, 120);
		g.setFont(fontEnd);
		g.drawString(kanL.getHP() + " | 100", this.getWidth() / 2 - 200, 120);
		g.drawString(kanR.getHP() + " | 100", this.getWidth() / 2 + 60, 120);

		// Shoots
		g.setFont(fontEndB);
		g.drawString("Shoots", this.getWidth() / 2 - 300, 140);
		g.setFont(fontEnd);
		g.drawString(kanL.getShootCounter() + "", this.getWidth() / 2 - 200, 140);
		g.drawString(kanR.getShootCounter() + "", this.getWidth() / 2 + 60, 140);

		// Healthpackcounter
		g.setFont(fontEndB);
		g.drawString("Healthpacks", this.getWidth() / 2 - 300, 160);
		g.setFont(fontEnd);
		g.drawString(kanL.getHealthpackCounter() + "", this.getWidth() / 2 - 200, 160);
		g.drawString(kanR.getHealthpackCounter() + "", this.getWidth() / 2 + 60, 160);

		// Bandages
		g.setFont(fontEndB);
		g.drawString("Bandages", this.getWidth() / 2 - 300, 180);
		g.setFont(fontEnd);
		g.drawString(kanL.getBandageCounter() + "", this.getWidth() / 2 - 200, 180);
		g.drawString(kanR.getBandageCounter() + "", this.getWidth() / 2 + 60, 180);

		// DamageBooster
		g.setFont(fontEndB);
		g.drawString("DamageBoost", this.getWidth() / 2 - 300, 200);
		g.setFont(fontEnd);
		g.drawString(kanL.getDamageBoostCounter() + "", this.getWidth() / 2 - 200, 200);
		g.drawString(kanR.getDamageBoostCounter() + "", this.getWidth() / 2 + 60, 200);

		// SpielInfos
		g.drawString("Press R to restart, ESC to quit", this.getWidth() / 2 - 200, 80);
		FensterAnimated.RUN = false;
	}

	/**
	 * Berechnet
	 * 
	 * @param w
	 * @param h
	 * @param k
	 * @param wall
	 */
	public void calcHit() {
		
		Hitbox hb;
		KanonenKugel kugel;
		
		switch(site) {
		case LEFT: hb = kanR.getHb(); kugel = kanL.getKugel(); break;
		case RIGHT: hb = kanL.getHb(); kugel = kanR.getKugel(); break;
		}

		// Kanone
		//TODO Ob Kugel gegnerische Kanone trifft

		// Wall
		for (Brick b : wall.getWall()) {
			//TODO Ob Kugel mit Wall kollidiert
		}
	}

	public void catchItem() {
		Kanone kan = null;
		
		switch(site) {
		case LEFT: kan = kanL; break;
		case RIGHT: kan = kanR; break;
		}
		
		if ((kan.getKugel().getMx() - powerItem.getMx()) * (kan.getKugel().getMx() - powerItem.getMx())
				+ (kan.getKugel().getMy() - powerItem.getMy()) * (kan.getKugel().getMy() - powerItem.getMy()) <= (powerItem.getHeight() / 2 + kan.getKugel().getR() + 10)
						* (powerItem.getWidth() / 2 + kan.getKugel().getR() + 10)) {

			switch (powerItem.getKindOf()) {
			case BANDAGE:
				kan.setHP(kan.getHP() + 20);
				kan.setBandageCounter(kan.getBandageCounter() + 1);
				break;
			case DAMAGEBOOST:
				kan.getKugel().setDamageMulti(kan.getKugel().getDamageMulti() + 0.5);
				kan.setDamageBoostCounter(kan.getDamageBoostCounter() + 1);
				break;
			case MEDKIT:
				kan.setHP(kan.getHP() + 40);
				kan.setHealthpackCounter(kan.getHealthpackCounter() + 1);
				break;
			}
			
			powerItem.setMx(-10);
			powerItem.setMy(-10);
			powerItem.setdT(5000);
			powerItem.setTimer(0);
		}

	}

	/**
	 * Spiegelt ein Image
	 * 
	 * @param img
	 * @return gespiegeltes Image
	 */
	public BufferedImage mirrorImg(BufferedImage img) {
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		BufferedImage imgR = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < height; y++) {
			for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
				int p = img.getRGB(lx, y);
				imgR.setRGB(rx, y, p);
			}
		}
		return imgR;
	}

	public static void main(String[] args) {
		new DuoKanone();
	}

}
