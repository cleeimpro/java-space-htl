package uebungen.hausuebungen;
import java.util.Scanner;

/**
 * Entwickle ein Java-Programm, 
 * welches folgende Aufgabenstellung erfüllt:
 * 
 * Es sollen 2 ganze Zahlen eingelesen werden. 
 * Wenn die hunderter-Ziffern beider Zahlen gleich sind 
 * soll die Summe der beiden Zahlen ausgegeben werden, 
 * ansonsten soll die Differenz der beiden Zahlen ausgegeben werden.
 * 
 * Die Ausgabe sollte wie folgt aussehen:
 * Ergebnis : 435
 * 
 * @autor Clemens Freudenthaler
 * @date 15.10.2018
 * @version 1.0
 * 
 */

public class LettoHUE4_1 {

	public static void main(String[] args) {
		
		// Deklaration
		int zahlEins, zahlZwei, erg; 
		Scanner sc = new Scanner(System.in);
		
		// Eingabe
		System.out.print("Gib die erste Zahl ein : ");
		zahlEins = sc.nextInt();
		System.out.print("Gib die zweite Zahl ein : ");
		zahlZwei = sc.nextInt();
		
		// Auswertung
		if(zahlEins/100%10==zahlZwei/100%10)
			erg=zahlEins+zahlZwei;
		else
			erg=zahlEins-zahlZwei;
		
		// Ausgabe
		System.out.printf("Ergebnis : %d", erg);
		
		// Finialisierung
		sc.close();

	}

}
