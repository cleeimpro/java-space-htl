package htl_4ahet.encrypt;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Bei einem Text wurde eine Stabchiffre-Verschluesselung mit einer Periode von 3 verwendet.
 * Eine kurze Nachricht wurde empfangen
 *
 * "Dieieieltsseea rtZ zeT ieülxbete  riwssuetrt dzneto !ca
 * hlA suv coehri hnea inBndeeei nsz!pw"
 *
 * und entschlüsselt:
 *
 * "Dieser Text wurde als ein Beispielsatz übersetzt!
 * Auch eine zweite Zeile ist noch vorhanden!"
 *
 * Schreibe ein Programm welche einen verschlüsselten Text entschlüsselt!
 *
 * @autor Clemens Freudenthaer
 * @date 23 March 2021
 */

public class Stabchiffre {

    /**
     * Liest eine Datei in einen Vector
     * @param s Dateifad
     * @return Vector mit eingelesener Datei
     * @throws IOException Fehler wenn Datei nicht gefunden wird
     */
    public static Vector<String> leseDatei(String s) throws IOException{
        return leseDatei(new File(s));
    }

    /**
     * Liest eine Datei in einen Vector zeilenweise ein.
     * Am Ende jeder Zeile wird das CR entfernt.
     *
     * @param f Datei
     * @return  zeilenweise Datei in Vector
     */
    public static Vector<String> leseDatei(File f) throws IOException {
        Vector<String> ret = new Vector<String>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        String s;
        while((s= bufferedReader.readLine())!=null){
            ret.add(s);
        }
        bufferedReader.close();
        return ret;
    }

    /**
     * Schreib einen Vector mit Strings in eine Datei
     * Ein String ist gleich eine Zeile
     * @param data Daten welche gespeichert werden sollen
     * @param dateiname Dateifad
     * @throws IOException Fehler wenn Datei nicht erstellt/gefunden werden kann
     */
    public static void schreibeDatei(Vector<String> data, String dateiname)throws IOException {
        schreibeDatei(data, new File(dateiname));
    }

    /**
     * Schreib einen Vector mit Strings in eine Datei
     * Ein String ist gleich eine Zeile
     * @param data Daten welche gespeichert werden sollen
     * @param f Ausgabedatei
     * @throws IOException Fehler wenn Datei nicht erstellt/gefunden werden kann
     */
    public static void schreibeDatei(Vector<String> data,File f) throws IOException{
        FileWriter fileWriter = new FileWriter(f);
        for(String s: data){
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
    }

    /**
     * Einfache Stabentschluesselung
     * @param data Vector mit eingelesener Datei (Zeile = String)
     * @param period Zahl um welche gedreht wurde
     * @return
     */
    public static Vector<String> stabDecrypt(Vector<String> data, int period){
        String in = "";
        String out = "";

        // Text wird zu einem String
        // mit Zeilenumbruechen zusammengesetzt
        for(String x : data){
            in += x+"\n";
        }
        in = in.substring(0, in.length()-1);

        for (int i = 0; i < period; i++){
            for (int j = 0; i+j < in.length(); j+=period){
                out += in.charAt(i+j);
            }
        }

        return new Vector<>(Arrays.asList(out.split("\n")));
    }

    public static void main(String[] args){
        try {
            
            int turns = 3;
            Vector<String> data = leseDatei("data/htl/B9k_chiffre.txt");
            // Entschluesseln
            Vector<String> decrypted = stabDecrypt(data, turns);

            // Ausgabe in Konsole
            for(String s : decrypted){
                System.out.println(s);
            }

            schreibeDatei(decrypted, "data/htl/smueSolve.txt");
        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(ioException.getMessage());
        }
    }
}
