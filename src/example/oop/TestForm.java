package example.oop;

import lib.awt.form.*;

public class TestForm {

	public static void main(String[] args) {
		Form f = new Rechteck(0,0,2);

		System.out.println("a: "+((Rechteck)f).getSeiteA());
		System.out.println("Form: "+ f);
	}

}
