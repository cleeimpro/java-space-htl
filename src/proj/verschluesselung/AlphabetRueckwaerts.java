package proj.verschluesselung;

import java.util.Scanner;

/**
 * (De)codet claras "Geheimschrift"
 * 
 * @author Clemens
 *
 */
public class AlphabetRueckwaerts {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Gib die Botschaft ein: ");
		while (true) {
			String input = sc.nextLine();
			input = input.toUpperCase();
			if (input == "0")
				break;
			String output = "";
			char[] chars = new char[input.length()];
			input.getChars(0, input.length(), chars, 0);
			for (int i = 0; i < chars.length; i++) {
				char temp = chars[i];
				if (temp >= 'A' && temp <= 'M')
					output += (char) (2 * 'M' - temp + 1);
				else if (temp >= 'N' && temp <= 'Z')
					output += (char) ('M' - (temp - 'M') + 1);
				else
					output += temp;
			}
			System.out.print("\nBotschaft: " + output);
		}
		sc.close();
	}
}
