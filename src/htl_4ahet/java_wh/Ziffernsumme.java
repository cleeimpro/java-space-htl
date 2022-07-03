package htl_4ahet.java_wh;

import java.util.Scanner;

/**
 * Schreibe ein Java Programm,
 * welches eine Ganzzahl welche maximal 5 Stellen haben darf einliest,
 * die Ziffernsumme berechnet und anschließend die Ziffernsumme ausgibt.
 */
public class Ziffernsumme {
    /**
     * Methode ermittelt die Ziffernsummer einer Zahl
     * @param x uebergeben Zahl
     * @return Ziffernsumme
     */
    public static int ziffernsumme(int x) {
        int res = 0;
        int y = x<0?-x:x;
        for(char valueDigit : String.valueOf(y).toCharArray()) {
            res += Character.digit(valueDigit, 10);
        }
        if(x<0) res*=-1;
        return res;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Gib eine Zahl ein : ");
        int in = sc.nextInt();
        sc.close();
        int out = ziffernsumme(in);
        System.out.print("Ziffernsumme : "+out);
    }
}
