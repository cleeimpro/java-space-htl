package example.dateien;


import java.io.FileReader;
import java.io.IOException;


public class CsvRead2 {
	
	
	

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("einmaleins.csv");
		char[] buffer = new char[10];
		int E[][] = new int[100][3];
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
					E[zeile][spalte] = E[zeile][spalte]*10+(z-'0');
				}
				
				
			}
		}
		
		for( int j=0;j<zeile;j++){
			for(int k=0;k<3;k++) {
				System.out.print(E[j][k]+(k<2?";":"\n"));
				
			}
			
		}
		
		
		fr.close();

	}

}
