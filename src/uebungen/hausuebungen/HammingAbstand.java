package uebungen.hausuebungen;

public class HammingAbstand {

	public static void main(String[] args) {
		
		int[] sum = new int[1000];
		int ha = 100;
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {	
				for(int k = 0; k < 10; k++) {	
					sum[k+j*10+i*100] = getParitiBit(k) + (k << 1) + 
							(getParitiBit(j) << 5) + (j << 6) +
							(getParitiBit(i) << 10) + (i << 11);
					
				}
			}
		}
		
		for(int i = 0; i < sum.length; i++) {
			// 4-fach Paritaetsbit
			sum[i] = sum[i] << 4 | ((sum[i] >> 11) & 1 ^ (sum[i]>>6)&1 ^ (sum[i]>>1)&1)
					| ((sum[i]>>12)&1 ^ (sum[i]>>7)&1 ^ (sum[i]>>2)&1)<<1
					| ((sum[i]>>13)&1 ^ (sum[i]>>8)&1 ^ (sum[i]>>3)&1)<<2
					| ((sum[i]>>14)&1 ^ (sum[i]>>9)&1 ^ (sum[i]>>4)&1)<<3;
			// PP
			sum[i] = sum[i]<<1 | ((sum[i]>>0)&1 ^ (sum[i]>>1)&1 ^ (sum[i]>>2) & 1 ^ (sum[i]>>3)&1);
			System.out.println(i+": "+Integer.toBinaryString(sum[i]));
		}
		
		for(int i = 1 ; i < sum.length; i++) {
			if(getOne(sum[i]^sum[i-1])<ha){
				ha = getOne(sum[i] ^ sum[i-1]);
			}
		}
		System.out.println("Hamming Abstand: "+ha);
	}
	
	public static int getOne(int x) {
		int y = 0;
		
		while(x!=0) {
			if((x&1)==1)
				y++;
			x = x >> 1;
		}
		
		return y;
	}
	
	public static int getParitiBit(int x) {
		if(getOne(x) % 2 == 0) {
			return 0;
		}else
			return 1;
	}

}
