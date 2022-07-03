package example.strukturen;
import java.util.Scanner;




public class Widerstand{
	
	public static double WdstSeriel(double r1,double r2) {
		return (r1+r2);
	}
	
	public static double WdstParallel(double r1,double r2) {
		return ((r1*r2)/(r1+r2));
	}
	

	public static void main(String[] args) {
		
		// Deklaration
		int auswahl;
		double r1,r2;
		Scanner sc = new Scanner(System.in);
		do {
			
			// Ausgabe
			System.out.print("1 > Seriel\n"
							+"2 > Parallel\n"
							+"0 > Ende\n\n"
							+"Auswahl : ");
			
			// Eingabe
			auswahl = sc.nextInt();
			
			
			
			// Auswertung
			if(auswahl==0)
				System.out.println("Auf Wiedersehen");
			else {
				System.out.print("\nErster Widerstandswert : ");
				r1 = sc.nextDouble();
				System.out.print("Zweiter Widerstandswert : ");
				r2 = sc.nextDouble();
			
			
				
				// Switch
				switch(auswahl) {
					case 1: System.out.println("Seriel : "+WdstSeriel(r1, r2)+"\n");break;
					case 2: System.out.println("Parallel : "+WdstParallel(r1, r2)+"\n");break;
					default: System.out.println("Es ist ein Fehler aufgetreten");
				}
			}
		
		}while(auswahl!=0);
		
		// Finale
		sc.close();
		
	}

}
