package lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Felder {
	/**
	 * Ließt vom Benutzer solange Werte ein und speichert diese in einem Array 
	 * solange die Eingabe != 0 sind.
	 * 
	 * @param message	Benutzerausgabe
	 * @param sc		Scanner
	 * @return			neues Array
	 */
	public static double[] readArray(String message,Scanner sc) {
		
		double f[] = new double[0];
		double val;
		System.out.print(message+" : ");
		do {
			val = sc.nextDouble();
			if (val!=0)
				f = addElement(f,val);
		}while(val!=0);
		
		return f;
	}
	
	/**
	 * Gibt ein Feld mit einem Infotext am Bildschirm aus und beendet mit einem Zeilenumbruch
	 * zB.: info[4|5,46|3,4]
	 * @param info	Infotext vor dem Feld
	 * @param f		Feld
	 */
	public static void printArray(String info, double f[]) {
		System.out.print(info+": [");
		for(int i = 0; i<f.length;i++) {
			System.out.printf("%.2f", f[i]);
			System.out.print(i+1==f.length?"]":"|");
		}
	}

	
	/**
	 * Es wird ein neues Feld erzeugt an das bestehende Feld f den Wert val hinten hinzufügt.
	 * @param val	neuer Wert
	 * @param f		altes Array
	 * @return		neues Array
	 */
	public static double[] addElement(double[] f, double val) {
		double x[] = new double[f.length+1];
		for(int i = 0; i<f.length;i++) {
			x[i]=f[i];
		}
		x[f.length]=val;
		return x;
	 }
	
	/**
	 * Fügt eine neue Zahl in ein Array an einer gewissen Stelle ein.
	 * 
	 * @param f 	Array
	 * @param val	neue Zahl
	 * @param pos	Stelle
	 * @return		Array mit val eingefügt
	 */
	public static double[] insertElement(double[] f, double val, int pos) {
		double x[] = new double[f.length+1];
		int j=0;
		
		for(int i = 0; i<=f.length;i++) {
			try {
				if(i==pos)
					x[i]=val;
				else {
					x[i]=f[j]; j++;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				System.err.println(e.getMessage());
				return f;
			}
		}
		
		
		return x;
	}
	
	/**
	 * Entfernt eine Zahl in einem Array an einer gewissen Stelle.
	 * 
	 * @param f 	Array
	 * @param pos	Stelle
	 * @return		Array mit entfernter Zahl eingefügt
	 */
	public static double[] removeElement(double[] f, int pos) {
		double x[] = new double[f.length-1];
		int j=0;
		
		for(int i = 0; i<f.length;i++) {
			try {
				if(i==pos);
				else {
					x[j]=f[i]; j++;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				System.err.println(e.getMessage());
				return f;
			}
		}
		
		
		return x;
	}
	
	/**
	 * Kopiert ein Array und ruft sortArray auf.
	 * @param f
	 * @return
	 */
	public static double[] sortArrayWithClone(double[] f) {
		double x[] = new double[f.length];
		
		for(int i=0;i<f.length;i++) {
			x[i]=f[i];
		}
		
		return sortArray(x);
	}
	
	/**
	 * Diese Methode sortiert die Werte eines Arrays der Größe nach.
	 * @param f 	Array, welches sortiert werden soll
	 * @return 	sortiertes Array
	 */
	
	public static double[] sortArray(double[] f) {
		
		int loop = f.length;
		double y;
		do {
			for(int i = 0; i < f.length-1; i++) {
				if(f[i] > f[i+1]) {
					y=f[i];
					f[i]=f[i+1];
					f[i+1]=y;
					loop = f.length;
				}
				else {
					loop--;
				}
				
			}
		}while(loop>0);
		return f;
	}
	
	/**
	 * Berechnet den größten Wert des Feldes
	 * @param feld Feld mit Feldelementen
	 * @return     größter im Feld vorkommender Wert
	 */
	public static double getMaxValue(double[] feld) {
		double y = feld[0];
		
		for(int i = 1; i<feld.length; i++) {
			if(y<feld[i]) y = feld[i];
		}
		return y;
	}
	
	
	/**
	 * Berechnet den kleinsten Wert des Feldes
	 * @param feld Feld mit Feldelementen
	 * @return     kleinster im Feld vorkommender Wert
	 */
	public static double getMinValue(double[] feld) {
		double y=feld[0];
		
		for(int i = 1; i<feld.length; i++) {
			if(y>feld[i]) y = feld[i];
		}
		return y;
	}
	
	/**
	 * Berechnet den Mittelwert des Feldes
	 * @param feld Feld mit Feldelementen
	 * @return     Mittelwert aller Feldelemente
	 */
	public static double getAveValue(double[] feld) {
		double y=0;
		
		for(int i = 0; i<feld.length; i++) {
			y += feld[i];
		}
		
		return y/feld.length;
	}
}
