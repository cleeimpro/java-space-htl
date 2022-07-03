package uebungen.hausuebungen;


public class LettoHUE3_2 {
	
	
	public static void main(String[] args) {	
		
		
		int a=16;
		int b=24;
		int c=30;


    while(a<b) {
        a += 2;
        b += a%2;
        c += b%a;
    }

    System.out.printf("A = %d\nB = %d\nC = %d", a, b, c);

   
}
}
