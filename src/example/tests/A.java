package example.tests;

import java.util.Scanner;

public class A{
	
	public static void main(String[] args) {
		B b =new B();
		Scanner sc = new Scanner(System.in);
		int x = 1;
		while(x != 0) {
			System.out.println("Command: ");
			x = sc.nextInt();
			System.out.println(b.getState());
			if(x == 9) {
				if(b.getName() == "Alive") {
					b.interrupt();
					System.out.println("B sollte unterbrochen werden");
				}else{
					b.run();
					System.out.println("B soll versuchen zu starten");
				}
			}
		}
	}

	
}
