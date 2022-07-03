package proj.cpe.zweipol;

public class ZC extends Zweipol {
	
	public ZC(double C, double f) {
		setZ(0,-(1/(C*2*Math.PI*f)));	
	}
	
}
