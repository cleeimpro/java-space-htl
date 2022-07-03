package example.dateien;


import java.io.FileReader;
import java.io.IOException;

public class CsvRead {
	
	public static void printIntArray(String info, int f[]) {
		System.out.print(info+": [");
		for(int i = 0; i<f.length;i++) {
			System.out.printf("%d", f[i]);
			System.out.print(i+1==f.length?"]":"|");
		}
	}

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("einmaleins.csv");
		char[] buffer = new char[10];
		int[] a = new int[100];
		int[] b = new int[100];
		int[] c = new int[100];
		int spalte = 0;
		int zeile = 0;
		
		
		int anz;
		
		DRAUSSEN:
		while((anz = fr.read(buffer))>0) {
			
			
			for(int i=0;i<anz;i++) {
				//System.out.print(buffer[i]);
				char z = buffer[i];
				
				if(z == ';') spalte++;
				else if (z=='\n'){
					spalte = 0;
					zeile++;
					if(zeile >= 100) break DRAUSSEN;
				}
				else if(z>='0' && z<='9') {
					switch(spalte) {
						case 0: a[zeile] = a[zeile]*10+(z-'0');
						case 1: b[zeile] = b[zeile]*10+(z-'0');
						case 2: c[zeile] = c[zeile]*10+(z-'0');
						default: break;
					}
				}
				
				
			}
		}
		
		printIntArray("\na",a);
		printIntArray("\nb",b);
		printIntArray("\nc",c);
		
		
		fr.close();

	}

}
