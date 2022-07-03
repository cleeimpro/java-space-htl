package htl_4ahet.java_wh;


/**
 * Schreibe eine prozedurale Methode "paintZ" welche ein großes Z wie angegeben auf den Bildschirm zeichnet.
 * Die Größe des Buchstabens soll dabei als Parameter übergeben werden.
 */
public class PaintZ {
    /**
     * Methode zeichnet ein Z in Konsole in der uebergebenen Groesse.
     * @param size groesse des Z
     */
    public static void paintZ(int size){
        for(int i = 0; i < size; i++){
            System.out.print((i==size-1?"->\n":"-"));
        }
        for(int i = 0; i < size-1; i++){
            for(int j = 0; j < size-1-i; j++) {
                System.out.print((j == size-2-i ? " /\n" : " "));
            }
        }
        for(int i = 0; i < size; i++){
            System.out.print((i==0?"<-":"-"));
        }
    }
    public static void main(String[] args){
        paintZ(4);
    }
}
