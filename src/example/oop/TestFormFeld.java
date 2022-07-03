package example.oop;

import java.util.Arrays;

import lib.awt.form.*;

public class TestFormFeld {

	public static void main(String[] args) {
		Form formen[] = new Form[3];
		formen[0] = new Rechteck(0,0,4);
		formen[1] = new Kreis(0,0,1.5);
		formen[2] = new Rechteck(0,0,7);
		
		double sum = 0;
		
		System.out.println(Arrays.toString(formen));
		
		// Alle Rechtecke verdoppeln und alle Kreise halbieren
		
		for(Form f:formen) {
			if(f instanceof Rechteck) {
				((Rechteck) f).setSeiteA(2*((Rechteck) f).getSeiteA());
			}
			else if(f instanceof Kreis) {
				((Kreis) f).setR(((Kreis) f).getR()/2);
			}
			else if(f instanceof Rechteck) {
				//TODO
			}
		}
		
		System.out.println(Arrays.toString(formen));

		
	}

}
