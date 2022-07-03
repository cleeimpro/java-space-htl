package proj.cpe.zweipol;

import lib.Complex;

/**
 * Klasse um mit komplexen Impedanzen in der Wechselstromtechnik zu rechnen
 * 
 * @author Freudenthaler Clemens
 * @date 27.03.2019
 * @version 1.0
 *
 */
public class Zweipol{

	/** Komplexe Zahl Z, Impedanz */
	private Complex Z;
	
	/**
	 * Konstruktor ohne Parameter
	 */
	public Zweipol() {
		this.Z = new Complex(0,0);
	}
	
	/**
	 * Konstruktor mit einem Parameter
	 * @param Z
	 */
	public Zweipol(Complex Z) {
		this.Z = Z;
	}
	
	/**
	 * Ausgabe in Komponentenform
	 * @return String mit Komponentenform
	 */
	public String toString() {
		return Z.toString();
	}
	
	/**
	 * Ausgabe in Polarform
	 * @return String mit Polarform
	 */
	public String toPolar() {
		return getAbs()+" < "+getArg()+" grad";
	}
	
	/**
	 * Gibt den Betrag der komplexen Zahl zurueck
	 * @return der Betrag
	 */
	public double getAbs() {
		return Math.sqrt(Z.getRe()*Z.getRe()+Z.getIm()*Z.getIm());
	}
	
	/**
	 * Gibt den Winkel der komplexen Zahl zurueck
	 * @return der Winkel
	 */
	public double getArg() {
		return Math.atan2(Z.getIm(),Z.getRe());
	}
	
	/**
	 * Beliebige Anzahl an Zweipol Impedanzen serielgeschalten
	 * @param zweipole VarArg mit Impedanzen
	 * @return neues Objekt mit der Summe
	 */
	public static Zweipol ser(Zweipol...zweipole) {
		Complex sum = new Complex(0,0);
		for(int i = 0; i < zweipole.length; i++) {
			sum = sum.add(zweipole[i].Z);
		}
		
		return new Zweipol(sum);
	}
	
	
	
	/**
	 * Beliebige Anzahl an Zweipol Impedanzen parallelgeschalten
	 * @param zweipole VarArg mit Impedanzen
	 * @return neues Objekt mit der Summe
	 */
	public static Zweipol par(Zweipol...zweipole) {
		Complex sum = new Complex(0,0);
		for(int i = 0; i < zweipole.length; i++) {
			sum=sum.add((zweipole[i].Z).inv());
		}
		return new Zweipol(sum.inv());
	}

	/**
	 * Setzt Z als neue komplexe Zahl
	 * @param re Realteil
	 * @param im Imaginaerteil
	 */
	public void setZ(double re, double im){
		this.Z = new Complex(re,im);
	}
	
	/**
	 * Gibt den komplexen Strom durch die Impedanz zurueck
	 * @param U komplexe Spannung
	 * @return komplexer Strom
	 */
	public Zweipol getI(Complex U) {
		return new Zweipol(U.div(Z));
	}
	
	/**
	 * Gibt die komplexe Spannung an der Impedanz zurueck
	 * @param I komplexer Strom
	 * @return komplexe Spannung
	 */
	public Zweipol getU(Complex I) {
		return new Zweipol(I.mul(Z));
	}
	
	/**
	 * Spannungsteiler ueber zwei Widerstaende
	 * @param U Gesamtspannung
	 * @param R1 erster Widerstand
	 * @param R2 zweiter Widerstand
	 * @return Spannung an R1
	 */
	public static Complex voltDiv(Complex U, Zweipol R1, Zweipol R2) {
		Complex erg = new Complex(0,0);
		
		erg = R1.Z.add(R2.Z);
		erg = R1.Z.div(erg);
		erg = U.mul(erg);
		
		return erg;
	}
	
	/**
	 * Stromteiler ueber zwei Widerstaende
	 * @param I Gesamtstrom
	 * @param R1 erster Widerstand
	 * @param R2 zweiter Widerstand
	 * @return Strom durch R1
	 */
	public static Complex currDiv(Complex I, Zweipol R1, Zweipol R2) {
		Complex erg = new Complex(0,0);
		
		erg = R1.Z.add(R2.Z);
		erg = R2.Z.div(erg);
		erg = I.mul(erg);
		
		return erg;
	}

	/**
	 * Gettermethode fuer Z
	 * @return Complex Z
	 */
	public Complex getZ() {
		return Z;
	}
	
	
	
}
