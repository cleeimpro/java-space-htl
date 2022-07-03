package uebungen.hausuebungen;

public class LettoHUE3_1 {
	 public static void main(String[] args) {

	        int a = 2;
	        int b = 6;

	        do {
	        	
	        	a++;

	        	if(a%2==0) {
	        		b=b-a%4;
	        	}
	        	else {
	        		b=b+a%3;
	        	}

	        }while(b>a);

	        
	        System.out.printf("a = %d\nb = %d", a, b);
	    }
}
