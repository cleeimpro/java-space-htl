package uebungen.stundenwiederholungen;

import java.util.Scanner;

/**
 * Schreibe je eine Methode für die Berechnung der Fläche und des Umfanges eines Kreises.
 * Verwende hierzu folgende Methodenköpfe:

 * public static double kreisUmfang(double r)
 * public static double kreisFlaeche(double r)
 * Schreibe dazu ein Testprogramm, welches von der Tastatur den Radius einliest und dann Fläche und Umfang ausgibt.
 *
 * Die Ausgabe des Ergebnisses sollte wie folgt aussehen:
 *
 * A = 30,35
 * U = 14,80
 * 
 * @author Clemens Freudenthaler
 * @date 07.11.2018
 * @version 1.0
 *
 */

public class Kreis {
	
	
	/**
	 * Rechnet den Umfang eines Kreises mit Hilfe des Radius aus
	 * @param r -> Radius eines Kreise
	 * @return Umfang eines Kreises
	 */
	public static double kreisUmfang(double r) {
		return 2*r*Math.PI;
	}
	
	/**
	 * Rechnet den Flaecheninhalt eines Kreises mit Hilfe des Radius aus
	 * @param r -> Radius eines Kreises
	 * @return Flaeche eines Kreises
	 */
	public static double kreisFlaeche(double r) {
		return Math.pow(r, 2)*Math.PI;
	}
	

	public static void main(String[] args) {
		//Deklaration
		double r;
		Scanner sc =new Scanner(System.in);
		 
		//Eingabe
		System.out.print("Gib den Kreisradius ein : ");
		r = sc.nextDouble();
		
		//Ausgabe
		System.out.printf("A = %.2f ",kreisFlaeche(r));
		System.out.printf("\nU = %.2f ",kreisUmfang(r));
		
		//Finalisierung
		sc.close();

	}

}
