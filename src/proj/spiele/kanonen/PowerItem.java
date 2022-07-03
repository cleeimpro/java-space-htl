package proj.spiele.kanonen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerItem {
	
	public static final int MEDKIT = 0;
	public static final int BANDAGE = 1;
	public static final int DAMAGEBOOST = 2;
	
	public enum Item{MEDKIT,BANDAGE,DAMAGEBOOST}
	
	/** File des Bildes */
	private File fmk, fbg, fdb;
	/** Bild des Items */
	private BufferedImage mk, bg, db, img;
	/** Mitte des Items, X und Y Koordinate */
	private int mx, my;
	/** Groesse des Items, X und Y Achse */
	private int width, height;
	/** Breite und Hoehe der Flaeche in welcher gespawnd werden darf */
	private int screenWidth, screenHeight;
	/** Wartezeit bis zum Spawnen eines neun Items */
	private int dT;
	/** Welche Art von Item */
	private Item kindOf;
	/**Timer fuer neuspawn */
	private int timer=0;

	//Konstruktor
	public PowerItem(int screenWidth, int screenHeight) {
		this.fmk = new File("./img/duoKanone/medkit.png");
		this.fbg = new File("./img/duoKanone/bandage.png");
		this.fdb = new File("./img/duoKanone/damageBoost.png");
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.width = this.height = 50;
		this.dT = 10000;

		// Bilder fuer Items werden geladen
		try {
			mk = ImageIO.read(fmk);
			bg = ImageIO.read(fbg);
			db = ImageIO.read(fdb);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		newRandItem();
	}
	
	/**
	 * Random Item wird ausgewaehlt
	 */
	public void newRandItem() {
		switch((int)(Math.random()*3)) {
		case MEDKIT: img = mk; kindOf = Item.MEDKIT; break;
		case BANDAGE: img = bg; kindOf = Item.BANDAGE;break;
		case DAMAGEBOOST: img = db; kindOf = Item.DAMAGEBOOST;break;
		}
		mx = (int) (20 + Math.random() * (screenWidth - 40));
		my = (int) (40 + Math.random() * (screenHeight - 60));
	}

	/**
	 * PowerItem wird gezeichnet
	 * @param g Graphicshandler
	 */
	public void paint(Graphics g) {	
		timer+=20;
		if(timer == dT) {
			newRandItem();
			timer = 0;
		}
		g.drawImage(img, mx-width/2, my-height/2, width, height, null);
	}

	public int getMx() {
		return mx;
	}

	public void setMx(int mx) {
		this.mx = mx;
	}

	public int getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
	}

	public Item getKindOf() {
		return kindOf;
	}

	public void setKindOf(Item kindOf) {
		this.kindOf = kindOf;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
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

	public int getdT() {
		return dT;
	}

	public void setdT(int dT) {
		this.dT = dT;
	} 
	

}
