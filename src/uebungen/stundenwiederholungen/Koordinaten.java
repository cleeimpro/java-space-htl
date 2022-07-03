package uebungen.stundenwiederholungen;
import java.util.Scanner;

/**
 * Lies von der Tastatur die x- und y-Koordinate eines Punktes ein und bestimme ob der Punkt oberhalb,zwischen(oder direkt auf einer Geraden) oder unterhalb der beiden Geraden liegt.
 * Verwende für die Angabe der Position die Wörter "über","zwischen" und "unter", 
 * die Ausgabe sollte dabei wie folgt aussehen:

 * Punkt x: 4,5
 * Punkt y: 2,3
 * Der Punkt liegt zwischen den Geraden.
 * 
 * 
 * @author Clemens
 * @date 24.10.2018
 * @version 1.0
 *
 */

public class Koordinaten {
	
	
	/**
	 * Vergleich zwei DoubleWerte auf gleichheit
	 * @param x -> erster Wert
	 * @param y -> zweiter Wert
	 * @return -> true wenn der Wert x gleich y ist
	 */
	public static boolean equals(double x, double y) {
		
		
		
		return (x>y-0.001 && x<y+0.001);
	}

	public static void main(String[] args) {
		// Definition
		double xP, yP, k1, d1, k2, d2, y1, y2;
		
		k1 = 1.1;
		d1 = 2.5;
		k2 = 1.6;
		d2 = 2.5;
		
		Scanner sc = new Scanner(System.in);
		
		
		// Eingabe
		System.out.print("Punkt X: ");
		xP = sc.nextDouble();
		System.out.print("Punkt Y: ");
		yP = sc.nextDouble();
		
		y1 = k1*xP+d1;
		y2 = k2*xP+d2;
		
		// Abgleich - Ausgabe
		System.out.println("Der Punkt liegt");
		
		if (equals(yP,y1)&&equals(yP,y2))	System.out.println("im Schnittpunkt");
		else if (equals(yP,y1))				System.out.println("auf der 1. Geraden");
		else if (equals(yP,y2))				System.out.println("auf der 2. Geraden");
		
		if(yP>y1&&yP>y2)		System.out.println("über den Geraden");
		else if(yP<y1&&yP<y2)	System.out.println("unter den Geraden");
		else 					System.out.println("zwischen bzw. auf den Geraden");

		// Finishing
		sc.close();
		
	}

}
