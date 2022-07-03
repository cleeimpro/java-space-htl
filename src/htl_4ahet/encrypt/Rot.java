package htl_4ahet.encrypt;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;

public class Rot {

    public static final char SECRET = 'n';

    public static char rot(char c, char secret){
        int i = (int) c;
        int s = (int) secret-(int)'a';
        if(c>='a'&&c<='z'){
            c = (char)(i+s);
            if(c > 'z')c-=26;
        } else if (c>='A'&&c<='Z'){
            c = (char)(i+s);
            if(c > 'Z')c-=26;
        }
        return c;
    }

    public static void main(String[] args){
        try {
            /** Eingelesene Datei */
            Vector<String> data = Datei.leseDatei("data/htl/faust.txt");
            Vector<String> encrypted = new Vector<>();

            Matcher m;
            for(String line: data){
                char[] chars = line.toCharArray();
                for(int i = 0; i<chars.length; i++){
                    chars[i] = rot(chars[i],SECRET);
                }
                String enc = new String(chars);
                encrypted.add(enc);
            }
            Datei.schreibeDatei(encrypted,"data/htl/faustenc1.txt");
        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(ioException.getMessage());
        }
    }


}
