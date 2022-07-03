package htl_4ahet.stringverarbeitung;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Faust2 {
    public static void main(String[] args){
        try {
            Vector<String> data = Datei.leseDatei("data/htl/faust.txt");
            Pattern p = Pattern.compile("\\((?<x>I+)\\)");
            Matcher m;
            for(int i=0;i< data.size(); i++){
                String line = data.get(i);
                while((m=p.matcher(line)).find()){
                    String found = m.group();
                    line = line.substring(0,m.start())+"(Faust "+m.group("x").length()+" Teil)" + line.substring(m.end());
                }
                /*line = line.replaceAll("\\(I\\)","(Faust 1. Teil)")
                        .replaceAll("\\(II\\)","(Faust 2. Teil)");*/
                data.set(i,line);
            }

            Datei.schreibeDatei(data,"data/htl/faust2.txt");
        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
        }
    }
}
