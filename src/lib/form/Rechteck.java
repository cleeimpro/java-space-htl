package lib.form;

public class Rechteck extends Form{

	/** Erste Seite des Rechtecks */
	private double a;
	/** Zweite Seite des Rechtecks */
	private double b;
	
	/** Erzeug ein Rechteck mit den Seitenlängen
	 * @return a 1. Seitenlänge 
	 * @return b 2. Seitenlänge*/
	public Rechteck(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public double getFlaeche() {
		return a*b;
	}
	
	public double getUmfang() {
		return 2*a+2*b;
	}
	
	/** @return seitenlänge a */
	public double getSeiteA() {
		return a;
	}
	
	/** @param seitenlänge a*/
	public void setSeiteA(double seite) {
		this.a = seite;
	}
	
	/** @return seitenlänge b*/
	public double getSeiteB() {
		return a;
	}
	
	/** @param seitenlänge b*/
	public void setSeiteB(double seite) {
		this.a = seite;
	}

	@Override
	public String toString() {
		return "Rechteck [seiteA = " + a + "; seiteB = " + b + "]";
	}	
	
}
