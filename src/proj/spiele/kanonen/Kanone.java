package proj.spiele.kanonen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import lib.Site;
import lib.awt.Hitbox;


public class Kanone {

	/** X - Mittelpunkt des Rohres */
	private int mxRohr;
	/** Y - Mittelpunkt des Rohres */
	private int myRohr;
	/** X - Mittelpunkt des Rades */
	private int mxRad;
	/** X - Mittelpunkt des Rades */
	private int myRad;
	/** Winkel der Kanone in mathematisch positiver Richtung */
	private int angle;
	/** Breite der Kanone */
	private int width;
	/** Hoehe der Kanone */
	private int height;
	/** Healthpoints der Kanone */
	private int HP = 10;
	/** Information, auf welcher Seite die Kanone steht */
	private Site site;
	/** Zaehlt, wie oft geschossen wurde */
	private int shootCounter;
	/** Hitbox */
	private Hitbox hb;
	/** Summe aller gesammelter Healthpacks */
	private int healthpackCounter;
	/** Summe aller gesammelten damageBooster */
	private int damageBoostCounter;
	/** Summe aller gesammelten bandagen */
	private int bandageCounter;
	/** Kanonenkugel */
	private KanonenKugel kugel;
	/** Bild des Kanonenrohres */
	private BufferedImage rohrImg;
	/** Bild des Kanonenrades */
	private BufferedImage radImg;
	
	
	// Konstruktor
	public Kanone(int mx, int my, int angle, BufferedImage rohr, BufferedImage rad, Site site) {
		this.mxRohr = this.mxRad = mx;
		this.myRohr = this.myRad = my;
		this.angle = angle;
		this.rohrImg = rohr;
		this.radImg = rad;
		this.site = site;
		this.width = this.height = 100;
		kugel = new KanonenKugel(10,mx,my,0.1,0.1,Color.BLACK);	//Kanonenkugel wird definiert
		hb = new Hitbox(70,30,mx,my,site == Site.LEFT?25:45,30,angle); //Hitbox wird definiert
	}
	
	/**
	 * Zeichnet die Kanone mit Kugel
	 * @param g Graphicshandler
	 */
	public void paint(Graphics g) {
		kugel.paint(g); // Kanonenkugel wird gezeichnet
		
		Graphics2D g2 = (Graphics2D) g;	//Graphics2D Handler fuer rotation wird erstellt
		g2.translate(mxRad, myRad);	// Mittelpunkt der Zeichenflaeche wird versetzt
		g2.rotate(Math.toRadians(-angle)); // Zeichenflaeche wird gedreht
		g.drawImage(rohrImg,-width/2,-height/2,width,height,null); //Kanonenrohr wird gezeichnet
		g2.rotate(Math.toRadians(angle)); //Zeichenflaeche wird zurueckgehdreht
		g2.translate(-mxRad, -myRad); // Mittelpunkt der Zeichenflaeche wird zurueckgesetzt
		g.drawImage(radImg,mxRad-width/2,myRad-height/2,width,height,null); // KanonenRad wird gezeichnet
		
		//hb.paint(g); //Hitbox kann um Rohr gezeichnet werden
	}

	public int getMxRohr() {
		return mxRohr;
	}

	public void setMxRohr(int mxRohr) {
		this.mxRohr = mxRohr;
	}

	public int getMyRohr() {
		return myRohr;
	}

	public void setMyRohr(int myRohr) {
		this.myRohr = myRohr;
	}

	public int getMxRad() {
		return mxRad;
	}

	public void setMxRad(int mxRad) {
		this.mxRad = mxRad;
	}

	public int getMyRad() {
		return myRad;
	}

	public void setMyRad(int myRad) {
		this.myRad = myRad;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
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

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		if(hP<0)
			HP = 0;
		else if(hP>100)
			HP = 100;
		else
			HP = hP;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public int getShootCounter() {
		return shootCounter;
	}

	public void setShootCounter(int shootCounter) {
		this.shootCounter = shootCounter;
	}

	public Hitbox getHb() {
		return hb;
	}

	public void setHb(Hitbox hb) {
		this.hb = hb;
	}

	public int getHealthpackCounter() {
		return healthpackCounter;
	}

	public void setHealthpackCounter(int healthpackCounter) {
		this.healthpackCounter = healthpackCounter;
	}

	public int getDamageBoostCounter() {
		return damageBoostCounter;
	}

	public void setDamageBoostCounter(int damageBoostCounter) {
		this.damageBoostCounter = damageBoostCounter;
	}

	public int getBandageCounter() {
		return bandageCounter;
	}

	public void setBandageCounter(int bandageCounter) {
		this.bandageCounter = bandageCounter;
	}

	public KanonenKugel getKugel() {
		return kugel;
	}

	public void setKugel(KanonenKugel kugel) {
		this.kugel = kugel;
	}

	public BufferedImage getRohrImg() {
		return rohrImg;
	}

	public void setRohrImg(BufferedImage rohrImg) {
		this.rohrImg = rohrImg;
	}

	public BufferedImage getRadImg() {
		return radImg;
	}

	public void setRadImg(BufferedImage radImg) {
		this.radImg = radImg;
	}

	

	
	
}
