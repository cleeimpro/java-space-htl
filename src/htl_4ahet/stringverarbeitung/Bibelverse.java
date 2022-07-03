package htl_4ahet.stringverarbeitung;

import htl_4ahet.dateien.Datei;

import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Aus einem Teil des alten Testaments sollen alle Verse herausgefillter werden.
 * @date 17. Feb 2021
 * @author Clemens Freudenthaler
 */
public class Bibelverse {
    public static void main(String[] args){
        try {
            /** Eingelesene Datei */
            Vector<String> data = Datei.leseDatei("data/htl/bibel02.txt");
            /** Regex Pattern fuer die Verse */
            //Pattern p = Pattern.compile("\\(([^\\(\\)]*?\\d.*?)\\)");
            Pattern p = Pattern.compile("\\((?<bs>[^\\(\\)]+)\\)");
            /** Gefundene Verse */
            HashSet<String> verse = new HashSet<>();
            //Vector<String> verse = new Vector<>();

            Matcher m;
            for(String line : data){
                m = p.matcher(line);
                while (m.find()) {

                    String[] list = m.group("bs").split(";");
                    for(String s: list){
                        s = s.trim();
                        if(s.startsWith("vgl.")) s=s.substring(4).trim();
                        if (s.length()>0 && s.matches(".*\\d.*")){
                            verse.add(s.trim());
                        }
                    }
                }
            }
            Vector<String> liste = new Vector<>(verse);
            Collections.sort(liste);
            for(String s : liste)
                System.out.println(s);
            System.out.printf("Es sind %d Verse vorhanden!", liste.size());
        } catch (Exception exception) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(exception.getMessage());
        }
    }
}
