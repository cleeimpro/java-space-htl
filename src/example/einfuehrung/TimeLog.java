package example.einfuehrung;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TimeLog {

	public static void main(String[] args) throws IOException {
		Thread t;
		int timer = 0;
		FileWriter fw = null;
		Date date;

		t = new Thread();
		t.start();

		fw = new FileWriter("./files/timeLog.txt");

		for (int i = 0; i < 100; i++) {
			timer += 20;
			date = new Date();

			fw.write("Programm Time:\t" + timer + "\t\t" + date.getSeconds() + "\n");
			System.out.println("Log wurde geschrieben");

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.out.println("Error: " + e);
			}
		}

		fw.close();
	}

}
