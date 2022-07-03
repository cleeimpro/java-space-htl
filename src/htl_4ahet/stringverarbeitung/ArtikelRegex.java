package htl_4ahet.stringverarbeitung;

import htl_4ahet.dateien.Datei;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * In einem TXT-File sollen alle Artikel (der,die,das) gezaehlt werden.
 * Als Beispieldatei wurde der 1. und 2. Teil des Fausts verwendet
 * @date 17. Feb 2021
 * @author Clemens Freudenthaler
 */
public class ArtikelRegex {
    public static void main(String[] args){
        try {
            /** Eingelesene Datei */
            Vector<String> data = Datei.leseDatei("data/htl/faust.txt");
            /** Regex Pattern fuer die Artikel */
            Pattern p = Pattern.compile("(^|[^\\wäÄöÖüÜß]|\\G)[Dd](er|as|ie|ER|AS|IE)(\\W|$)");

            Matcher m;
            int ct = 0;
            for(String line : data){
                m = p.matcher(line);
                while (m.find()) {
                    System.out.printf("%s || %s\n",line, m.group(0));
                    ct++;
                }
            }
            System.out.printf("Es sind %d Artikel (der,die,das) vorhanden!", ct);
        } catch (Exception exception) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(exception.getMessage());
        }
    }
}
