package htl_4ahet.encrypt;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordRot {
    public static final String PASSWORD = "vollderdamboeckmove";

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
        for (int i = 4; i < 100; i++) {
            char[] pw = new char[i];
            char[] pwInv = new char[i];
            int pwIdx = 0;

            // Hashtables fuer jedes Passwortzeichen
            Vector<Hashtable<Character, Integer>> bs = new Vector<>();
            for (int j = 0; j < i; j++) {
                bs.add(new Hashtable<>());
            }

            // Analyse der Buchstabenhaeufigkeit pro Passwortzeichen
            for (String line : data) {
                char[] chars = line.toCharArray();
                for (char c : chars) {
                    c = Character.toLowerCase(c);
                    if (c >= 'a' && c <= 'z') {
                        if (bs.get(pwIdx).containsKey(c))
                            bs.get(pwIdx).put(c, bs.get(pwIdx).get(c) + 1);
                        else
                            bs.get(pwIdx).put(c, 1);
                        pwIdx = (pwIdx + 1) % i;
                    }
                }
            }

            for (int j = 0; j < i; j++) {
                int max = 0;
                char maxChar = 0;
                for (char c = 'a'; c <= 'z'; c = (char) (c + 1)) {
                    if (bs.get(j).containsKey(c) && bs.get(j).get(c) > max) {
                        max = bs.get(j).get(c);
                        maxChar = c;
                    }
                }
                int temp = maxChar - ('e' - 'a');
                if (temp < 'a') temp += 26;
                pw[j] = (char) temp;
                pwInv[j] = (char) ('z' - temp + 'b');
            }


            System.out.println(new String(pw));

            Vector<String> solve = encrypt(data, new String(pwInv));

            /** Regex Pattern fuer die Artikel */
            Pattern p = Pattern.compile("(^|[^\\wäÄöÖüÜß]|\\G)[Dd](er|as|ie|ER|AS|IE)([^\\wäÄöÖüÜß]|$)");
            Matcher m;
            int ct = 0;
            for (String line : solve) {
                m = p.matcher(line);
                while (m.find()) {
                    ct++;
                }
            }

            if(ct >= solve.size()/5){
                return solve;
            }
        } // Ende Passwortzeichen
        return null;
    }

    public static void main(String[] args) {

        try {
            /** Eingelesene Datei */
            Vector<String> data = Datei.leseDatei("data/htl/Handout.txt");
            // Verschluesseln
            Vector<String> encrypted = encrypt(data, PASSWORD.toLowerCase());
            Datei.schreibeDatei(encrypted, "data/htl/faustPenc1.txt");

            /** Eingelesene Datei */
            Vector<String> data1 = Datei.leseDatei("data/htl/save.txt");
            // Entschluesseln
            Vector<String> decrypted = decrypt(data1);
            Datei.schreibeDatei(decrypted, "data/htl/faustPencSolve.txt");

        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(ioException.getMessage());
        }
    }
}
