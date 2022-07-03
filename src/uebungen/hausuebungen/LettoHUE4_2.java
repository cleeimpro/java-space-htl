package uebungen.hausuebungen;
import java.util.Scanner;

/**
 * Entwickle ein Java-Programm, welches folgende Aufgabenstellung erfüllt:
 * 
 * Lies einen Kreis (x,y Koordinate von Mittelpunkt und Radius) und 
 * einen Punkt ein und bestimme ob dieser Punkt innerhalb des Kreise liegt!
 * Lies der Reihe nach ein: 
 * Mittelpunkt x, Mittelpunkt y, Radius, zweiter Punkt x, zweiter Punkt y
 *
 * Als Antwort gibt entweder "innerhalb des Kreises" oder "außerhalb des Kreises" aus!
 * 
 * @author Clemens
 * @date 15.10.2018
 * @version 1.0
 *
 */

public class LettoHUE4_2 {

	public static void main(String[] args) {
		
		// Deklaration
		
		double x,y,r,x2,y2,r2;
		Scanner sc = new Scanner(System.in);
		
		// Eingabe
		
		System.out.print("Mittelpunkt x : ");
		x = sc.nextDouble();
		System.out.print("Mittelpunkt y : ");
		y = sc.nextDouble();
		System.out.print("Radius r : ");
		r = sc.nextDouble();
		System.out.print("Punkt x2 : ");
		x2 = sc.nextDouble();
		System.out.print("Punkt y2 : ");
		y2 = sc.nextDouble();
		
		// Abgleich
		
		r2=Math.sqrt(Math.pow(x-x2,2)+Math.pow(y-y2,2));
		
		// Ausgabe
		
		if(r2<r)
			System.out.println("\ninnerhalb des Kreises");
		else
			System.out.println("\naußerhalb des Kreises");
		
		// Finialisierung
		
		sc.close();
		
	
		

	}

}
