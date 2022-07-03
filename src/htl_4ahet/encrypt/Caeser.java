package htl_4ahet.encrypt;

import htl_4ahet.dateien.Datei;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class Caeser {

    public static Vector<String> decrypt(Vector<String> data){
        
        Vector<String> decrypt = new Vector<String>();
        
        for(String line : data){
            char[] chars = line.toCharArray();
            for(int i = 0; i< chars.length; i++){
                char c = chars[i];
                if(c>='a'&&c<='z'){
                    c = (char)(c+6);
                    if(c > 'z')c-=26;
                    c += 'A'-'a';
                } else if (c>='A'&&c<='Z'){
                    c = (char)(c+1);
                    if(c > 'Z')c-=26;
                    c += 'a'-'A';
                }
                chars[i] = c;
            }
            decrypt.add(new String(chars));
        }

        return decrypt;
    }

    public static void main(String[] args) {

        try {
            /** Eingelesene Datei */
            Vector<String> data1 = Datei.leseDatei("data/htl/uebungEncrypt_2.txt");
            // Entschluesseln
            Vector<String> decrypted = decrypt(data1);
            Datei.schreibeDatei(decrypted, "data/htl/uebung2_solve.txt");

        } catch (IOException ioException) {
            System.out.println("Datei kann nicht gelesen werden!");
            System.out.println(ioException.getMessage());
        }
    }
}
