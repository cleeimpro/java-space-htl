package example.dateien;

import java.io.FileReader;
import java.io.IOException;

public class MwCsv {

	public static double[] deleteZero(double[] x) {
		
		for(int i = 0; i<x.length; i++) {
			if(x[i]==0) {
				x=lib.Felder.removeElement(x, i);
				i--;
			}
		}
		return x;
	}
	
	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("MwCsv.csv");
		
		char[] buffer = new char[10];
		
		int spalte = 0;
		int zeile = 0;
		
		boolean komma = false;
		
		double[] a = new double[100];
		double[] b = new double[100];
		
		int r=1;
		int anz;
		
		EXIT:
		while((anz = fr.read(buffer))>0) {
			for(int i=0;i<anz;i++) {

				char z = buffer[i];
				
				if(z==';') {
					spalte++;
					komma = false;
					r=1;
				}
				else if(z=='\n') {
					zeile++;
					spalte=0;
					komma = false;
					r=1;
					if(zeile >= 100) break EXIT;
				}
				else if(z==',') {
					komma = true;
				}			
				else if(z>='0' && z<='9') {
					z-='0';
					
					if(komma) {
						switch(spalte) {
							case 0: a[zeile] = a[zeile]+z/Math.pow(10, r); break;
							case 1: b[zeile] = b[zeile]+z/Math.pow(10, r); break;
							default: break;
						}
						r++;
					}
					
					
					else{
						switch(spalte) {
							case 0: a[zeile] = a[zeile]*10+z; break;
							case 1: b[zeile] = b[zeile]*10+z; break;
							default: break;
						}
					}			
				}
			}
		}
		
		a=deleteZero(a);
		b=deleteZero(b);
		
		lib.Felder.printArray("a",a);
		lib.Felder.printArray("\nb",b);
		
		System.out.println();
		System.out.println(lib.Felder.getAveValue(a));
		System.out.println(lib.Felder.getAveValue(b));
		
		
		fr.close();
		
	}

}
