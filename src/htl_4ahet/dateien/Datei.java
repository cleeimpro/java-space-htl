package htl_4ahet.dateien;

import java.io.*;
import java.util.Vector;

public class Datei {

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

    public static void schreibeDatei(Vector<String> data, String dateiname)throws IOException {
        schreibeDatei(data, new File(dateiname));
    }

    public static void schreibeDatei(Vector<String> data,File f) throws IOException{
        FileWriter fileWriter = new FileWriter(f);
        for(String s: data){
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
    }

    public static void main(String[] args){
        try {
            Vector<String> data = leseDatei("data/htl/text.txt");
            for (String s:data){
                System.out.println(s);
            }
            schreibeDatei(data,"data/htl/text1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
