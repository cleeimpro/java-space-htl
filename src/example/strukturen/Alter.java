package example.strukturen;
import java.util.Scanner;

/**
 * Erstelle ein Programm welches dein eingegebnes Alter der Kategorie
 * 		> Kind
 * 		> Erwachsener
 * zuweist.
 * 
 * 
 * @author Clemens Freudenthaler
 * @date 17.10.2018
 * @version 1.1
 *
 */

public class Alter {

	public static void main(String[] args) {
		
		// Deklaration
		int alter;
		Scanner sc = new Scanner(System.in);
		
		// Eingabe
		System.out.print("Gib dein Alter ein\n\t> ");
		alter = sc.nextInt();
		
		// Abgleich - Ausgabe
		
		if(alter<0)
			System.out.println("Ich");
		else if(alter<3)
			System.out.println("Ich glaube nicht, dass Sie schon Ihr Alter wissen.");
		else if(alter<18)
			System.out.println("Bald kommt der Ernst des Lebens auf Sie zu.");
		else if(alter<120)
			System.out.println("Sie sind im Main-Part Ihres Lebens angekommen.");
		else
			System.out.println("Sie sind ein Museumsstück!");
		
		// Finalisierung
		sc.close();

	}

}
