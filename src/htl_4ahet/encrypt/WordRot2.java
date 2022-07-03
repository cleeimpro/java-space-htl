package htl_4ahet.encrypt;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordRot2 {
    public static final String PASSWORD = "vigenere";

    /**
     * Verschluesseln mit gegebenen Schluessel
     * @param data
     * @param pwd
     * @return
     */
    public static Vector<String> encrypt(Vector<String> data, String pwd) {
        Vector<String> encrypted = new Vector<>();

        int secIdx = 0;
        for (String line : data) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int s = (int) pwd.toCharArray()[secIdx] - (int) 'a';
                if (chars[i] >= 'a' && chars[i] <= 'z') {
                    chars[i] = (char) (chars[i] + s);
                    if (chars[i] > 'z') chars[i] -= 26;
                    secIdx = (secIdx + 1) % pwd.length();
                } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                    chars[i] = (char) (chars[i] + s);
                    if (chars[i] > 'Z') chars[i] -= 26;
                    secIdx = (secIdx + 1) % pwd.length();
                }
            }
            String enc = new String(chars);
            encrypted.add(enc);
        }
        return encrypted;
    }

    public static Vector<String> decrypt(Vector<String> data) {
        // Passwortzeichen
        for (int i = 4; i < 40; i++){
            char[] pw = new char[i];
            char[] pwInv = new char[i];

            int pwInd = 0;

            // Hashtable fuer jedes Passwortzeichen
            Vector<Hashtable<Character,Integer>> bs = new Vector<>();
            for(int j = 0; j < i; j++){
                bs.add(new Hashtable<>());
            }

            // Sammeln und zaehlen der Buchstaben in Hashtables
            for (String line : data){
                char[] chars = line.toLowerCase().toCharArray();
                for(char c : chars){
                    if(c >= 'a' && c <= 'z'){
                        if(bs.get(pwInd).containsKey(c))
                            bs.get(pwInd).put(c, bs.get(pwInd).get(c)+1);
                        else
                            bs.get(pwInd).put(c, 1);
                        pwInd = (pwInd + 1) % i;
                    }
                }
            }

            // Analyse und zu neuem PW zusammensetzten
            for(int j = 0; j < i; j++){
                int max = 0;
                char maxChar = 0;
                // Buchstaben mit groesster Haeufigkeit finden
                for(char c = 'a'; c <= 'z'; c = (char)(c+1)){
                    if(bs.get(j).get(c) > max){
                        max = bs.get(j).get(c);
                        maxChar = c;
                    }
                }

                int temp = maxChar - ('e' - 'a');
                if(temp < 'a') temp += 26;
                pw[j] = (char) temp;
                pwInv[j] = (char) ('z' - temp + 'b');
            }

            System.out.println(new String(pw));

            Vector<String> solution = encrypt(data, new String(pwInv));

            /* Regex Pattern fuer die Artikel */
            Pattern p = Pattern.compile("(^|[^\\wäÄöÖüÜß]|\\G)[Dd](er|as|ie|ER|AS|IE)([^\\wäÄöÖüÜß]|$)");
            Matcher m;
            int ct = 0;
            for (String line : solution) {
                m = p.matcher(line);
                while (m.find()) {
                    ct++;
                }
            }

            if(ct >= solution.size()/5){
                return solution;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        try {
            /* Eingelesene Datei die verschluesselt werden soll */
            Vector<String> data = Datei.leseDatei("data/htl/faust.txt");
            // Verschluesseln
            Vector<String> encrypted = encrypt(data, PASSWORD.toLowerCase());
            Datei.schreibeDatei(encrypted, "data/htl/faustEnc.txt");

            /* Eingelesene Datei die entschluesselt werden soll */
            Vector<String> data_enc = Datei.leseDatei("data/htl/faustEnc.txt");
            // Entschluesseln
            Vector<String> decrypted = decrypt(data_enc);
            Datei.schreibeDatei(decrypted, "data/htl/faustSolve.txt");
        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(ioException.getMessage());
        }
    }
}
