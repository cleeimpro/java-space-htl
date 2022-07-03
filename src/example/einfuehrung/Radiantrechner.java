package example.einfuehrung;

import java.util.Scanner;

/**
 * Entwickle ein Java-Programm, welches folgende Aufgabenstellung erfüllt: Lies
 * vom Benutzer einen Winkel in Grad ein und gib den Winkel in Radiant aus.
 * Verwende eine formatierte Ausgabe mit vier Nachkommastellen!
 * 
 * 
 * @author Clemens
 * @date 26.09.2018
 * @version 1.0
 *
 */

public class Radiantrechner {

	public static void main(String[] args) {

		// Deklaration
		double wG, wR;
		Scanner sc = new Scanner(System.in);

		// Eingabe
		System.out.print("Winkel in Grad\n\t> ");
		wG = sc.nextDouble();

		// Umwandlung
		wR = wG * Math.PI / 180;

		// Ausgabe
		System.out.printf("Winkel in RAD\n\t> %f", wR);

		// Finialisierung
		sc.close();

	}

}
