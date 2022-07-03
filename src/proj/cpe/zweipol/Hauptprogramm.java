package proj.cpe.zweipol;

import lib.Complex;

/**
 * Bedienprogramm fuer Zweipol
 * 
 * @author Freudenthaler Clemens
 * @date 27.03.2019
 * @version 2.0
 *
 */
public class Hauptprogramm {

	public static void main(String[] args) {
		
		double freq = 237.3;
		
		Complex U = new Complex(15.5);
		
		Zweipol R1 = new ZR(4700);
		Zweipol R2 = new ZR(4700);
		Zweipol R3 = new ZR(1200);
		Zweipol R4 = new ZR(3300);
		Zweipol C1 = new ZC(0.00000056, freq);
		Zweipol L1 = new ZL(0.0012, freq);
		
		
		Zweipol Zges = Zweipol.ser(Zweipol.par(Zweipol.ser(Zweipol.par(L1,R4),C1,R3),R2),R1);
		Complex Ir1 = Zges.getI(U).getZ();
		Zweipol Zx = Zweipol.ser(C1,R3,Zweipol.par(L1,R4));
		Complex Ir2 = Zweipol.currDiv(Ir1, R2, Zx);
		Complex Ir3 = Zweipol.currDiv(Ir1, Zx, R2);
		Complex Il1 = Zweipol.currDiv(Ir3, L1, R4);
		
		
		System.out.println(	"Ir1: "+Ir1.getAbs()+"\n"
							+"Ir1: "+Ir2.getAbs()+"\n"
							+"Ir3: "+Ir3.getAbs()+"\n"
							+"Il1: "+Il1.getAbs());
		
		
		
	}

}
