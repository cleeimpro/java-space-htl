package example.einfuehrung;

import java.time.Duration;
import java.time.Instant;

/**
 * Schreibe ein Programm in Java welches den Text "Hallo Welt!" auf dem
 * Bildschirm ausgibt. Verwende bei dem Programm eine saubere JavaDOC
 * Dokumentation!
 * 
 * 
 * @author Clemens
 * @ver 1.0
 * @date 12.09.2018
 * 
 */

public class HalloWelt {

	public static void main(String[] args) {
		for(int x = 0; x<20; x++) {
		int k = 0;
		int m = 0;
		for(int i = 0; i < 100000; i++) {
			
			int j = (int)(Math.random()*3+1);
			
			Instant starts = Instant.now();
			switch(j) {
			case 1: break; //System.out.println("1"); break;
			case 2: break; //System.out.println("2"); break;
			case 3: break; //System.out.println("3"); break;
			case 4: break; //System.out.println("4"); break;
			case 5: break; //System.out.println("5"); break;
			case 6: break; //System.out.println("6"); break;
			case 7: break; //System.out.println("7"); break;
			case 8: break; //System.out.println("8"); break;
			case 9: break; //System.out.println("9"); break;
			
			default: break;
			}
			Instant ends = Instant.now();
			//; //System.out.println(Duration.between(starts, ends));
			Duration d = Duration.between(starts, ends);
			k += d.getNano();
			
			starts = Instant.now();
			if(j == 1)
				; //System.out.println("1");
			else if(j == 2)
				; //System.out.println("2");
			else if(j == 3)
				; //System.out.println("3");
			else if(j == 4)
				; //System.out.println("4");
			else if(j == 5)
				; //System.out.println("5");
			else if(j == 6)
				; //System.out.println("6");
			else if(j == 7)
				; //System.out.println("7");
			else if(j == 8)
				; //System.out.println("8");
			else if(j == 9)
				; //System.out.println("9");
			
			ends = Instant.now();
			//; //System.out.println(Duration.between(starts, ends));
			d = Duration.between(starts, ends);
			m += d.getNano();
			
		}
		if(k>m)
			System.out.printf("Der Switch war um %dns schneller als die If-Kette\n",(k-m)/100000);
		else if(k<m)
			System.out.printf("Die If-Kette war um %dns schneller als der Switch\n",(m-k)/100000);
		}
	}

}
