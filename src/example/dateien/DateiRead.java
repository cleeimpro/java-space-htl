package example.dateien;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DateiRead {

	public static void main(String[] args) throws IOException {
		// Deklaration
		FileReader fr = new FileReader("test.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String input = br.readLine();
		
		System.out.println(input.split(";"));
		
		
		br.close();
		fr.close();
		
	}

}
