package example.einfuehrung;

import java.util.Scanner;

/**
 * Entwickle ein Java-Programm, welches folgende Aufgabenstellung erfüllt: Lies
 * vom Benutzer 2 Widerstände ein und berechne danach den Parallel und den
 * Serienersatzwiderstand. Die Ausgabe soll in wie folgt aussehen:
 *
 * Serie : 34,53 Ohm Parallel : 13,42 Ohm
 *
 * @author Clemens
 * @date 19.09.2018
 * @version 1.0
 *
 */

public class WiPaSe {

	public static void main(String[] args) {

		// Deklaration
		double r1, r2, rSerie, rParallel;
		Scanner sc = new Scanner(System.in);

		// Eingabeaufforderung
		System.out.print("Erster Widerstand\n\t> ");
		r1 = sc.nextDouble();
		System.out.print("Zweiter Widerstand\n\t> ");
		r2 = sc.nextDouble();

		// Werteverarbeitung
		rSerie = r1 + r2;
		rParallel = (r1 * r2) / (r1 + r2);

		// Ausgabe
		System.out.printf("\nSerie : %.2f Ohm\nParallel : %.2f Ohm", rSerie, rParallel);

		// Finalisierung
		sc.close();

	}

}
