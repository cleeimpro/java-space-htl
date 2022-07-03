package uebungen.hausuebungen;

/**
 * Schreibe die Methoden "isPos","isNeg","isNull" 
 * welche prüfen ob eine double Zahl positiv, 
 * negativ oder null ist. 
 * Das Ergebnis soll true oder false sein!
 * 
 * @author Clemens Freudenthaler
 * @date 11.11.2018
 * @version 1.0
 */

public class LettoHUE6_2 {

	/**
	 * Schaut, ob ein Wert positiv ist.
	 * @param x -> zu überprüfender Wert
	 * @return true, wenn positiv
	 */
	public static boolean isPos(double x) {
		return (x>0);
	}
	
	/**
	 * Schaut, ob ein Wert negativ ist.
	 * @param x -> zu überprüfender Wert
	 * @return true, wenn negativ
	 */
	public static boolean isNeg(double x) {
		return (x<0);
	}
	
	/**
	 * Schaut, ob ein Wert gleich 0 ist
	 * @param x -> zu überprüfender Wert
	 * @return true, wenn gleich 0
	 */
	public static boolean isNull(double x) {
		return (x==0);
	}
	
	public static void main(String[] args) {
		

	}

}
