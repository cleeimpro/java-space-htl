package uebungen.stundenwiederholungen;
/**
 * Schreibe eine prozedurale Methode "paintZ" welche ein großes Z wie angegeben auf den Bildschirm zeichnet.
 * @author Clemens Freudenthaler
 * @date 28.11.2018
 * @version 1.0
 *
 */
public class LettoSMUE_3 {
	/**
	 * Konstruiert grafisch ein Z
	 * @param size -> Länge
	 */
	public static void paintZ(int size) {
		for(int i = 0; i <= size; i++) {
			System.out.print(i==size?">\n":"-");
		}
		
		for(int i = 1; i < size; i++) {
			for(int j = size; j>=i; j--){
				System.out.print(j==i?"/\n":" ");
			}
		}
		for(int i = 0; i <= size; i++) {
			System.out.print(i==0?"<":i==size?"-\n":"-");
		}
	}

	public static void main(String[] args) {
		paintZ(4);
	}

}
