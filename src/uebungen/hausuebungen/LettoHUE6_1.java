package uebungen.hausuebungen;

/**
 * Schreibe je eine Methode "ser" und "par" 
 * welche den Serien- und Parallelersatzwiderstand 
 * von zwei Widerständen (double!) berechnet.
 * 
 * @author Clemens Freudenthaler
 * @date 11.11.2018
 * @version 1.0
 *
 */
public class LettoHUE6_1 {
	
	/**
	 * Rechnet zwei Widerstände seriell.
	 * @param x -> erster Wdst.
	 * @param y -> zweiter Wdst.
	 * @return addiert die beiden Wdst
	 */
	public static double ser(double x, double y) {
		return x+y;
	}
	
	/**
	 * Rechnet zwei Widerstände parallel.
	 * @param x -> erster Wdst.
	 * @param y -> zweiter Wdst.
	 * @return gibt die beiden Wdst parallel geschalten zurück
	 */
	public static double par(double x, double y) {
		return x*y/(x+y);
	}
	
	public static void parTest(double x, double y, double z) {
		if(par(x,y)==z) System.out.println("OK");
		else System.out.printf("\nFALSCH -> x parallel y = %f statt %f", z, par(x,y));
	}

	public static void main(String[] args) {
		parTest(4,4,2);
		parTest(500,500,250);
		parTest(34,245,29.8566);
		parTest(2353,265,238.176);

	}

}
