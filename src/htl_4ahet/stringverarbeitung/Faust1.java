package htl_4ahet.stringverarbeitung;

import htl_4ahet.dateien.Datei;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Faust1 {
    public static void main(String[] args){
        try {
            Vector<String> data = Datei.leseDatei("data/htl/faust.txt");
            int ct = 0;
            Pattern p = Pattern.compile("(^|[^a-zA-Z]?)[Ii]st[\\s\\.\\,\\!$]");
            Matcher m;
            for(String line : data){
                //if(line.matches(".*[\\.\\,\\!\\s][Ii]st([\\s\\.\\,\\!].*|$)")) ct++;
                m = p.matcher(line);
                while (m.find()) {
                    ct++;
                }
            }
            System.out.printf("Es kommen %d \"ist\" vor!", ct);
        } catch (Exception exception) {
            System.out.println("Datei kann nicht gelesen werden!");
        }
    }
}
