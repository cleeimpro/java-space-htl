package example.einfuehrung;

import java.util.Scanner;

/**
 * Entwickle ein Java-Programm, welches vom Benutzer eine Temperatur in °C
 * abfrägt und dann die Temperatur in Kelvin ausgibt. Verwende eine formatierte
 * Ausgabe mit drei Nachkommastellen!
 * 
 * @author Clemens
 * @date 19.9.2018
 * @version 2.0
 *
 */

public class Temperatur {

	public static void main(String[] args) {

		// Deklaration
		double tC, tK;
		Scanner sc = new Scanner(System.in);

		// Eingabeaufforderung
		System.out.print("Temperatur in Grad Celsius:\n\t> ");
		tC = sc.nextDouble();

		// Celsius zu Kelvin
		tK = tC + 273.15;

		// Ausgabe
		System.out.printf("Temperatur in Grad Kelvin\n\t> %.3f", tK);
		sc.close();
	}

}
