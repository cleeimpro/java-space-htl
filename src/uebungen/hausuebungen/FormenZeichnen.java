package uebungen.hausuebungen;

import java.util.Scanner;


/**
 * Schreibe ein Programm welches über eine Menüauswahl ein Dreieck oder
 * ein Quadrat ausgibt.
 * @author Clemens
 * @date 20.11.2018
 * @version 1.0
 *
 */

public class FormenZeichnen {
	
	/**
	 * Erzeugt grafisch ein Quadrat
	 * @param l -> Seitenlänge
	 */
	public static void quadrat(int l) {
		
		for(int i = 0;i<=l;i++) {
			System.out.print(i==0?"+ ":"- ");
			System.out.print(i==l?"+\n":"");
		}
		for(int i = 0;i<l;i++) {
			for(int j = 0;j<=l;j++) {
				System.out.print((j==0?"| ":j==l?"  |\n":"  "));
			}
		}
		for(int i = 0;i<=l;i++) {
			System.out.print(i==0?"+ ":"- ");
			System.out.print(i==l?"+\n":"");
		}
	}
	
	/**
	 * Erzeugt grafisch ein Dreieck
	 * @param l -> Seitenlänge
	 *
	 */
	public static void dreieck(int l) {
		for(int i = 1; i <= l; i++) { // Wie viele Zeilen
			for (int j = 0; j < i; j++) { // Wie viele Zeichen pro Zeile
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	/**
	 * Erzeugt grafisch ein verkehrtes Dreieck
	 * @param l -> Seitenlänge
	 *
	 */
	public static void dreieckVerkehrt(int l) {
		for(int i = 1; i <= l; i++) {
			for (int j = l; j > i; j--) {
				System.out.print(" ");
			}
			
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	/**
	 * Erzeugt grafisch ein breites Dreieck
	 * @param l -> Seitenlänge
	 *
	 */
	public static void dreieckBreit(int l) {
		for(int i = 1; i <= l; i++) {
			for (int j = l; j > i; j--) {
				System.out.print(" ");
			}
			
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			
			for (int j = 1; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * Erzeugt grafisch ein Quadrat aus X'en
	 * @param l -> Seitenlänge
	 *
	 */
	public static void quadratX(int l) {
		for(int i = 0; i < l; i++) {
			System.out.print(i==0?"x":" x");
		}
		
		for(int i = 2; i < l; i++) {
			
			for(int j = 1; j <= l; j++) {
				System.out.print(j==1?"\nx":j==l?" x":"  ");
			}	
		}
		
		for(int i = 0; i < l; i++) {
			System.out.print(i==0?"\nx":" x");
		}
	}
	
	/**
	 * Erzeugt grafisch ein Dreieck aus X'en
	 * @param l -> Höhe des Dreiecks
	 */
	public static void dreieckX(int l) {
		for(int i = 1; i < l; i++) {
			for(int j = l; j > 0; j--) {
				System.out.print(j==i?"x ":"  ");
			}
			
			for(int j = 1; j < i; j++) {
				System.out.print(j<i-1?"  ":"x");
			}
			System.out.println();
		}
		
		for(int i = 1; i < l*2; i++) {
			System.out.print(i==1?"x":" x");
		}
	}
	
	/**
	 * Eingabeaufforderung für Länge
	 * @return -> eingelesene Zahl
	 */
	public static int eingabe(Scanner sc1) {
		System.out.print("Länge > ");
		int x = sc1.nextInt();
		return x;
	}
	
	public static void main(String[] args) {
		// Deklaration
		boolean val = true;
		char c;
		Scanner sc = new Scanner(System.in);
		do {
		
		// Menü
		System.out.print("\n\nDreieck \t\t(d)"
						+"\nVerkehrtes Dreieck\t(v) "
						+"\nBreites Dreieck\t\t(b) "
						+"\nQuadrat \t\t(q) "
						+"\nQuadrat X\t\t(x)"
						+"\nDreieck X\t\t(y)"
						+"\nEnde \t\t\t(e) "
						+"\n\n\t> ");
		
		c = sc.next().charAt(0);
		
			switch (c) {
				case 'd' : dreieck(eingabe(sc)); break;
				case 'q' : quadrat(eingabe(sc)); break;
				case 'v' : dreieckVerkehrt(eingabe(sc)); break;
				case 'b' : dreieckBreit(eingabe(sc)); break;
				case 'x' : quadratX(eingabe(sc)); break; 
				case 'y' : dreieckX(eingabe(sc)); break;
				case 'e' : val = false; break;
				default : break;
			}
			
		}while(val);
		System.out.println("Bye Bye!");
		// Finalisierung
		sc.close();
	}
}
