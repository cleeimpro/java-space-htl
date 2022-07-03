package example.dateien;

import java.io.FileWriter;
import java.io.IOException;

public class DateiWrite {

	public static void main(String[] args) throws IOException {
		// Deklaration
		FileWriter fw;
		// Initialisierung des Dateihandles mit dem Dateinamen
		fw = new FileWriter("test.txt");
		
		// schreiben in die Datei
		fw.write("Hello World!;");
		fw.write("Test");
		
		// Datei schließen
		fw.close();
	}

}
