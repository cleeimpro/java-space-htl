package lib.form;
/**
 * Klasse für eine quadratische Form
 *
 * @author Freudenthaler Clemens
 * @date 06.03.2019
 * @version 1.0
 *
 */
public class Quadrat extends Form{

	/** Seitenlänge des Quadrates */
	private double seite;
	
	/** Erzeug ein Quadrat mit eienr Seitenlänge 
	 * @return seite Seitenlänge */
	public Quadrat(double seite) {
		this.seite = seite;
	}
	
	public double getFlaeche() {
		return seite*seite;
	}
	
	@Override
	public double getUmfang() {
		return 4*seite;
	}
	
	/** @return seitenlänge */
	public double getSeite() {
		return seite;
	}
	
	/** @param seitenlänge */
	public void setSeite(double seite) {
		this.seite = seite;
	}

	@Override
	public String toString() {
		return "Quadrat [seite = " + seite + "]";
	}
}
