package htl_4ahet.java_wh;
import java.util.Scanner;

/**
 * Erstelle ein Programm welches anhand von 3 eingegebene Zahlen erkennt,
 * welche Art von Dreieck sich daraus ergibt.
 *
 * > Gleichseitig
 * > Gleichschenklig
 * > Rechtwinkelig
 * > Rechtwinkelig Gleichschenkelig
 * > Allgemeines Dreieck
 * > Kein Dreieck
 *
 * @author Clemens Freudenthaler
 * @version 1.0
 *
 */

public class Dreieck {
    /**
     * Die Werte werden mit einer Genauigkeit von 0.1 Prozent verglichen
     *
     * @param x -> erster Wert
     * @param y -> zweiter Wert
     * @return false/true
     */
    public static boolean equals(double x, double y, double toleranz) {

        double toleranzX=toleranz*x;
        double toleranzY=toleranz*y;

        return x+toleranzX >= y && y-toleranzY <= x;
    }

    public static void main(String[] args) {

        // Deklaration
        double a,b,c;
        double x;
        Scanner sc = new Scanner(System.in);

        // Ausgabe - Einlesen
        System.out.print("Erste Seite : ");
        a = sc.nextDouble();
        System.out.print("Zweite Seite : ");
        b = sc.nextDouble();
        System.out.print("Dritte Seite : ");
        c = sc.nextDouble();

        // Sortieren
        if(a>c) 	{x = a;a = c;c = x;}
        if(b>c) 	{x = b;b = c;c = x;}
        if(a>b) 	{x = a;a = b;b = x;}

        // Ausgabe - Auswerten
        if(equals(a,c,0.001))
            System.out.print("gleichseitiges ");
        else if(equals((Math.pow(a, 2)+Math.pow(b, 2)),Math.pow(c, 2),0.001)) {
            if(equals(a,b,0.001) || equals(b,c,0.001))
                System.out.print("rechtwinkeliges gleichschenkeliges ");
            else
                System.out.print("rechtwinkeliges ");
        }
        else if(a+b <= c-c*0.001)
            System.out.print("kein ");
        else if(equals(a,b,0.001) || equals(b,c,0.001))
            System.out.print("gleichschenkeliges ");

        else
            System.out.print("allgemeines ");

        System.out.print("Dreieck");

        // Finalisierung
        sc.close();

    }

}