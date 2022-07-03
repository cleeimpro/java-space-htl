package htl_4ahet.encrypt;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;

public class Analyse {
    public static void main(String[] args){
        Hashtable<Character,Integer> bs = new Hashtable<>();
        try {
            Vector<String> data = Datei.leseDatei("data/htl/faust.txt");
            Matcher m;
            for(String line:data){
                char[] chars = line.toCharArray();
                for(char c:chars){
                    c = Character.toLowerCase(c);
                    if(c>='a' && c <= 'z')
                        if (bs.containsKey(c))
                            bs.put(c,bs.get(c)+1);
                        else
                            bs.put(c,1);
                }
            }
            for(char c='a'; c<= 'z'; c=(char)(c+1)){
                if(bs.containsKey(c)){
                    System.out.println(c+":"+bs.get(c));
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
