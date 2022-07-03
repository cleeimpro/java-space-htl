package htl_4ahet.java_wh;

import java.util.Scanner;

/**
 * Lies von der Tastatur die x- und y-Koordinate eines Punktes ein und
 * bestimme ob der Punkt oberhalb,zwischen(oder direkt auf einer Geraden) oder unterhalb der beiden Geraden liegt.
 * Verwende für die Angabe der Position die Wörter "ueber","zwischen" und "unter"
 */
public class ZweiGerade {
    public static void main(String[] args){
        double k1 = -1.2, d1 = -1.2;
        double k2 = 0.9, d2 = 2.5;
        Scanner sc = new Scanner(System.in);
        System.out.print("Punkt x: ");
        double x = sc.nextDouble();
        System.out.print("Punkt y: ");
        double y = sc.nextDouble();
        sc.close();

        String ret;

        if(k1*x+d1 < y && k2*x+d2 < y){
            ret = "\u00fcber";
        } else if(k1*x+d1 > y && k2*x+d2 > y) {
            ret = "unter";
        } else {
            ret = "zwischen";
        }

        System.out.printf("Der Punkt liegt %s den Geraden.\n",ret);

    }
}
