package example.strukturen;
import java.util.Scanner;

public class Menue {

	public static void main(String[] args) {
		// Deklaration
		int m;
		Scanner sc = new Scanner(System.in);
		
		// Ausgabe Liste
		System.out.print("1 > Gewicht\n"
						 + "2 > Größe\n"
						 + "3 > Alter\n"
						 + "0 > Ende\n\n"
						 + "Auswahl > ");
		
		// Benutzereingabe
		m = sc.nextInt(); 
		
		// Switch
		switch(m) {
			case 1: System.out.println("\nGewicht"); 	break;
			case 2: System.out.println("\nGröße"); 		break; 
			case 3: System.out.println("\nAlter"); 		break;
			case 0: System.out.println("\nEnde"); 		break;
			default:break;
		}
		
		// Finalisierung
		sc.close();

	}

}
