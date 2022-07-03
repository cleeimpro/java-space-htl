package htl_4ahet.java_wh;

/**
 * Schreibe ein Methode sort, welche aus einem gegebenen Feld ein neues Feld mit sortierter Reihenfolge der Elemente erzeugt.
 * Verwende dazu den Bubble-Sort Algorithmus
 * Hierbei darf das ursprüngliche Feld nicht verändert werden.
 * Der Import vom Package "Arrays" ist nicht erlaubt!
 */
public class SortArray {

    /**
     * Zwei Werte eines Arrays werden vertauscht
     * @param feld      urspruengliches Array
     * @param fstInd    Index des ersten Felds
     * @param secInd    Index des zweiten Felds
     * @return          Array mit vertauschten Feldern
     */
    public static double[] swap(double feld[], int fstInd, int secInd ){
        double tmp = feld[fstInd];
        feld[fstInd] = feld[secInd];
        feld[secInd] = tmp;
        return feld;
    }

    /**
     * Ein Array wird mit dem Bubble-Sort Algorithmus sortiert
     * @param feld  Array, welches sortiert wird
     * @return      sortiertes Array
     */
    public static double[] sort(double feld[]) {
        for (int n = feld.length; n > 1; --n) {
            for (int i = 0; i < n - 1; ++i) {
                if (feld[i] > feld[i + 1]) {
                    feld = swap(feld, i, i + 1);
                }
            }
        }

        return feld;
    }


    public static void main(String args[]) {
        double x[] = {4, -2, 6, 3};
        double y[] = sort(x);
        for(double d : y){
            System.out.print(d + ", ");
        }
    }
}
