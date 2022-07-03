package uebungen.schuluebung;

import java.util.Vector;
/**
 * 4. Letto Rechnübung
 * Vier kleine mathematische Spielereien
 *
 * @author Freudenthaler Clemens
 * @date 23.01.2019
 * @version 1.0
 *
 */
public class LettoSUE4_1 {

	public static void main(String[] args) {
		System.out.println("Kuben: \t\t"+kuben());
		System.out.println("Ziffernsumme: \t"+zS());
		System.out.println("Teiler: \t"+teiler(100));
		System.out.println("Fakultät: \t"+faku());
	}
	
	/**
	 * Bestimme alle dreiziffrigen natürlichen Zahlen die gleich der Summe 
	 * der Kuben ihrer Ziffern sind. 
	 * @return die Zahlen
	 */
	public static Vector<Integer> kuben(){
		Vector<Integer> vK = new Vector<Integer>();
		
		for(int i = 100; i <= 999; i++) {
			if((Math.pow(i%10, 3)+Math.pow((i/10)%10, 3)+Math.pow((i/100)%10, 3))==i)
				vK.add(i);
		}
		
		return vK;
	}
	
	/**
	 * Bestimme alle zweiziffrigen Zahlen die durch ihre Ziffernsumme teilbar sind!
	 * @return die Zahlen
	 */
	public static Vector<Integer> zS(){
		Vector<Integer> vZ = new Vector<Integer>();
		
		for(int i = 10; i < 100; i++) {
			if(i%((i%10)+(i/10%10))==0)
				vZ.add(i);
		}
		
		return vZ;
	}
	
	/**
	 * Bestimme alle Zahlen bis zu einer Grenze für die 
	 * die Summe aller Teiler gleich der Zahl ist! 
	 * z.B. 28=1+2+4+7+14
	 * 
	 * @param x Grenze
	 * @return die Zahlen
	 */
	public static Vector<Integer> teiler(int x){
		Vector<Integer> vT = new Vector<Integer>();
		int zS;
		for(int i = 1; i <= x; i++) {
			zS=0;
			for(int j = 1; j < i; j++) {
				if(i%j==0)
					zS+=j;
			}
			if(zS==i)
				vT.add(i);
		}
		
		return vT;
	}
	
	/**
	 * Bestimme alle Zahlen zwischen 100 und 999 
	 * wo die Summe der Fakultäten der Ziffern gleich der Zahl sind (hze = h!+z!+e!)
	 * 
	 * @return die Zahlen
	 */
	public static Vector<Integer> faku(){
		Vector<Integer> vF = new Vector<Integer>();
		for( int i = 100; i<999; i++) {
			if(fak(i%10)+fak(i/10%10)+fak(i/100%10)==i)
				vF.add(i);
		}
		return vF;
	}
	
	/**
	 * Gibt die Fakultät eine Zahl zurück
	 * @param a Zahl
	 * @return Fakultät
	 */
	public static int fak(int a) {
        int ret=1;
        for(int i=1;i<=a;i++) {
            ret*=i;
        }
        return ret;
    }

}
