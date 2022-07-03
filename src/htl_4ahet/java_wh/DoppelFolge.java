package htl_4ahet.java_wh;

import java.util.Scanner;

/**
 * Entwickle ein Java-Programm, welches folgende Aufgabenstellung erfüllt:
 * Berechne eine Zahlenfolge von n Zahlen beginnend bei 1 wobei jede Zahl das doppelte der vorhergehenden Zahl ist!
 * n soll von der Tastatur eingelesen werden und die Zahlenfolge am Bildschirm ausgegeben werden.
 */
public class DoppelFolge {
    public static void main(String[] args){
        System.out.print("Zahl: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 0; i<n ; i++){
            System.out.printf("%d%s",(int) Math.pow(2,i), i==n-1?"\n":",");
        }
        sc.close();
    }
}