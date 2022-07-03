package uebungen.hausuebungen;
import java.util.Scanner;

/**
 * Schreibe ein Java Programm, welches eine Ganzzahl welche maximal 5 Stellen haben darf einliest, die Ziffernsumme berechnet und anschließend die Ziffernsumme ausgibt.
 * Die Ausgabe sollte dabei wie folgt aussehen:
 * Gib eine Zahl ein : 34
 * Ziffernsumme : 7
 * 
 * @author Clemens
 * @date 02.10.2018
 * @version 1.0
 *
 */

public class Ziffernsumme {

	public static void main(String[] args) {
		
		//Definition
		int userNumber;
		Scanner sc = new Scanner(System.in);
		
		//Eingabe
		System.out.print("Gib eine Zahl ein : ");
		userNumber = sc.nextInt();
		
		
		//Ausgabe
		
		System.out.println("Ziffernsumme : " + ziffernsumme(userNumber));
		
		//Finishing
		sc.close();
	
	
	}
	
	
	public static int ziffernsumme(int zahl) {

		if (zahl <= 9) return zahl;

		return zahl%10 + ziffernsumme(zahl/10);

	}
	
	
	
}
