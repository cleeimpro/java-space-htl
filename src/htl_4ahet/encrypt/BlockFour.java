package htl_4ahet.encrypt;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 * Ein Text wurde mit einer Blockverschlüsselung durch Umsortierung mit einer Blockgröße von 4 verschlüsselt.
 *
 * Eine kurze Nachricht wurde empfangen
 * "esDi Tert exrdwuale eis Ben piissael ützrsbeztetAu!
 *  eche ineizw Zteleeist ioc nvoh anrhn!de  "
 *
 * und entschlüsselt:
 * "Dieser Text wurde als ein Beispielsatz übersetzt!
 * Auch eine zweite Zeile ist noch vorhanden!"
 * Schreibe ein Programm welche einen verschlüsselten Text entschlüsselt und entschlüssle damit den Text.
 *
 * @author clee
 * @date 16.03.2021
 */

public class BlockFour {

    public static Vector<String> decrypt(Vector<String> data){

        String s = "";

        for(String line:data){
            s += line+'\n';
        }


        while(s.length()%4 != 0){
            s += " ";
        }
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i+=4){
            char[] temp = new char[2];
            // Erstes und zweites Zeichen speichern
            temp[0] = chars[i];
            temp[1] = chars[i+1];

            // Drittes + Viertes Zeichen => Erstes und zweites Zeichen
            chars[i] = chars[i+2];
            chars[i+1] = chars[i+3];

            // Erstes und zweites Zeichen => Drittes und Viertes
            chars[i+2] = temp[0];
            chars[i+3] = temp[1];
        }

        return new Vector<String>(Arrays.asList(new String(chars).split("\n")));
    }

    public static void main(String[] args) {

        try {
            /** Eingelesene Datei */
            Vector<String> data1 = Datei.leseDatei("data/htl/uebungEncrypt_1.txt");
            // Entschluesseln
            Vector<String> decrypted = decrypt(data1);
            Datei.schreibeDatei(decrypted, "data/htl/uebung1_solve.txt");

        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(ioException.getMessage());
        }
    }
}
