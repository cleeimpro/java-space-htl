package lib.form;
/**
 * Klasse für die Berechnung eines Kreises
 *
 * @author Freudenthaler Clemens
 * @date 06.03.2019
 * @version 1.0
 *
 */
public class Kreis extends Form{

	/** Radius des Kreises */
	private double radius;
	
	/** Erzeug einen Kreis mit einem Radius von radius
	 * @return radius radius */
	public Kreis(double radius) {
		this.radius = radius;
	}
	
	public double getFlaeche() {
		return radius*radius*Math.PI;
	}
	
	@Override
	public double getUmfang() {
		return radius*2*Math.PI;
	}
	
	/** @return radiuslänge */
	public double getRadius() {
		return radius;
	}
	
	/** @param radius */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Kreis [radius = " + radius + "]";
	}	
	
}
